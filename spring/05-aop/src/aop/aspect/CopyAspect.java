package aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(30)
@Component
public class CopyAspect {

	// Copy
	@After(value="execution(public * aop.dummy_dao.MembershipDAO.add*(..))")
	public void copyAfterAddAccountAdvice(JoinPoint joinPoint) {
		System.out.println("-------------- (priority: 30) Execution after public * Membership::add*(..) --------------");
		System.out.println("Signature:");
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		System.out.println(signature);
	}
}
