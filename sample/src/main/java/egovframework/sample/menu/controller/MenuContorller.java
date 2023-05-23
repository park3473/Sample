package egovframework.sample.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.sample.menu.service.MenuService;
import egovframework.sample.menu.model.MenuVo;

@Controller
public class MenuContorller {

	@Autowired
	MenuService menuService;
	
	
	@RequestMapping(value="/admin/menu/list.do" , method = RequestMethod.GET)
	public ModelAndView AdminMenuAllList(@ModelAttribute("MenuVo")MenuVo MenuVo , HttpServletRequest request , HttpServletResponse response) {
		
		
		ModelMap model = new ModelMap();
		
		List<?> list = menuService.getMenuList(MenuVo);
		
		model.put("list", list);
		
		return new ModelAndView("admin/menu/list" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/menu/insert.do" , method = RequestMethod.POST)
	public void AdminMenuInsert(@ModelAttribute("MenuVo")MenuVo MenuVo , HttpServletRequest request , HttpServletResponse response) {
		
		menuService.setMenuData(MenuVo , "insert");
		
	}
	
	@RequestMapping(value="/admin/menu/update.do" , method = RequestMethod.POST)
	public void AdminMenuUpdate(@ModelAttribute("MenuVo")MenuVo MenuVo , HttpServletRequest request , HttpServletResponse response) {
		
		menuService.setMenuData(MenuVo , "update");
		
	}
	
	@RequestMapping(value="/admin/menu/Allupdate.do" , method = RequestMethod.POST)
	public void AdminMenuAllUpdate(@ModelAttribute("MenuVo")MenuVo MenuVo , HttpServletRequest request , HttpServletResponse response) {
		
		menuService.setMenuData(MenuVo , "update");
		
	}
	
	@RequestMapping(value="/admin/menu/delete.do" , method = RequestMethod.POST)
	public void AdminMenuDelete(@ModelAttribute("MenuVo")MenuVo MenuVo , HttpServletRequest request , HttpServletResponse response) {
		
		menuService.setMenuData(MenuVo , "delete");
		
	}
	
	
}
