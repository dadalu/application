package com.wxc.spring.aop;

import com.wxc.spring.aop.annotation.IdempotenceAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author admin
 */
@Slf4j
@Component
public class IdempotenceAnnotationImpl {
    @Autowired(required = false)
    private RedisTemplate<String, Object> redisTemplate;
    @Pointcut("@annotation(com.wxc.spring.aop.annotation.IdempotenceAnnotation)")
    public void idempotence() {

    }
    @Around("idempotence()")
    public Object redisLock(ProceedingJoinPoint joinPoint) throws Throwable {
        /*MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        StringBuffer lockKey = new StringBuffer();
        IdempotenceAnnotation idempotenceAnnotation = method.getAnnotation(IdempotenceAnnotation.class);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(idempotenceAnnotation.type()==1){
            JwtUser jwtUser=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            lockKey.append(jwtUser.getUsername());
        }else {
            //使用ip
            lockKey.append(IpUtils.ipAddress(request));
        }
        lockKey.append("_");
        //方法名称
        lockKey.append(method.getName());
        lockKey.append("_idempotence");

        log.info("redis分布式锁Key:"+lockKey.toString());
        //创建redis锁对象
        RedisLock redisLock=new RedisLock(redisTemplate,lockKey.toString(),false,idempotenceAnnotation.expiration());
        Object result;
        //获取锁
        if(redisLock.lockNoRetry()){
            try{
                //执行方法
                result=joinPoint.proceed();
            } finally{
                //释放锁
                redisLock.unlock();
            }
        }else {
            throw new ObjectException(250,"请勿重复提交!");
        }*/
        return null;
    }
}
