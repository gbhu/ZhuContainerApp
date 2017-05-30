package zhu.app.service;

import java.util.List;

import zhu.app.model.Account;
import zhu.app.model.CustomerService;


public interface CustomerServiceService {

	public int insertCustomerService(CustomerService customerService) throws Exception;

	public CustomerService findCustomerServiceById(int i);
	
	public List<CustomerService> findAllCustomerService();
}
