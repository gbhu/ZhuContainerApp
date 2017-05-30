/**
 * Created by lenovo on 2017/5/28.
 */
var AjaxObject={
    getData:function (url,param,requestType,callback) {
        $.ajax({
                   url:url,
                   type:requestType, //GET
                   async:true,    //或false,是否异步
                   data:param,
                   timeout:5000,    //超时时间
                   dataType:'json',    //返回的数据格式
                   beforeSend:function(xhr){
                       console.log('发送前')
                   },
                   success:function(data,textStatus,jqXHR){
                       console.log(data);
                       callback!=undefined?callback(data):"未定义访问成功回调函数！";
                   },
                   error:function(xhr,textStatus){
                       console.log('错误')
                       console.log(xhr)
                       console.log(textStatus)
                   },
                   complete:function(){
                       console.log('结束')
                   }
               })
    }

};


