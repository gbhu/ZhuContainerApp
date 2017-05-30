package zhu.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import zhu.app.dao.CustomerServiceDAO;
import zhu.app.model.CustomerService;
import zhu.app.service.CustomerServiceService;

@Service("CustomerServiceService")
public class CustomerServiceServiceImpl implements CustomerServiceService {

	Logger logger = LoggerFactory.getLogger(CustomerServiceServiceImpl.class);

	@Resource
	private CustomerServiceDAO customerServiceDAO;

	@Override
	public int insertCustomerService(CustomerService customerService) throws Exception {
		return customerServiceDAO.insertCustomerService(customerService);
	}

	@Override
	public CustomerService findCustomerServiceById(int i) {
		return customerServiceDAO.findCustomerServiceById(i);
	}

	@Override
	public List<CustomerService> findAllCustomerService() {
		return customerServiceDAO.findAllCustomerService();
	}
}
