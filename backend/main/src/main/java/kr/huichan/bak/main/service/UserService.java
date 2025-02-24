package kr.huichan.bak.main.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.huichan.bak.main.dto.UserDocument;
import kr.huichan.bak.main.repository.UserDocumentRepository;

@Service
public class UserService {
    
    private final UserDocumentRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(String username, String password) {
        if (userRepository.findByUsername(username) == null)
            userRepository.insert(new UserDocument(username, passwordEncoder.encode(password)));
        // else
        // 오류 반환
    }

    // 생성자 주입
    public UserService(UserDocumentRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
