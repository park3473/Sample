package egovframework.sample.admin.subpage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.system.util.SUtil;

import egovframework.sample.admin.subpage.service.AdminSubPageService;
import egovframework.sample.admin.subpage.model.AdminSubPageVo;

@Controller
public class AdminSubPageController {

	@Autowired
	AdminSubPageService adminSubPageService;
	
	@RequestMapping(value="/admin/subpage/list.do" , method = RequestMethod.GET)
	public ModelAndView AdminSubPageList(@ModelAttribute("AdminSubpageVo")AdminSubPageVo AdminSubpageVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		model = adminSubPageService.getAllList(AdminSubpageVo);

		model.put("before", AdminSubpageVo);
		
		return new ModelAndView("/admin/subpage/list" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/subpage/view.do" , method = RequestMethod.GET)
	public ModelAndView AdminSubPageView(@ModelAttribute("AdminSubpageVo")AdminSubPageVo AdminSubpageVo , HttpServletRequest request , HttpServletResponse response){
		
		ModelMap model = new ModelMap();
		
		model = adminSubPageService.getViewData(AdminSubpageVo);
		
		return new ModelAndView("/admin/subpage/view" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/subpage/update.do" , method = RequestMethod.POST)
	public void AdminSubPageUpdate(@ModelAttribute("AdminSubpageVo")AdminSubPageVo AdminSubpageVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminSubPageService.setAdminSubPageData(AdminSubpageVo , "update");
		SUtil.AlertAndPageMove(response, "페이지가 저장되었습니다.", "/admin/subpage/list.do");
		
	}
	
	@RequestMapping(value="/admin/subpage/insert.do" , method = RequestMethod.GET)
	public String AdminSubPageInsertPage(@ModelAttribute("AdminSubpageVo")AdminSubPageVo AdminSubpageVo , HttpServletRequest request , HttpServletResponse response) {
		
		return "admin/subpage/insert";
		
	}
	
	@RequestMapping(value="/admin/subpage/insert.do" , method = RequestMethod.POST)
	public void AdminSubPageInsert(@ModelAttribute("AdminSubpageVo")AdminSubPageVo AdminSubpageVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminSubPageService.setAdminSubPageData(AdminSubpageVo , "insert");
		SUtil.AlertAndPageMove(response, "페이지가 저장되었습니다.", "/admin/subpage/list.do");
		
	}
	
	@RequestMapping(value="/admin/subpage/delete.do" , method = RequestMethod.POST)
	public void AdminSubPageDelete(@ModelAttribute("AdminSubpageVo")AdminSubPageVo AdminSubpageVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminSubPageService.setAdminSubPageData(AdminSubpageVo , "delete");
		SUtil.AlertAndPageMove(response, "페이지가 삭제되었습니다.", "/admin/subpage/list.do");
		
	}
	
}
