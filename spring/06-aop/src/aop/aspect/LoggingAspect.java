package aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/* Summary:
 * 		Annotations:
 * 			- @Before | @Around | @After
 * 
 * 		Pointcut Expressions:
 * 			- execution(type return method)
 * 			- use of wildcard '*'. Can also be used within the method name
 * 			- use of FQN to be more specific
 * 			- () = method with no arguments
 * 			- (*) = method with one argument
 * 			- (..) = method with zero to 'n' arguments
 */
@Aspect
@Component
public class LoggingAspect {

	// Action before execution of 'public' 'void' 'addAccount'()
	@Before(value = "execution(public void addAccount())")
	public void beforeAddAccountAdvice() {
		System.out.println("-------------- Execution before public void addAccount() --------------");
		
	}
	
	// Action after execution of 'public' 'any' 'method starting with add'()
	// Only for the MembershipDAO class!
	// (use of wildcards)
	@After(value = "execution(public * aop.dao.MembershipDAO.add*(..))")
	public void afterAddAccountAdvice() {
		System.out.println("-------------- Execution after public * Membership::add*(..) --------------");
		
	}
}
