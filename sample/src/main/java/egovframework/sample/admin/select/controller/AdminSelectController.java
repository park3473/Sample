package egovframework.sample.admin.select.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.sample.admin.question.model.AdminQuestionVo;
import egovframework.sample.admin.select.model.AdminSelectVo;
import egovframework.sample.admin.select.service.AdminSelectService;

@Controller
public class AdminSelectController {

	@Autowired 
	AdminSelectService adminSelectService;
	
	@RequestMapping(value="/admin/select/list.do" , method = RequestMethod.GET)
	public ModelAndView AdminSelectList(@ModelAttribute("AdminSelectVo")AdminSelectVo AdminSelectVo , HttpServletRequest request , HttpServletResponse response) {
	
		String question_idx = request.getParameter("question_idx") != null ? request.getParameter("question_idx") : "";
		
		String select_type = request.getParameter("select_type") != null ? request.getParameter("select_type") : "";
		
		String select_val = request.getParameter("select_val") != null ? request.getParameter("select_val") : "";
		
		ModelMap model = new ModelMap();
		//해당 문제에 대한 답 리스트 가져오기
		AdminSelectVo.setQuestion_idx(question_idx);
		model = adminSelectService.getSelectAllList(AdminSelectVo);
		
		//문제 세팅 관련하여 가져오기
		AdminQuestionVo question = new AdminQuestionVo();
		
		question.setSelect_type(select_type);
		question.setIdx(question_idx);
		
		model.put("question", question);
		
		return new ModelAndView("admin/select/list" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/select/insert.do" , method = RequestMethod.POST)
	public void AdminSelectInsert(@ModelAttribute("AdminSelectVo")AdminSelectVo AdminSelectVo , MultipartHttpServletRequest request , HttpServletResponse response) {
		
		adminSelectService.setSelectData(AdminSelectVo , "insert");
		
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/select/view.do" , method = RequestMethod.POST)
	public ModelMap AdminSelectView(@ModelAttribute("AdminSelectVo")AdminSelectVo AdminSelectVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		model = adminSelectService.getSelectView(AdminSelectVo);
		
		return model;
		
	}
	
	@RequestMapping(value="/admin/select/update.do" , method = RequestMethod.POST)
	public void AdminSelectUpdate(@ModelAttribute("AdminSelectVo")AdminSelectVo AdminSelectVo , MultipartHttpServletRequest request , HttpServletResponse response) {
		
		adminSelectService.setSelectData(AdminSelectVo , "update");
		
	}
	
	@RequestMapping(value="/admin/select/delete.do" , method = RequestMethod.POST)
	public void AdminSelectDelete(@ModelAttribute("AdminSelectVo")AdminSelectVo AdminSelectVo , HttpServletRequest request , HttpServletResponse response) {
		
		adminSelectService.setSelectData(AdminSelectVo , "delete");
		
	}
	
	
}
