package aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import aop.entities.Account;

/* Summary:
 * 		Used for security - (audit) logging - transactions
 * 		Postprocessing with @AfterReturning
 * 
 * 		Advice types:
 * 			- @Before | @After | @Around | @AfterReturning | 
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
 */
@Aspect
@Order(10)
@Component
public class LoggingAspect {
	
	/* Pointcut declarations */
	
	@Pointcut(value="execution(public void addAccount())")
	private void publicVoidAddAccount() { }
	
	@Pointcut(value="execution(public void deleteAccount())")
	private void publicVoidDeleteAccount() { }
	
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
	
	// Advice after execution of 'public' 'any' 'updateAccount'(Account account)
	// Postprocessing example
	@AfterReturning(value="publicVoidUpdateAccount()")
	public void afterReturningUpdateAccountAdvice(JoinPoint joinPoint) {
		System.out.println("-------------- Execution afterReturning public void updateAccount(Account) --------------");
		
		Object[] args = joinPoint.getArgs();
		if (args[0] instanceof Account) {
			Account account = (Account) args[0];
			account.setStatus("Online");
		}
	}
}
