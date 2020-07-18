package com.aaa.cloud.annotation;

import com.aaa.cloud.model.LoginLog;
import com.aaa.cloud.model.User;
import com.aaa.cloud.service.IProjectService;
import com.aaa.cloud.utils.AddressUtils;
import com.aaa.cloud.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.util.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import static com.aaa.cloud.staticproperties.TimeFormatProperties.TIME_FORMAT;

/**
 * @Author: zcl
 * @ClassName: LogAspect
 * @Description:
 *      AOP
 *      @Slf4j:
 *          simple log for java
 * @Date: 2020/7/15 18:14
**/
@Aspect
@Slf4j
@Component
public class LogAspect {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author: zcl
     * @Methodname: pointcut
     * @Description:
     *      定义切点信息
     *      这个时候就不能再按照常规的切点(service/controller)
     *      直接去切自定义的注解
     *      也就是说当检测自定义注解存在的时候，切面触发，也就是说AOP才会被触发
     * @Date: 2020/7/15 18:20
     * @Param: []
     * @Return: void
     **/
    @Pointcut("@annotation(com.aaa.cloud.annotation.LoginAnnotation)")
    public void pointcut(){
            //TODO  nothing to do
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws ClassNotFoundException {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable th) {
            th.printStackTrace();
        }

        // 获取Request对象
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        // 1.获取ip地址(最简单的)
        String ipAddr = IPUtils.getIpAddr(request);// 需要一个HttpServletRequest对象
        // 2.获取地理位置(最简单的)
        Map<String, Object> addressMap = AddressUtils.getAddresses(ipAddr, "UTF-8");


        LoginLog loginLog = new LoginLog();
        loginLog.setIp(ipAddr);
        loginLog.setLocation(addressMap.get("province")+"|"+addressMap.get("city"));
        loginLog.setLoginTime(DateUtil.formatDate(new Date(), TIME_FORMAT));

        // 3.获取Username--->想要获取到username，必须要获取到目标方法的参数值
        Object[] args = proceedingJoinPoint.getArgs();
        User user = (User) args[0];
        /*User user1 = null;
        for (Object arg : args) {
            user1 = (User) arg;
        }*/
        loginLog.setUsername(user.getUsername());


        // 4.获取操作的类型以及具体操作的内容(反射)
        // 4.1.获取目标类名(全限定名)
        String tarClassName = proceedingJoinPoint.getTarget().getClass().getName();

        String tarMehtodName = proceedingJoinPoint.getSignature().getName();

        // 4.2.获取类对象
        Class tarClass = Class.forName(tarClassName);
        // 4.3.获取目标类中的所有方法
        Method[] methods = tarClass.getMethods();

        String operationType = "";
        String operationName = "";

        for(Method method : methods) {
            String methodName = method.getName();
            if(tarMehtodName.equals(methodName)) {
                // 这个时候虽然已经确定了目标方法没有问题，但是有可能会出现方法的重载
                // 还需要进一步判断
                // 4.4.获取目标方法的参数
                Class[] parameterTypes = method.getParameterTypes();
                if(parameterTypes.length == args.length) {
                    // 获取目标方法 完美，优秀 英俊！
                    operationType = method.getAnnotation(LoginAnnotation.class).opeationType();
                    operationName = method.getAnnotation(LoginAnnotation.class).opeationName();
                }
            }
        }

        loginLog.setOperationName(operationName);
        loginLog.setOperationType(operationType);

        iProjectService.addLoginLog(loginLog);

        return result;

    }

}
