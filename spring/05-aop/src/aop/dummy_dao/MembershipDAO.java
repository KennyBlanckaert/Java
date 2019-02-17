package aop.dummy_dao;

import org.springframework.stereotype.Component;

import aop.entities.Account;

@Component
public class MembershipDAO {

	public String addAccount(Account account) {
		System.out.println(getClass() + ": Calling String addAccount(Account account)");
		return null;
	}
	
	public void updateAccount(Account account) {
		System.out.println(getClass() + ": Calling public void updateAccount(Account account)");
	}
}
