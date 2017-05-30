package zhu.app.service;

import java.util.List;

import zhu.app.model.CustomerService;


public interface CustomerServiceXmlService {

	public int insertCustomerService(CustomerService customerService) throws Exception;

	public CustomerService findCustomerServiceById(int i);
	
	public List<CustomerService> findAllCustomerService();
}
