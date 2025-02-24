package kr.huichan.bak.main.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "users")
public class UserDocument implements UserDetails {
    @Id
    private ObjectId _id;
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public UserDocument(String username, String password) {
        this.username = username;
        this.password = password;
        this.authorities = new HashSet<>(Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
