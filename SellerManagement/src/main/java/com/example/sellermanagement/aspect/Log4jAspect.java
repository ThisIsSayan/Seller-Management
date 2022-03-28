package com.example.sellermanagement.aspect;

import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Log4jAspect {
	private static Logger log = LogManager.getLogger(Log4jAspect.class);

	
	@Around("execution(* com.example.sellermanagement.controller.AppController.*(..))")
	public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
		String msg="";
		String methodname = joinPoint.getSignature().getName();
		msg="Entering the method " + methodname + " args==>" + Arrays.toString(joinPoint.getArgs());
		log.info(msg); 
		Object obj = null;
		
		obj = joinPoint.proceed();


		msg="Leaving the method " + methodname + " args==>" + Arrays.toString(joinPoint.getArgs());
		log.info(msg);

		return obj;
	}
	
}
