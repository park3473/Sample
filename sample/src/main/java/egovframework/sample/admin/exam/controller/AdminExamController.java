package egovframework.sample.admin.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.sample.admin.exam.model.AdminExamVo;
import egovframework.sample.admin.exam.service.AdminExamService;

@Controller
public class AdminExamController {

	@Autowired
	AdminExamService adminExamService;

	@RequestMapping(value="/admin/exam/list.do" , method = RequestMethod.GET)
	public ModelAndView AdminExamListGet(@ModelAttribute("AdminExamVo")AdminExamVo AdminExamVo , HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("PAGE : " + AdminExamVo.getPAGE());
		System.out.println("ITEM_COUNT : " + AdminExamVo.getITEM_COUNT());
		
		ModelMap model = new ModelMap();
		
		
		
		return new ModelAndView("/" , "model" , model);
		
	}
	
}
