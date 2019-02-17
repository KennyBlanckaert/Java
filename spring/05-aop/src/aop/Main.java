package aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import aop.dummy_dao.AccountDAO;
import aop.dummy_dao.MembershipDAO;
import aop.entities.Account;

public class Main {

	/* AOP = Aspect Oriented Programming
	 * (do something before/after/during a given action)
	 * 
	 * Parts:
	 * 		- Aspects : module of code for cross-cutting concerns
	 * 		- Advice : what to do & when to do
	 * 		- Join point : when to apply code during program
	 * 		- Pointcut : a predicate expression 
	 * 
	 * Frameworks: AspectJ & Spring AOP
	 */
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AccountDAO account_dao = context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO membership_dao = context.getBean("membershipDAO", MembershipDAO.class);
	
		Account account = new Account();
		account.setName("Kenny");
		
		account_dao.addAccount();
		System.out.println();
		
		account_dao.deleteAccount();
		System.out.println();
		
		membership_dao.addAccount(account);
		System.out.println();
		
		membership_dao.updateAccount(account);
		System.out.println();
		
		System.out.println("---------------- Check Postprocessing ----------------");
		System.out.println("Account level: " + account.getName() + " (" + account.getStatus() + ")");
		System.out.println();
		
		try { account_dao.changeAccount(); } catch (Exception exception) { /* message should be printed within advice */ }
		try { Thread.sleep(100); } catch (Exception exception) { /* because code after advice could be faster */ }
		System.out.println();
		
		account_dao.logOut(account);
		System.out.println();
		
		context.close();
	}
}
