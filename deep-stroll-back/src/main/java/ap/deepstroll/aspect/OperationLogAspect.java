package ap.deepstroll.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import ap.deepstroll.annotation.OperationLogAnnotation;
import ap.deepstroll.entity.LogEntity;
import ap.deepstroll.mapper.LogMapper;
import ap.deepstroll.utils.JwtTokenUtil;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    LogMapper logMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    /**
     * 设置操作日志切入点   在注解的位置切入代码
     */
    @Pointcut("@annotation(ap.deepstroll.annotation.OperationLogAnnotation)")
    public void operLogPoinCut() {    }


    @AfterReturning(returning  /**
     * 记录操作日志
     * @param joinPoint 方法的执行点
     * @param result  方法返回值
     * @throws Throwable
     */ = "result", value = "operLogPoinCut()")
    public void saveOperLog(JoinPoint joinPoint, Object result) throws Throwable {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        try {
            // //将返回值转换成map集合
            // Map<String, String> map = (Map<String, String>) result;
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            OperationLogAnnotation annotation = method.getAnnotation(OperationLogAnnotation.class);
            String operName = "";
            if (annotation != null) {
                operName = annotation.operName();
            }

            String token = request.getHeader("Authorization").substring("Bearer ".length());
            String id = jwtTokenUtil.getIdFromToken(token);
            logMapper.insertLog(
                LogEntity.builder()
                         .adminId(Integer.valueOf(id))
                         .content(operName)
                         .build()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

