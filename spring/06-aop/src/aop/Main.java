package aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import aop.dao.AccountDAO;
import aop.dao.MembershipDAO;

public class Main {

	/* AOP = Aspect Oriented Programming
	 * (do something before/after/during a given action)
	 * 
	 * Parts:
	 * 		- Aspects
	 * 		- Advice)
	 * 		- Join point
	 * 		- Pointcut
	 * 
	 * Frameworks: AspectJ & Spring AOP
	 */
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AccountDAO account_dao = context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO membership_dao = context.getBean("membershipDAO", MembershipDAO.class);
	
		account_dao.addAccount();
		membership_dao.addAccount();
		
		context.close();
	}
}
