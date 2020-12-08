package ap.deepstroll.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ap.deepstroll.utils.JwtTokenUtil;

/**
 * @author mxf
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("UserDetailsServiceImp")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("AdminDetailsServiceImp")
    private UserDetailsService adminDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain chain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String roleToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            String[] idAndRole = jwtTokenUtil.getIdAndRoleFromToken(roleToken);
            String id = idAndRole[0];
            String role = idAndRole[1];
            if (id != null && role != null) {
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 如果我们足够相信token中的数据，也就是我们足够相信签名token的secret的机制足够好
                    // 这种情况下，我们可以不用再查询数据库，而直接采用token中的数据
                    // 本例中，我们还是通过Spring Security的 @UserDetailsService 进行了数据查询
                    // 但简单验证的话，你可以采用直接验证token是否合法来避免昂贵的数据查询
                    UserDetails user;
                    if (role.equals("common")) {
                        user = userDetailsService.loadUserByUsername(id);
                    } else if (role.equals("admin")) {
                        user = adminDetailsService.loadUserByUsername(id);
                    } else {
                        user = null;
                    }
                    if (user != null && user.isEnabled()) {
                        String name = user.getUsername();
                        if (name != null && name.length() > 0) {
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                  user, null, user.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            logger.info("用户: " + name + "通过身份认证");
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

    
}
