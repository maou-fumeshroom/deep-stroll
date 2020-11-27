package ap.deepstroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.AdminEntity;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.mapper.AdminMapper;
import ap.deepstroll.mapper.AdminRoleMapper;
import ap.deepstroll.mapper.UserMapper;
import ap.deepstroll.utils.JwtTokenUtil;
import ap.deepstroll.vo.AdminLogInVO;
import ap.deepstroll.vo.ResponseVO;
import ap.deepstroll.vo.UserLogInVO;

@Service
public class IdentityService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminRoleMapper adminRoleMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    
    /**
     * 用户登录 返回token id
     * @author mxf
     * @param telephone
     * @param pwd
     * @return
     */
    public ResponseVO userLogin(String telephone, String pwd) {

        final Log logger = LogFactory.getLog(getClass());
        UserEntity user = userMapper.queryUserByTel(telephone);

        if (user != null) {
            if (pwd.equals(user.getPassword())) {
                if (user.getState() == 0) {
                    final String token = jwtTokenUtil.generateToken(Long.toString(user.getId()), "common");
                    logger.info("用户: " + telephone + "登录");
                    return ResponseVO.builder()
                                     .data(
                                         UserLogInVO.builder()
                                                    .id(user.getId())
                                                    .token(token)
                                                    .menus(null)
                                                    .build()
                                     )
                                     .result(
                                         Result.builder()
                                               .code(1)   
                                               .message("ok")
                                               .build()
                                     )
                                     .build();            
                } else { // 账户被冻结
                    return ResponseVO.builder()
                                     .result(
                                         Result.builder()
                                               .code(0)
                                               .message("frozen")
                                               .build()
                                     )
                                     .build();
                }
            } else { // 密码错误
                return ResponseVO.builder()
                                 .result(
                                    Result.builder()
                                           .code(0)
                                           .message("wrong password")
                                           .build()
                                 )
                                 .build();
            }
        } else { // 账户不存在
            return ResponseVO.builder()
                             .result(
                                Result.builder()
                                      .code(0)
                                      .message("no user")
                                      .build()
                              )
                              .build();
        }

    }

    /**
     * 管理员登录 返回token
     * @author mxf
     * @param account
     * @param password
     * @return
     */
    public ResponseVO adminLogin(String account, String password) {

        final Log logger = LogFactory.getLog(getClass());
        AdminEntity admin = adminMapper.queryAdminByAccount(account);

        if (admin != null) {
            if (password.equals(admin.getPassword())) {
                if (admin.getState() == 0) {
                    logger.info("管理员：" + account + "登录");
                    final String token = jwtTokenUtil.generateToken(Integer.toString(admin.getId()), "admin");
                    return ResponseVO.builder()
                                     .data(
                                         AdminLogInVO.builder()
                                                    .token(token)
                                                    .menu(null)
                                                    .build()
                                     )
                                     .result(
                                         Result.builder()
                                               .code(1)   
                                               .message("ok")
                                               .build()
                                     )
                                     .build();            
                } else { // 账户被冻结
                    return ResponseVO.builder()
                                     .result(
                                         Result.builder()
                                               .code(0)
                                               .message("frozen")
                                               .build()
                                     )
                                     .build();
                }
            } else { // 密码错误
                return ResponseVO.builder()
                                 .result(
                                    Result.builder()
                                           .code(0)
                                           .message("wrong password")
                                           .build()
                                 )
                                 .build();
            }
        } else { // 账户不存在
            return ResponseVO.builder()
                             .result(
                                Result.builder()
                                      .code(0)
                                      .message("no user")
                                      .build()
                              )
                              .build();
        }
        
    }

    /**
     * 添加管理员
     * @author mxf
     * @param account
     * @param password
     * @param roleId
     * @return
     */
    // TODO<mxf>
    public ResponseVO adminRegister(String account, String password, Integer roleId) {
        
        final Log logger = LogFactory.getLog(getClass());

        AdminEntity admin = adminMapper.queryAdminByAccount(account);
        if (admin == null) {
            logger.info("新管理员" + account + " " + password);
            admin = AdminEntity.builder().account(account).password(password).build();
            adminMapper.insertAdmin(admin);
            Integer[] roleIds = {roleId};
            if (admin.getId() == null) {
                return ResponseVO.builder()
                                 .result(
                                     Result.builder()
                                           .code(0)
                                           .message("unknow error") 
                                           .build() 
                                 )
                                 .build();
            }
            adminRoleMapper.insertAdminRole(admin.getId(), roleIds);
            return ResponseVO.builder()
                             .result(
                                 Result.builder()
                                 .code(1)
                                 .message("ok") 
                                 .build() 
                             )
                             .build();
        } else {
            return ResponseVO.builder()
                             .result(
                                 Result.builder()
                                 .code(0)
                                 .message("account already exists") 
                                 .build() 
                             )
                             .build();
        }
    }

}
