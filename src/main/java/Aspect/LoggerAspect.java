package Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
    static int requestCounter;

    @Before("logJP")
    public static void logCounter(){
        requestCounter++;
        System.out.println("Request counter is at " +requestCounter);
    }
    @Pointcut("within(API..*)")
    private void logJP(){

    }
}
