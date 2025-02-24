package kr.huichan.bak.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.huichan.bak.main.dto.SignUpRequest;
import kr.huichan.bak.main.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<Void> createUser(SignUpRequest reqeust) {
        
        userService.createUser(reqeust.username(), reqeust.password());

        return ResponseEntity.ok().build();
    }

    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
