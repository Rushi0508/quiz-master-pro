package com.quizmasterpro.quizmaterpro.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quizmasterpro.quizmaterpro.Dtos.User.UserLoginDto;
import com.quizmasterpro.quizmaterpro.Dtos.User.UserRegisterDto;
import com.quizmasterpro.quizmaterpro.Models.User;
import com.quizmasterpro.quizmaterpro.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public User register(UserRegisterDto userRegisterDto){
         // Check for email existence
         if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        // Check for username uniqueness
        if (userRepository.findByUsername(userRegisterDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Encrypt password
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        // Convert DTO to entity and save
        User user = modelMapper.map(userRegisterDto, User.class);
        userRepository.save(user);
        return user;
    }

    public User login(UserLoginDto userLoginDto){
        var user = userRepository.findByEmail(userLoginDto.getEmail());
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Account is not registered");
        }

        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.get().getPassword())){
            throw new BadCredentialsException("Invalid Credentials");
        }
        return user.get();
    }

    public void deleteUserById(UUID id){
        if (userRepository.findById(id).isEmpty()) {
            throw new UsernameNotFoundException("No user found to delete");
        }
        userRepository.deleteById(id); 
    }
}
