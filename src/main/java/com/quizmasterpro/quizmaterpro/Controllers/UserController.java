package com.quizmasterpro.quizmaterpro.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.quizmasterpro.quizmaterpro.Models.User;
import com.quizmasterpro.quizmaterpro.Services.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizmasterpro.quizmaterpro.Dtos.User.TokenResp;
import com.quizmasterpro.quizmaterpro.Dtos.User.UserDto;
import com.quizmasterpro.quizmaterpro.Dtos.User.UserLoginDto;
import com.quizmasterpro.quizmaterpro.Dtos.User.UserRegisterDto;
import com.quizmasterpro.quizmaterpro.Services.JwtService;
import com.quizmasterpro.quizmaterpro.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.getAllUsers().stream().map(user->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try{
            User user =  userService.getUserById(id);
            var quizzes = quizService.getByUserId(id);
            user.setQuizzes(quizzes);
            return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto, BindingResult bindingResult) {
        try{
            if(bindingResult.hasErrors()){
                return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
            }
            var user = userService.register(userRegisterDto);
            TokenResp tokenResp = modelMapper.map(user, TokenResp.class);
            String jwtToken = jwtService.generateToken(user);
            tokenResp.setToken(jwtToken);
            return ResponseEntity.ok(tokenResp);
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid UserLoginDto userLoginDto, BindingResult bindingResult) {
        try{
            if(bindingResult.hasErrors()){
                return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
            }
            User user = userService.login(userLoginDto);
            TokenResp tokenResp = modelMapper.map(user, TokenResp.class);
            String jwtToken = jwtService.generateToken(user);
            tokenResp.setToken(jwtToken);
            return ResponseEntity.ok(tokenResp);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try{
            userService.deleteUserById(id);
        return ResponseEntity.ok(true);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
