package ap.deepstroll.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ap.deepstroll.bo.BackMenuBO;
import ap.deepstroll.bo.HomeMenuBO;
import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.AdminEntity;
import ap.deepstroll.entity.BackMenuEntity;
import ap.deepstroll.entity.HomeMenuEntity;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.global.Global;
import ap.deepstroll.global.VerificationCode;
import ap.deepstroll.global.VerificationCodeMap;
import ap.deepstroll.mapper.AdminMapper;
import ap.deepstroll.mapper.AdminRoleMapper;
import ap.deepstroll.mapper.BackMenuMapper;
import ap.deepstroll.mapper.HomeMenuMapper;
import ap.deepstroll.mapper.RoleMenuMapper;
// import ap.deepstroll.mapper.RoleMapper;
import ap.deepstroll.mapper.UserMapper;
import ap.deepstroll.utils.JwtTokenUtil;
import ap.deepstroll.utils.SMSMessageUtil;
import ap.deepstroll.vo.request.AdminLogInRequestVO;
import ap.deepstroll.vo.request.AdminRegisterRequestVO;
import ap.deepstroll.vo.request.GetVerificationCodeRequestVO;
import ap.deepstroll.vo.request.RegisterRequestVO;
import ap.deepstroll.vo.request.UserLogInRequestVO;
import ap.deepstroll.vo.response.AdminLogInVO;
import ap.deepstroll.vo.response.ResponseVO;
import ap.deepstroll.vo.response.UserLogInVO;

