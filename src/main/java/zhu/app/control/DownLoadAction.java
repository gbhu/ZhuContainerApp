package zhu.app.control;

import com.alibaba.fastjson.JSON;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhu.app.core.model.ApiResponse;
import zhu.app.model.Account;
import zhu.app.model.CustomerService;
import zhu.app.service.CustomerServiceService;
import zhu.app.service.ITestService;
import zhu.app.service.ITestXmlService;
import zhu.app.utils.FileDownloadUtil;


/**
 * Created by lenovo
 * date 2017/5/12.
 */
@Controller
@RequestMapping("/file")
public class DownLoadAction extends BaseController{

    private Logger logger = Logger.getLogger(DownLoadAction.class);


    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public ApiResponse saveTemplate(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
            throws IOException {
        byte[] b = file.getBytes();
        Byte by[] = new Byte[b.length];
        if (b.length > 0) {
            for (int i = 0; i < b.length; i++) {
                by[i] = b[i];
            }
        }
        String param = request.getParameter("document");
        Map<String, Object> map = JSON.parseObject(param, Map.class);
        return null;
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String param = request.getParameter("model");
        Map<String, Object> map = JSON.parseObject(param, Map.class);
        String id = MapUtils.getString(map, "id");
        String fileName = MapUtils.getString(map, "fileName");

        logger.info("======================== id :" + id + "== fileName:" + fileName);
     /*   List<com.gi.object.model.Document> document = documentService.findByProperty("id", id);
        Byte[] docContent = document.get(0).getDocumentContent();
        fileName = URLEncoder.encode(fileName, "UTF-8") + ".doc";
        File file = new File(fileName);
        FileOutputStream out = new FileOutputStream(file);
        byte by[] = new byte[docContent.length];
        for (int i = 0; i < docContent.length; i++) {
            by[i] = docContent[i];
            out.write(by[i]);
        }*/

        FileDownloadUtil.fileDownload(response, "", fileName);
       // file.delete();
    }


}
