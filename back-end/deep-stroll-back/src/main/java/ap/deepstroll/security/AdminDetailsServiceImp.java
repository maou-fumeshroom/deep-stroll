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

import ap.deepstroll.entity.AdminEntity;
import ap.deepstroll.entity.AdminRoleEntity;
import ap.deepstroll.entity.RoleEntity;
import ap.deepstroll.mapper.AdminMapper;
import ap.deepstroll.mapper.AdminRoleMapper;
import ap.deepstroll.mapper.RoleMapper;

@Service("AdminDetailsServiceImp")
public class AdminDetailsServiceImp implements UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminRoleMapper adminRoleMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        AdminEntity adminEntity;
        if ((adminEntity = adminMapper.queryAdminById(Integer.valueOf(id))) != null) {

            List<Integer> roleIds = new ArrayList<Integer>();
            List<AdminRoleEntity> adminRoleEntitys;
            try {
                adminRoleEntitys = adminRoleMapper.queryByAdminId(adminEntity.getId());
            } catch(Exception e) {
                return null;
            }
            if (adminRoleEntitys == null) { return null; }
            for (AdminRoleEntity adminRoleEntity: adminRoleEntitys) {
                roleIds.add(adminRoleEntity.getRoleId());
            }

            List<RoleEntity> roleNames = roleMapper.queryRoleByIds(roleIds, 0);
            // System.out.println(roleNames);
            List<String> temp = new ArrayList<String>();
            for (RoleEntity roleName: roleNames) {
                temp.add("ROLE_" + roleName.getName());
            }

            return new UserDetailsImp(
                            adminEntity.getId().longValue(),
                            adminEntity.getAccount(), 
                            adminEntity.getPassword(), 
                            adminEntity.getState(),
                            temp.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                       );

        } else {
            return null;
        }
    }

    
}
