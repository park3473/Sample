package egovframework.sample.user.board.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.system.util.SUtil;

import egovframework.sample.user.board.model.UserBoardVo;
import egovframework.sample.user.board.service.UserBoardService;

@Controller
public class UserBoardController {

	private static final Logger Logger = LoggerFactory.getLogger(UserBoardController.class);
	
	/*
	 * 기초 설정
	 * 
	 * @ModelAttribute("UserBoardVo")UserBoardVo UserBoardVo , HttpServletRequest request , HttpServletResponse response
	 * */
	
	@Autowired
	UserBoardService userBoardService;
	
	@RequestMapping(value="/user/board/list.do" , method = RequestMethod.GET)
	public ModelAndView BoardAllList(@ModelAttribute("UserBoardVo")UserBoardVo UserBoardVo , HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("PAGE : " + UserBoardVo.getPAGE());
		System.out.println("ITEM_COUNT" + UserBoardVo.getITEM_COUNT());
		
		String PAGE = request.getParameter("PAGE") != null ? request
				.getParameter("PAGE") : "0";
		String ITEM_COUNT = request.getParameter("ITEM_COUNT") != null ? request
				.getParameter("ITEM_COUNT") : "10";
		
		UserBoardVo.setPAGE(Integer.parseInt(PAGE));
		UserBoardVo.setITEM_COUNT(Integer.parseInt(ITEM_COUNT));
		
		int pagelimit = UserBoardVo.getPAGE() * UserBoardVo.getITEM_COUNT();
		
		UserBoardVo.setLIMIT(Integer.parseInt(ITEM_COUNT));
		UserBoardVo.setOFFSET(pagelimit);
		
		ModelMap model = new ModelMap();
		model = userBoardService.getAllList(UserBoardVo);
		
		return new ModelAndView("user/board/list" , "model" , model);
		
	}
	
	@RequestMapping(value = "/user/board/insert.do" , method = RequestMethod.GET)
	public String BoardInsertView(@ModelAttribute("UserBoardVo")UserBoardVo UserBoardVo , HttpServletRequest request , HttpServletResponse response) {
		return "user/board/insert";
	}
	
	@RequestMapping(value="/user/board/insert.do" , method = RequestMethod.POST)
	public void BoardInsertData(@ModelAttribute("UserBoardVo")UserBoardVo UserBoardVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		userBoardService.setBoard(UserBoardVo , "insert");
		
		SUtil.AlertAndPageMove(response, "게시판 등록되었습니다.", "/user/board/list.do");
		
		
	}
	
	@RequestMapping(value="/user/board/view.do" , method = RequestMethod.GET)
	public ModelAndView BoardView(@ModelAttribute("UserBoardVo")UserBoardVo UserBoardVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		model = userBoardService.getBoard(UserBoardVo);
		
		return new ModelAndView("user/board/view" , "model" , model);
		
		
	}
	
	@RequestMapping(value="/user/board/update.do" , method = RequestMethod.GET)
	public ModelAndView BoardUpdateView(@ModelAttribute("UserBoardVo")UserBoardVo UserBoardVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		model = userBoardService.getBoard(UserBoardVo);
		
		return new ModelAndView("user/board/update" , "model" , model);
		
		
	}
	
	@RequestMapping(value="/user/board/update.do" , method = RequestMethod.POST)
	public void BoardUpdateData(@ModelAttribute("UserBoardVo")UserBoardVo UserBoardVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		userBoardService.setBoard(UserBoardVo , "update");
		
		SUtil.AlertAndPageMove(response, "게시판 수정되었습니다.", "/user/board/list.do");
		
	}
	
	
	/* User에는 현재 필요없는 기능이라 주석
	 * @RequestMapping(value="/user/board/delete.do" , method = RequestMethod.GET)
	 * public void BoardDeleteData(@ModelAttribute("UserBoardVo")UserBoardVo
	 * UserBoardVo , HttpServletRequest request , HttpServletResponse response) {
	 * userBoardService.delBoardData(UserBoardVo); }
	 */
}
