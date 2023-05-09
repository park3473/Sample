package egovframework.admin.ckeditor.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller	
public class CkeditorController {
	
	protected Log log = LogFactory.getLog(CkeditorController.class);
	
	@RequestMapping(value = "/ckeditor/file_upload.do", method = RequestMethod.POST)
	@ResponseBody
	public String communityImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) 
	{

	    OutputStream out = null;
	    PrintWriter printWriter = null;
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    String callback = request.getParameter("CKEditorFuncNum");
	    String fileName = "";
	    try{
	    	
	        fileName = upload.getOriginalFilename();
	        byte[] bytes = upload.getBytes();
	        
	        String drv = request.getRealPath("");
			
			drv = drv.substring(0, drv.length()) + "./resources"+request.getContextPath()+"/upload/ckeditor/";
			
			 File desti = new File(drv);
		  	 if(!desti.exists())
			 {
				desti.mkdirs(); 
			 }
			
			String inDate   = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
			String inTime   = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
			
			fileName = inDate + inTime + fileName;
			
	        out = new FileOutputStream(new File(drv+fileName));
	        out.write(bytes);
	       
	        if (log.isDebugEnabled()) {
	            log.debug(" Request drv \t:  " + drv);
	            log.debug(" Request filename \t:  " + fileName);
	            log.debug(" Request callback \t:  " + callback);
		    }

	    }catch(IOException e){
	        e.printStackTrace();

	    } finally {
	    }
	    return "/resources"+request.getContextPath()+"/upload/ckeditor/"+fileName + "";
	    //return "{\"uploaded\":1, \"url\":\"" + "http://localhost:8080/base/resources"+request.getContextPath()+"/upload/notices/"+fileName + "\"}";
	}
	
}
