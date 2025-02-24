package kr.huichan.bak.main.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import kr.huichan.bak.main.dto.UserDocument;
import kr.huichan.bak.main.repository.UserDocumentRepository;

@Service
public class MongoUserDetailService implements UserDetailsService {

    private final UserDocumentRepository userRepository;

    @Override
    public UserDocument loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 생성자 주입
    public MongoUserDetailService(UserDocumentRepository userRepository) {
        this.userRepository = userRepository;
    }
}
