package ap.deepstroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

import ap.deepstroll.service.IdentityService;
import ap.deepstroll.service.TestService;
import ap.deepstroll.utils.JwtTokenUtil;
import ap.deepstroll.vo.ResponseVO;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    IdentityService identityService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/api/login")
    public ResponseVO userLogin(
        @RequestParam String telephone,
        @RequestParam String pwd
    ) {
        return identityService.userLogin(telephone, pwd);
    }

    @PostMapping("/api/admin/login")
    public ResponseVO adminLogin(
        @RequestParam String account,
        @RequestParam String password
    ) {
        return identityService.adminLogin(account, password);
    }

    @PostMapping("/api/test")
    @PreAuthorize("hasRole('common')")
    public String test(@RequestHeader HttpHeaders headers) {
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        System.out.println(id);
        return testService.test();
    }

    @PostMapping("/api/admin/test")
    @PreAuthorize("hasRole('admin')")
    public String adminTest(@RequestHeader HttpHeaders headers) {
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        System.out.println(id);
        return "adminok";
    }
}
