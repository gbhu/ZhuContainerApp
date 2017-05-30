package zhu.app.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * 下载filePath路径下的fileName文件
 * @author wu
 *
 */
public class FileDownloadUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileDownloadUtil.class);

	public static void fileDownload(HttpServletResponse response, String filePath, String fileName) {
		response.reset();

		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		// 2.设置文件头：最后一个参数是设置下载后的文件名
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		// 获取要下载的文件File对象(即源文件)
		File file = new File(filePath + fileName);
		//设置文件大小
		response.setHeader("Content-Length", "" + file.length());

		try {
			FileInputStream inputStream = new FileInputStream(file);
			// 创建输出流
			OutputStream outputStream = response.getOutputStream();
			// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = inputStream.read(buffer)) > 0) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				outputStream.write(buffer, 0, len);
			}
			// 关闭文件输入流
			inputStream.close();
			// 关闭输出流
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			logger.error("处理异常", e);
		}
	}

	public static void saveFileSomeWhere(XSSFWorkbook wb, File file) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			wb.write(out);
			out.flush();
		} catch (IOException e) {
			logger.error("导出数据错误", e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				logger.error("导出数据错误", e);
			}
		}
	}

	public static Object getCellValueByType(Cell cell){
		if(Cell.CELL_TYPE_STRING == cell.getCellType()){
			return cell.getStringCellValue();
		}else if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
			return cell.getNumericCellValue();
		}else {//公式
			return cell.getNumericCellValue();
		}
	}
}