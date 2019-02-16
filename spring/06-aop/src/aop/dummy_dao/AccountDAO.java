package aop.dummy_dao;

import org.springframework.stereotype.Component;

import aop.entities.Account;

@Component
public class AccountDAO {

	public void addAccount() {
		System.out.println(getClass() + ": Calling public void addAccount()");
	}
	
	public void deleteAccount() {
		System.out.println(getClass() + ": Calling public void deleteAccount()");
	}
}
