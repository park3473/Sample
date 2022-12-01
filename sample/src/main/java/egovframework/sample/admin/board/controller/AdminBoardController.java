package egovframework.sample.admin.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.sample.admin.board.model.AdminBoardVo;
import egovframework.sample.admin.board.service.AdminBoardService;

@Controller
public class AdminBoardController {
	
	@Autowired
	AdminBoardService adminBoardService;
	
	
	@RequestMapping(value="/admin/board/list.do" , method = RequestMethod.GET)
	public ModelAndView AdminBoardList(@ModelAttribute("AdminBoardVo")AdminBoardVo AdminBoardVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		
		return new ModelAndView("admin/board/list" , "model" , model);
		
	}
	

}
