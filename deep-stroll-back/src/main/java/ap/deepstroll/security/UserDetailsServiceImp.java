package ap.deepstroll.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.mapper.UserMapper;

@Service("UserDetailsServiceImp")
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    // username为telephone/account
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        
        UserEntity userEntity;

        if ((userEntity = userMapper.queryUserById(Long.valueOf(id))) != null) {
            List<String> temp = new ArrayList<String>();
            temp.add("ROLE_common");

            return new UserDetailsImp(
                            userEntity.getId(),
                            userEntity.getTelephone(), 
                            userEntity.getPassword(), 
                            userEntity.getState(),
                            temp.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                       );
        } else {
            return null;
        }

	}

}
