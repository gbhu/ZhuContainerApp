package zhu.app.service;



import java.util.List;

import zhu.app.model.Account;

public interface ITestXmlService {

	
	public int insertAccount(Account account) throws Exception;

	public Account findAccountById(int i);
	
	public List<Account> findAccountsById(int i);
}
