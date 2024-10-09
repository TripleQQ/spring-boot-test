package com.example.demo.security.impl;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private long id;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> roles;


    public UserDetailsImpl(long id,String name,String email,String password, List<GrantedAuthority> roles){
        this.id=id;
        this.username=name;
        this.email=email;
        this.roles=roles;
        this.password=password;

    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> roles=user.getRoleList().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(user.getUserId(),user.getUsername(),user.getEmail(),user.getPassword(),roles);



    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        System.out.println("Password"+this.password);
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
