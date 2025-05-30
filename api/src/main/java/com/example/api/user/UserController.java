package com.example.api.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();

        if (userName == null || password == null || userName.isBlank() || password.isBlank()) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Bad Request"));
        }

        User user = userService.postLoginUser(userName, password);

        if (user != null) {
            // セッションにユーザー情報を保存
            session.setAttribute("user", user);

            LoginResponse.UserResponse userInfo = new LoginResponse.UserResponse(user.getId(), user.getUserName());
            LoginResponse response = new LoginResponse("Login Successful", userInfo);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Unauthorized"));
        }
    }

    // エラーレスポンス用のクラス
    static class ErrorResponse {
        private final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}