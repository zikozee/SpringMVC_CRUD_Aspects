package com.zikozee.SpringMVC.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger
	private Logger myLogger  = Logger.getLogger(getClass().getName());
	
	// setup pointcut declaration
	@Pointcut("execution(* com.zikozee.SpringMVC.controller.*.*(..))")
	private void forControllerPackage() {}
	
	// do the same for service and dao
	@Pointcut("execution(* com.zikozee.SpringMVC.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.zikozee.SpringMVC.dao.*.*(..))")
	private void forDaoPackage() {}
	
	//pointcut combo
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {//theJoinPoint gives us metadata about the method call
		
		//display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====>> in @Before: caling method: " + theMethod);
		
		// display the arguments to the method
		
		// get the arguments
		Object[] args = theJoinPoint.getArgs();
 		
		//loop through and display args
		for(Object tempArg : args) {
			myLogger.info("====>> argument: " + tempArg);
		}
	}
	
	// add @AfterReturning advice
	@AfterReturning(pointcut="forAppFlow()", returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		//display method we are returning from
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====>> in @AfterReturning: from method: " + theMethod);
		
		//display data returned
		myLogger.info("====>> result: " + theResult);
	}
	//<% response.sendRedirect("customer/list"); %>
//	@Around("forAppFlow()")
//	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
//		
//		// print out method we are advising on
//		String method = theProceedingJoinPoint.getSignature().toShortString();
//		System.out.println("\n=====>>>> Executing @Around on method: " + method);
//
//		// get begin timeStamp
//		long begin = System.currentTimeMillis();
//
//		// now, let's execute the method
//		Object result = theProceedingJoinPoint.proceed();
//
//		// get end timeStamp
//		long end = System.currentTimeMillis();
//
//		// compute duration and display it
//		long duration = end - begin;
//		System.out.println("\n====> Duration: " + duration / 1000.0 + " seconds");
//
//		return result;
//	}
//	
//	@After("forAppFlow()") //works regardless of success or failure of the target object
//	public void afterFInallyAccountFindAccountsAdvice(JoinPoint theJoinPoint) {// remember it runs before AfterThrowing
//
//		//print out which method we are advising on
//		String method = theJoinPoint.getSignature().toShortString();
//		System.out.println("\n=====>>>> Executing @After (finally) on method: " + method);
//	}
//	
//	//@AfterThrowing(pointcut="forAppFlow()", throwing="theExc")
//	@AfterThrowing(pointcut="forAppFlow()", throwing="theExc")
//	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
//		
//		//print out which method we are advising on
//		String method = theJoinPoint.getSignature().toShortString();
//		System.out.println("\n=====>>>> Executing @AfterThrowing on method: " + method);
//		
//		//log the exception
//		System.out.println("\n=====>>>> The exception is: " + theExc);
//
//	}
	
}
