package zhu.app.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import  org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhu.app.core.model.ApiResponse;
import zhu.app.model.Account;
import zhu.app.service.ITestService;
import zhu.app.service.ITestXmlService;


/**
 * Created by lenovo
 * date 2017/5/12.
 */
@Controller
@RequestMapping("/test")
public class TestAction extends BaseController{

    private Logger logger = Logger.getLogger(TestAction.class);
    @Autowired
    private ITestService testService;

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
        List<Account> accountList = this.testService.findAccountsById(3);

        logger.info(accountList);
       return super.callSuccess(accountList);
    }
    @ResponseBody
    @RequestMapping(value = "/testXml", method = RequestMethod.GET, produces = { "text/json;charset=UTF-8" })
    public String testXml(HttpServletRequest request, HttpServletResponse response) {
        List<Account> accountList = this.testXmlService.findAccountsById(3);
        logger.info(accountList);
        return super.callSuccess(accountList);
    }
}
