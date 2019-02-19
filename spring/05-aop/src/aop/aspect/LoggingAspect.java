package aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import aop.entities.Account;

// Summary below
@Aspect
@Order(10)
@Component
public class LoggingAspect {
	
	/* Pointcut declarations */
	
	@Pointcut(value="execution(public void addAccount())")
	private void publicVoidAddAccount() { }
	
	@Pointcut(value="execution(public void deleteAccount())")
	private void publicVoidDeleteAccount() { }
	
	@Pointcut(value="execution(public void changeAccount())")
	private void publicVoidChangeAccount() { }
	
	@Pointcut(value="execution(public aop.entities.Account logOut(aop.entities.Account))")
	private void publicAccountLogOut() { }
	
	@Pointcut(value="execution(public void updateAccount(aop.entities.Account))")
	private void publicVoidUpdateAccount() { }
	
	@Pointcut(value="publicVoidAddAccount() || publicVoidDeleteAccount()")
	private void addOrDelete() { }
	
	/* Advices */

	// Advice before execution of 'public' 'void' 'addAccount'() OR 'public' 'void' 'deleteAccount'()
	// (use of pointcut declaration)
	@Before(value="addOrDelete()")
	public void beforeAddAccountAdvice() {
		System.out.println("-------------- Execution before public void addAccount() OR public void deleteAccount() --------------");
		
	}
	
	// Advice after execution of 'public' 'any' 'method starting with add'()
	// Only for the MembershipDAO class!
	// (use of wildcards)
	@After(value="execution(public * aop.dummy_dao.MembershipDAO.add*(..))")
	public void afterAddAccountAdvice(JoinPoint joinPoint) {
		System.out.println("-------------- (priority: 10) Execution after public * Membership::add*(..) --------------");
		
		System.out.println("Signature:");
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		System.out.println(signature);
	}
	
	// Advice after returning of 'public' 'void' 'updateAccount'(Account account)
	// Postprocessing example
	@AfterReturning(value="publicVoidUpdateAccount()")
	public void afterReturningUpdateAccountAdvice(JoinPoint joinPoint) {
		System.out.println("-------------- Execution afterReturning public void updateAccount(Account) --------------");
		
		Object[] args = joinPoint.getArgs();
		Account account = (Account) args[0];
		account.setStatus("Online");
		
		// Note: 
		// when the monitored method has a return value, add the 'returning="..."' to the annotation
		// this copies the return value/object to the arguments 
	}
	
	// Advice after throwing of exception in 'public' 'void' 'changeAccount'()
	// Postprocessing example
	@AfterThrowing(value="publicVoidChangeAccount()", throwing="exception")
	public void afterThrowingChangeAccountAdvice(JoinPoint joinPoint, Throwable exception) {
		System.out.println("-------------- Execution afterThrowing public void ChangeAccount() --------------");
		System.err.println("Exception detected!");
	}
	
	// Around advice for 'public' 'Account' 'logOut'(Account account)
	// Proxy pattern: method is only executed within advice!!!
	@Around(value="publicAccountLogOut()")
	public void aroundLogOut(ProceedingJoinPoint preceedingJoinPoint) {
		System.out.println("-------------- Execution around public Account logOut(Account) --------------");
		
		try {
			Object[] args = preceedingJoinPoint.getArgs();
			Account account = (Account) args[0];
			if (account.getStatus().equalsIgnoreCase("online")) {
				
				long begin = System.currentTimeMillis();
				preceedingJoinPoint.proceed();
				Thread.sleep(123);
				long end = System.currentTimeMillis();
				long duration = (end - begin);
				System.out.println(account.getName() + " is now " + account.getStatus());
				System.out.println("Operation took " + duration + " milliseconds");
			}
			else {
				System.out.println("Account already logged out.");
			}
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

/* Summary:
 * 		Used for security - (audit) logging - transactions
 * 
 * 		Advice types:
 * 			- @Before | @After | @Around | @AfterReturning | @AfterThrowing
 * 
 * 		Pointcut expressions:
 * 			- @Pointcut
 * 			- execution(type return method)
 * 			- use of wildcard '*'. Can also be used within the method name
 * 			- use of FQN to be more specific
 * 			- () = method with no arguments
 * 			- (*) = method with one argument
 * 			- (..) = method with zero to 'n' arguments
 * 			- combinations with '&&', '||', '!' 
 * 
 * 		Order:
 * 			- @Order
 * 			- highest priority goes first
 * 			- negative numbers allowed
 * 		
 * 		Postprocessing by converting the Object arguments to the correct type
 */