@Service
public class IdentityService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminRoleMapper adminRoleMapper;

    @Autowired
    SMSMessageUtil sMSMessageUtil;

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Autowired
    BackMenuMapper backMenuMapper;

    @Autowired
    HomeMenuMapper homeMenuMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    
    /**
     * 用户登录 返回token id
     * @author mxf
     * @param telephone
     * @param pwd
     * @return
     */
    public ResponseVO userLogin(UserLogInRequestVO userLogInRequestVO) {
        String telephone = userLogInRequestVO.getTelephone();
        String pwd = userLogInRequestVO.getPwd();

        final Log logger = LogFactory.getLog(getClass());
        UserEntity user = userMapper.queryUserByTel(telephone);

        if (user != null) {
            if (pwd.equals(user.getPassword())) {
                if (user.getState() == 0) {
                    final String token = jwtTokenUtil.generateToken(Long.toString(user.getId()), "common");
                    logger.info("用户: " + telephone + "登录");
                    List<HomeMenuEntity> homeMenuEntitys = homeMenuMapper.queryMenuByState(0);
                    List<HomeMenuBO> homeMenuBOs = new ArrayList<HomeMenuBO>();
                    for (HomeMenuEntity homeMenuEntity: homeMenuEntitys) {
                        homeMenuBOs.add(
                            HomeMenuBO.builder()
                                      .id(homeMenuEntity.getId())
                                      .path(homeMenuEntity.getPath())
                                      .name(homeMenuEntity.getName())
                                      .build()
                        );
                    }
                    return ResponseVO.builder()
                                     .data(
                                         UserLogInVO.builder()
                                                    .id(user.getId())
                                                    .token(token)
                                                    .menus(homeMenuBOs.toArray(new HomeMenuBO[homeMenuBOs.size()]))
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
     * 为指定手机号发送验证码
     * @author mxf
     * @param telephone
     * @return
     */
    public ResponseVO getVerificationCode(GetVerificationCodeRequestVO getVerificationCodeRequestVO) {
        String telephone = getVerificationCodeRequestVO.getTelephone();
        if (userMapper.queryUserByTel(telephone) == null) {
            String letterTable = Global.letterTable;
            String code = "";
            for(int i = 0; i < 6; i++){
                int index = (int)(Math.random()*letterTable.length());
                code += letterTable.charAt(index);
            }
            code = "123456";
            VerificationCodeMap verificationCodeMap = VerificationCodeMap.getInstance();
            verificationCodeMap.put(telephone, code);
            if (sMSMessageUtil.sendSMSMessage(telephone, code)) {
                System.out.println(code);
                return ResponseVO.builder()
                                 .result(
                                     Result.builder()
                                         .code(1)
                                         .message("ok")
                                         .build()
                                 )
                                .build();
            } else { // 向短信api发送请求失败
                return ResponseVO.builder()
                                 .result(
                                     Result.builder()
                                         .code(0)
                                         .message("SMSMessage server error")
                                         .build()
                                 )
                                 .build();
            }
        } else { // 手机号已注册
            return ResponseVO.builder()
                             .result(
                                 Result.builder()
                                       .code(0)
                                       .message("telephone exist")
                                       .build()
                             )
                             .build();

        }
        
    }

    /**
     * 绑定手机号 注册新用户
     * @author mxf
     * @param nickname
     * @param pwd
     * @param telephone
     * @param code
     * @return
     */
    public ResponseVO bindTelephone(RegisterRequestVO registerRequestVO) {
        String telephone = registerRequestVO.getTelephone();
        String nickname = registerRequestVO.getNickname();
        String code = registerRequestVO.getCode();
        String pwd = registerRequestVO.getPwd();
        VerificationCodeMap verificationCodeMap = VerificationCodeMap.getInstance();
        VerificationCode verificationCode = verificationCodeMap.get(telephone);
        if (verificationCode != null) {
            if (verificationCode.isExpire()) {
                if (verificationCode.getCode().equals(code)) {
                    UserEntity userEntity = UserEntity.builder()
                                                      .nickname(nickname)
                                                      .password(pwd)
                                                      .telephone(telephone)
                                                      .build();
                    if (userMapper.insertUser(userEntity) > 0) {
                        verificationCodeMap.remove(telephone);
                        return ResponseVO.builder()
                                         .result(
                                             Result.builder()
                                                 .code(1)
                                                 .message("ok")
                                                 .build()
                                         )
                                         .build();
                    } else { // 数据库错误插入失败
                        return ResponseVO.builder()
                                         .result(
                                             Result.builder()
                                                 .code(0)
                                                 .message("database error")
                                                 .build()
                                         )
                                         .build();
                    }
                } else { // 验证码错误
                    return ResponseVO.builder()
                                     .result(
                                         Result.builder()
                                             .code(0)
                                             .message("code error")
                                             .build()
                                     )
                                     .build();
                }
            } else { // 验证码超时
                return ResponseVO.builder()
                                 .result(
                                     Result.builder()
                                         .code(0)
                                         .message("timeout")
                                         .build()
                                 )
                                 .build();
            }
        } else { // 手机号未被发送验证码
            return ResponseVO.builder()
                             .result(
                                 Result.builder()
                                       .code(0)
                                       .message("no telephone")
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
    public ResponseVO adminLogin(AdminLogInRequestVO adminLogInRequestVO) {
        String account = adminLogInRequestVO.getAccount();
        String password = adminLogInRequestVO.getPassword();

        final Log logger = LogFactory.getLog(getClass());
        AdminEntity admin = adminMapper.queryAdminByAccount(account);

        if (admin != null) {
            if (password.equals(admin.getPassword())) {
                if (admin.getState() == 0) {
                    logger.info("管理员：" + account + "登录");
                    final String token = jwtTokenUtil.generateToken(Integer.toString(admin.getId()), "admin");
                    
                    List<Integer> roleIds = adminRoleMapper.queryRoleIdsByAdminId(admin.getId());
                    List<Integer> backMenuIds = roleMenuMapper.queryMenuIdByRoleIds(roleIds);
                    List<BackMenuEntity> backMenuEntities = backMenuMapper.queryById(backMenuIds);
                    List<BackMenuBO> backMenuBOs = new ArrayList<BackMenuBO>();
                    
                    for (BackMenuEntity backMenuEntity: backMenuEntities) {
                        if (backMenuEntity.getParentId() == null) {
                            backMenuBOs.add(
                                BackMenuBO.builder()
                                          .id(backMenuEntity.getId())
                                          .path(backMenuEntity.getPath())
                                          .name(backMenuEntity.getName())
                                          .sonMenuList(new ArrayList<BackMenuBO>())
                                          .build()
                            );
                        } else {
                            for (int i = 0; i < backMenuBOs.size(); i++) {
                                if (backMenuBOs.get(i).getId() == backMenuEntity.getParentId()) {
                                    backMenuBOs.get(i).addSonMenuList(
                                        BackMenuBO.builder()
                                          .id(backMenuEntity.getId())
                                          .path(backMenuEntity.getPath())
                                          .name(backMenuEntity.getName())
                                          .sonMenuList(new ArrayList<BackMenuBO>())
                                          .build()
                                    );
                                    break;
                                }
                            }
                        }
                    }

                    for (BackMenuBO backMenuBO: backMenuBOs) {
                        backMenuBO.tranArray();
                    }

                    return ResponseVO.builder()
                                     .data(
                                         AdminLogInVO.builder()
                                                    .token(token)
                                                    .menu(backMenuBOs.toArray(new BackMenuBO[backMenuBOs.size()]))
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
    public ResponseVO adminRegister(AdminRegisterRequestVO adminRegisterRequestVO) {
        String account = adminRegisterRequestVO.getAccount();
        String password = adminRegisterRequestVO.getAccount();
        Integer roleId = adminRegisterRequestVO.getRoleId();
        
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
