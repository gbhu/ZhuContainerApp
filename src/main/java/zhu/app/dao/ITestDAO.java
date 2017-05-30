package zhu.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import org.mybatis.caches.ehcache.LoggingEhcache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import zhu.app.model.Account;

@Repository("testDAO")
public interface ITestDAO {

	String INSERT_ACCOUT = "insert into account (name,passwod,account) values (#{name},#{passwod},#{account})";
	
	String FIND_ACCOUNT_BY_ID = "select " +
			" id as id," +
			" passwod as passwod," +
			"account as account" +
			" from account " +
			" where " +
			" id = #{id}";
	
	String FIND_ACCOUNTS_BY_ID = "select " +
			" id as id," +
			" name as name," +
			" passwod as passwod," +
			"account as account" +
			" from account " ;
	

	@Insert(INSERT_ACCOUT)
	@CacheEvict(value = {"indexCache"},allEntries = true,beforeInvocation = true)
	public int insertAccount(Account account);

	@Select(FIND_ACCOUNT_BY_ID)
	@Cacheable(value = "indexCache",key = "'getAccountById'+#id")
	public Account getAccountById(int id);

	@Select(FIND_ACCOUNTS_BY_ID)
	@Cacheable(value = "indexCache",key = "'findAccountsById'+#id")
	public List<Account> findAccountsById(int id); 
}
