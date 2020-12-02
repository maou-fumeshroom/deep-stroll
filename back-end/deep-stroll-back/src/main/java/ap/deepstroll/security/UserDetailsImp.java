package ap.deepstroll.security;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImp implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String username;
    private final String password;
    private final Integer state;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImp(
        Long id,
        String username,
        String password,
        Integer state,
        Collection<? extends GrantedAuthority> authorities
    ){
        this.id = id;
        this.username = username;
        this.password = password;
        this.state = state;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Long getId() {
        return this.id;
    }

    // 账户是否未过期
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        // * may need modify
        return true;
    }

    // 账户是否未锁定
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        // * may need modify
        return true;
    }

    // 密码是否未过期
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        // * may need modify
        return true;
    }

    // 账户是否激活
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        // * may need modify
        return state == 0 ? true:false;
    }

}