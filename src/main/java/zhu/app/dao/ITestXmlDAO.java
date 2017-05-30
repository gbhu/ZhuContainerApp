package zhu.app.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

import zhu.app.model.Account;

@Repository("testXmlDAO")
public interface ITestXmlDAO {

	@CacheEvict(value = {"indexCache"},allEntries = true,beforeInvocation = true)
	public int insertAccount(Account account);

	@Cacheable(value = "indexCache",key = "'findAccountById'+#id")
	public Account findAccountById(int id);

	@Cacheable(value = "indexCache",key = "'findAccounts'")
	public List<Account> findAccounts();
}
