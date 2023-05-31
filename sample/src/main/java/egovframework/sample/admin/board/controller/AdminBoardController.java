package egovframework.sample.admin.board.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.commonj.ScheduledTimerListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.system.util.SUtil;

import egovframework.sample.admin.board.model.AdminBoardVo;
import egovframework.sample.admin.board.service.AdminBoardService;

@Controller
public class AdminBoardController {
	
	@Autowired
	AdminBoardService adminBoardService;
	
	
	@RequestMapping(value="/admin/board/list.do" , method = RequestMethod.GET)
	public ModelAndView AdminBoardList(@ModelAttribute("AdminBoardVo")AdminBoardVo AdminBoardVo , HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("PAGE : " + AdminBoardVo.getPAGE());
		System.out.println("ITEM_COUNT : " + AdminBoardVo.getITEM_COUNT());
		
		String PAGE = request.getParameter("PAGE") != null ? request
				.getParameter("PAGE") : "0";
		String ITEM_COUNT = request.getParameter("ITEM_COUNT") != null ? request
				.getParameter("ITEM_COUNT") : "10";
		
		AdminBoardVo.setPAGE(Integer.parseInt(PAGE));
		AdminBoardVo.setITEM_COUNT(Integer.parseInt(ITEM_COUNT));
		
		int pagelimit = AdminBoardVo.getPAGE() * AdminBoardVo.getITEM_COUNT();
		
		AdminBoardVo.setLIMIT(Integer.parseInt(ITEM_COUNT));
		AdminBoardVo.setOFFSET(pagelimit);
		
		ModelMap model = new ModelMap();
		model = adminBoardService.getAllList(AdminBoardVo);
		
		
		model.put("before", AdminBoardVo);
		
		return new ModelAndView("admin/board/list" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/board/insert.do" , method = RequestMethod.GET)
	public String AdminBoardInsertView(@ModelAttribute("AdminBoardVo")AdminBoardVo AdminBoardVo , HttpServletRequest request , HttpServletResponse response) {
		
		return "admin/board/insert";
		
	}
	
	@RequestMapping(value="/admin/board/insert.do" , method = RequestMethod.POST)
	public void AdminBoardInsert(@ModelAttribute("AdminBoardVo")AdminBoardVo AdminBoardVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminBoardService.setBoardInsert(AdminBoardVo);
		
		SUtil.AlertAndPageMove(response, "게시판이 등록되었습니다." , "/admin/board/list.do");
		
		
	}
	
	@RequestMapping(value="/admin/board/view.do" , method = RequestMethod.GET)
	public ModelAndView AdminBoardView(@ModelAttribute("AdminBoardVo")AdminBoardVo AdminBoardVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		model = adminBoardService.getBoardView(AdminBoardVo);
		
		return new ModelAndView("admin/board/view" , "model" , model);
	
	}
	
	@RequestMapping(value="/admin/board/update.do" , method = RequestMethod.GET)
	public String AdminBoardUpdateView(@ModelAttribute("AdminBoardVo")AdminBoardVo AdminBoardVo , HttpServletRequest request , HttpServletResponse response) {
		
		
		return "admin/board/update";
		
	}
	
	@RequestMapping(value="/admin/board/update.do" , method = RequestMethod.POST)
	public void AdminBoardUpdate(@ModelAttribute("AdminBoardVo")AdminBoardVo AdminBoardVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminBoardService.setBoardUpdate(AdminBoardVo);
		
		SUtil.AlertAndPageMove(response, "게시판이 수정되었습니다.", "/admin/board/list.do");
		
	}
	
	@RequestMapping(value="/admin/board/delete.do" , method = RequestMethod.POST)
	public void AdminBoardDelete(@ModelAttribute("AdminBoardVo")AdminBoardVo AdminBoardVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
	
		adminBoardService.setBoardDelete(AdminBoardVo);
		
		SUtil.AlertAndPageMove(response, "게시판이 삭제되었습니다." , "/admin/board/list.do" );
	
	}
	
	
}
