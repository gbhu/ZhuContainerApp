package zhu.app.dao;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

import zhu.app.model.CustomerService;

@Repository("customerServiceXmlDAO")
public interface CustomerServiceXmlDAO {



	@CacheEvict(value = {"indexCache"},allEntries = true,beforeInvocation = true)
	public int insertCustomerService(CustomerService customerService);

	@Cacheable(value = "indexCache",key = "'findCustomerServiceById'+#id")
	public CustomerService findCustomerServiceById(int id);

	@Cacheable(value = "indexCache",key = "'findAllCustomerService'")
	public List<CustomerService> findAllCustomerService();
}
