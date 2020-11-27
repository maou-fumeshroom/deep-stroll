package ap.deepstroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import ap.deepstroll.service.FileService;
import ap.deepstroll.service.IdentityService;
import ap.deepstroll.service.TestService;
import ap.deepstroll.utils.JwtTokenUtil;
import ap.deepstroll.vo.request.AdminLogInRequestVO;
import ap.deepstroll.vo.request.AdminRegisterRequestVO;
import ap.deepstroll.vo.request.GetVerificationCodeRequestVO;
import ap.deepstroll.vo.request.RegisterRequestVO;
import ap.deepstroll.vo.request.UserLogInRequestVO;
import ap.deepstroll.vo.response.ResponseVO;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    IdentityService identityService;

    @Autowired
    FileService fileService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/api/login")
    public ResponseVO userLogin(@RequestBody UserLogInRequestVO userLogInRequestVO) {
        return identityService.userLogin(userLogInRequestVO);
    }

    @PostMapping("/api/ssm")
    public ResponseVO getVerificationCode(@RequestBody GetVerificationCodeRequestVO getVerificationCodeRequestVO) {
        return identityService.getVerificationCode(getVerificationCodeRequestVO);
    }

    @PostMapping("/api/register")
    public ResponseVO register(@RequestBody RegisterRequestVO registerRequestVO) {
        return identityService.bindTelephone(registerRequestVO);
    }
    
    @PostMapping("/api/file")
    @PreAuthorize("hasRole('common')")
    public ResponseVO uploadFile(@RequestParam MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @PostMapping("/api/admin/login")
    public ResponseVO adminLogin(@RequestBody AdminLogInRequestVO adminLogInRequestVO) {
        return identityService.adminLogin(adminLogInRequestVO);
    }

    @PostMapping("/api/admin/add")
    @PreAuthorize("hasRole('admin')")
    public ResponseVO adminRegister(@RequestBody AdminRegisterRequestVO adminRegisterRequestVO) {
        return identityService.adminRegister(adminRegisterRequestVO);
    }

    @PostMapping("/api/test")
    @PreAuthorize("hasRole('common')")
    public String test(@RequestHeader HttpHeaders headers) {
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long.valueOf(id);
        System.out.println(id);
        return testService.test();
    }

    @PostMapping("/api/admin/test")
    @PreAuthorize("hasRole('admin')")
    public String adminTest(@RequestHeader HttpHeaders headers) {
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Integer Id = Integer.valueOf(id);
        System.out.println(id);
        return "adminok";
    }

}
