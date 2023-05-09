package egovframework.sample.file.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.sample.file.model.FileVo;
import egovframework.sample.file.service.FileService;

@Controller
public class FileContorller {

	@Autowired
	FileService fileService;
	
	@RequestMapping(value="/api/fileList.do" , method = RequestMethod.POST)
	@ResponseBody
	public ModelMap FileList(@ModelAttribute("FileVo")FileVo FileVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		List<?> filelist = fileService.getFileList(FileVo);
		
		model.put("filelist", filelist);
		
		return model;
		
	}
	
	@RequestMapping(value="/api/fileDelete.do" , method = RequestMethod.POST)
	public void FileDelete(@ModelAttribute("FileVo")FileVo FileVo , HttpServletRequest request , HttpServletResponse response) {
		
		FileVo.setType("delete");
		
		fileService.setFileUpdate(FileVo);
		
	}
	
	@RequestMapping(value = {"/fileDown.do"}, method = RequestMethod.GET)
	public void filedown(HttpServletRequest request, HttpServletResponse response) throws IOException {

	String path = (String) (request.getParameter("path") != null ? request.getParameter("path") : "");

	String drv = request.getRealPath("");
	//drv = drv.substring(0, drv.length()) + "./resources"+request.getContextPath()+"/upload/notices/";
	drv = drv.substring(0, drv.length());

	File file = new File(drv+path);
	System.out.println("drv+path : " + drv+path);
	        
	response.setContentType("application/octet-stream");
	        response.setContentLength((int) file.length()); 
	        
	        String header = request.getHeader("User-Agent");

	        String browser = "MSIE";
	        if (header.indexOf("MSIE") > -1) {
	        browser = "MSIE";
	        } else if (header.indexOf("Chrome") > -1) {
	        browser = "Chrome";
	        } else if (header.indexOf("Opera") > -1) {
	        browser = "Opera";
	        }
	        
	        String realFileName = file.getName();
	        if(realFileName.length() > 17)
	        {
	        realFileName = realFileName.substring(13, realFileName.length());
	        }
	        
	        String encodedFilename = null;
	        if (browser.equals("MSIE")) {
	            encodedFilename = URLEncoder.encode(realFileName, "UTF-8")
	                    .replaceAll("\\+", "%20");
	        } else if (browser.equals("Firefox")) {
	            encodedFilename =

	         "\"" + new String(realFileName.getBytes("UTF-8"), "8859_1") + "\"";
	        } else if (browser.equals("Opera")) {
	            encodedFilename =

	         "\"" + new String(realFileName.getBytes("UTF-8"), "8859_1") + "\"";
	        } else if (browser.equals("Chrome")) {
	            StringBuffer sb = new StringBuffer();
	            for (int i = 0; i < realFileName.length(); i++) {
	                char c = realFileName.charAt(i);
	                if (c > '~') {
	                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
	                } else {
	                    sb.append(c);
	                }
	            }
	            encodedFilename = sb.toString();
	        } else {
	            throw new RuntimeException("Not supported browser");
	        }

	        
	        
	        response.setHeader("Content-Disposition","attachment;filename=\"" +encodedFilename+"\";");
	        response.setHeader("Content-Transter-Encoding", "binary");
	        
	        OutputStream out = response.getOutputStream();
	        FileInputStream fis = null;
	        
	        try {
	            fis = new FileInputStream(file);
	            
	            FileCopyUtils.copy(fis, out);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (fis != null) {
	                try {
	                    fis.close();
	                } catch (IOException ioe) {
	                    ioe.printStackTrace();
	                }
	            }
	            
	            out.flush();
	        }

	}
	
}
