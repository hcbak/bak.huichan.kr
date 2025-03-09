package kr.huichan.bak.main.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import kr.huichan.bak.main.repository.UserRepository;

@Service
public class MongoUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 생성자 주입
    public MongoUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
