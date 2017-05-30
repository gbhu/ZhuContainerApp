package zhu.app.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

import zhu.app.model.Account;
import zhu.app.model.CustomerService;

@Repository("customerServiceDAO")
public interface CustomerServiceDAO {

	String findCustomerServiceById = "select  " +
			"id ,serviceId,serviceName, serviceQq,"
			+ "servicePhone ,serviceNick from " +
			"CustomerService where id = #{id}";
	
	String findAllCustomerService = "select  " +
			"id ,serviceId,serviceName, serviceQq,servicePhone ,serviceNick" +
			" from CustomerService";
	String insertCustomerService = "insert into CustomerService (serviceId,serviceName,serviceQq,servicePhone,serviceNick) values (#{serviceId},#{serviceName},#{serviceQq},#{servicePhone})";

	
	@Insert(insertCustomerService)
	@CacheEvict(value = {"indexCache"},allEntries = true,beforeInvocation = true)
	public int insertCustomerService(CustomerService customerService);

	@Select(findCustomerServiceById)
	@Cacheable(value = "indexCache",key = "'findCustomerServiceById'+#id")
	public CustomerService findCustomerServiceById(int id);

	@Select(findAllCustomerService)
	@Cacheable(value = "indexCache",key = "'findAllCustomerService'")
	public List<CustomerService> findAllCustomerService();
}
