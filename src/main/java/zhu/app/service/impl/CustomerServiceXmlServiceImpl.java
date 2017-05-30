package zhu.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import zhu.app.dao.CustomerServiceDAO;
import zhu.app.dao.CustomerServiceXmlDAO;
import zhu.app.model.CustomerService;
import zhu.app.service.CustomerServiceService;
import zhu.app.service.CustomerServiceXmlService;

@Service("CustomerServiceXmlService")
public class CustomerServiceXmlServiceImpl implements CustomerServiceXmlService {

	Logger logger = LoggerFactory.getLogger(CustomerServiceXmlServiceImpl.class);

	@Resource
	private CustomerServiceXmlDAO customerServiceXmlDAO;

	@Override
	public int insertCustomerService(CustomerService customerService) throws Exception {
		return customerServiceXmlDAO.insertCustomerService(customerService);
	}

	@Override
	public CustomerService findCustomerServiceById(int i) {
		return customerServiceXmlDAO.findCustomerServiceById(i);
	}

	@Override
	public List<CustomerService> findAllCustomerService() {
		return customerServiceXmlDAO.findAllCustomerService();
	}
}
