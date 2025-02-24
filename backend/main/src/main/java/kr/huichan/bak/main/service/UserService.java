package kr.huichan.bak.main.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.huichan.bak.main.document.User;
import kr.huichan.bak.main.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(String username, String password) {
        if (userRepository.findByUsername(username) == null)
            userRepository.insert(new User(username, passwordEncoder.encode(password)));
        // else
        // 오류 반환
    }

    // 생성자 주입
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
