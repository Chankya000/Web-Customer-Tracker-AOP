package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//import com.sun.istack.logging.Logger;


@Aspect
@Component
public class CRMLoggingAspect {

	//setup loggger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	
	//setip point delaration
	@Pointcut("execution (* com.luv2code.springdemo.controller.*.*(..)")
	private void forControllerPackager() {
		
	}
	
	
	@Pointcut("execution (* com.luv2code.springdemo.service.*.*(..)")
	private void forServicePackage() {
		
	}
	
	
	@Pointcut("execution (* com.luv2code.springdemo.dao.*.*(..)")
	private void forDAOPackage() {
		
	}

	@Pointcut("forControllerPackager() || forServicePackage() || forDAOPackage() " )
	private void forAppFlow() {
		
	}
	
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJointPoint) {
		
		String theMethod = theJointPoint.getSignature().toShortString();
		myLogger.info("===> in @Before calling the method "+theMethod);
		
		//display the arguments to the method
		
		//get the arguments
		Object[] args =  theJointPoint.getArgs();
		//loop through and dispay args
		
		for(Object arg : args) {
			myLogger.info("===> argument "+arg);
		}
		
	}
	

	//add @fterReturning advice
	@AfterReturning(
			pointcut="forAppFlow",
			returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint,Object theResult) {
		//display the method we are returning from
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("===> in @AfterReturnig: from method "+theMethod);
		
		//display the data returned
		myLogger.info("===> result "+theResult);
	}
}
