package egovframework.sample.file.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	
}
