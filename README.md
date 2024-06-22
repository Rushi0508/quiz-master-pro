# Quiz Master Pro

Quiz Master is a Spring Boot backend application for creating and managing quizzes. Users can log in, select quiz topics, choose difficulty levels, and take quizzes generated dynamically using OpenAI. The application tracks user scores and provides insights into user performance globally.

## Description

Quiz Master is designed to offer a seamless quiz-taking experience with a focus on dynamic content generation and user performance tracking. Users can register, log in, and select from predefined quiz topics. Each quiz is customized based on selected difficulty levels and the number of questions. Upon completion, users can view their scores and see how they rank globally among other users.

## Features

- **User Registration and Authentication:**
  - Secure user registration with unique username and email validation
  - Password encryption using BCrypt
  - JWT-based authentication and authorization

- **Quiz Management:**
  - Selection of quiz topics from a predefined list
  - Choice of difficulty levels and the number of questions (up to 20)
  - Dynamic question generation using OpenAI API
  - Real-time quiz progress tracking

- **User Performance Tracking:**
  - Score calculation and display upon quiz completion
  - Global ranking based on scores and difficulty level
  - Detailed quiz history for each user with question and answer review

- **User Profile Management:**
  - Update profile information
  - View attended quizzes and performance history

- **API Endpoints:**
  - RESTful endpoints for user registration, login, quiz management, and profile management

## Technologies Used

### Frontend
- Typescript
- React Native
- React Native Paper

### Backend
- Java
- Spring Boot
- MongoDB
- Spring Security
- Gemini API
- Lombok
- Maven

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

If you have any questions or feedback, feel free to contact me at rushigandhi14@gmail.com.


