/**
 * Created by lenovo on 2017/5/28.
 */
$(document).ready(function(){

    var listModel=[];//列表

    var contactUs=[];//联系我们
    var downLoadDocumentList=[];//下载文档
    var init=function () {
        AjaxObject.getData('/index/findAllCustomerService',{},'GET',function (data) {
            contactUs=data.body;

            var html="<p>"+"QQ群：462694081"+"</p>";
            for (var i=0;i<contactUs.length;i++){
                html=html+"<p>"+"联系人："+contactUs[i].serviceName+"</p>"+"<p>"+"QQ："+contactUs[i].serviceQq+"</p>"+"<p>"+"电话："+contactUs[i].servicePhone+"</p>";
                html=html+"<br>";
            }
            $("#contactUs").append(html);
        });
    };
    init();

});

