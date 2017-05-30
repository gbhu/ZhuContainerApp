package zhu.app.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhu.app.model.Account;
import zhu.app.model.CustomerService;
import zhu.app.service.CustomerServiceService;
import zhu.app.service.ITestService;
import zhu.app.service.ITestXmlService;


/**
 * Created by lenovo
 * date 2017/5/12.
 */
@Controller
@RequestMapping("/index")
public class TestAction extends BaseController{

    private Logger logger = Logger.getLogger(TestAction.class);
    @Autowired
    private ITestService testService;
    @Autowired
    private CustomerServiceService customerServiceService;
    @Autowired
    private ITestXmlService testXmlService;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  String helloWorld(){
        return "hello world";
    }


    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces ={ "text/json;charset=UTF-8" })
    public String test(HttpServletRequest request, HttpServletResponse response) {
        List<Account> accountList = testService.findAccountsById(3);

        logger.info(accountList);
       return super.callSuccess(accountList);
    }
    @ResponseBody
    @RequestMapping(value = "/testXml", method = RequestMethod.GET, produces = { "text/json;charset=UTF-8" })
    public String testXml(HttpServletRequest request, HttpServletResponse response) {
        List<Account> accountList = testXmlService.findAccounts();
        logger.info(accountList);
        return super.callSuccess(accountList);
    }
    @ResponseBody
    @RequestMapping(value = "/testXmlobj", method = RequestMethod.GET, produces = { "text/json;charset=UTF-8" })
    public String testXmlobj(HttpServletRequest request, HttpServletResponse response) {
        Account account= testXmlService.findAccountById(1);
        logger.info(account);
        return super.callSuccess(account);
    }
    @ResponseBody
    @RequestMapping(value = "/findCustomerServiceById", method = RequestMethod.GET, produces = { "text/json;charset=UTF-8" })
    public String findCustomerServiceById(HttpServletRequest request, HttpServletResponse response) {
        CustomerService customerService = customerServiceService.findCustomerServiceById(1);
        logger.info(customerService);
        return super.callSuccess(customerService);
    }
    @ResponseBody
    @RequestMapping(value = "/findAllCustomerService", method = RequestMethod.GET, produces = { "text/json;charset=UTF-8" })
    public String findAllCustomerService(HttpServletRequest request, HttpServletResponse response) {
        List<CustomerService> customerServices = customerServiceService.findAllCustomerService();
        logger.info(customerServices);
        return super.callSuccess(customerServices);
    }
}
