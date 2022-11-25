package egovframework.sample.user.board;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.system.util.SUtil;

import egovframework.sample.user.board.model.UserBoardDataVo;
import egovframework.sample.user.board.model.UserBoardReplyVo;
import egovframework.sample.user.board.service.UserBoardDataService;
import egovframework.sample.user.board.service.UserBoardService;

@Controller
public class UserBoardDataController {

	private static final Logger Logger = LoggerFactory.getLogger(UserBoardDataController.class);
	
	/* 
	 * 기초 설정
	 * 
	 * @ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response
	 * */
	
	@Autowired
	UserBoardService userBoardService;
	
	@Autowired
	UserBoardDataService userBoardDataService;
	
	
	/*board_data 부분*/
	
	@RequestMapping(value="/user/board_data/list.do" , method = RequestMethod.GET)
	public ModelAndView BoardDataAllList(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("PAGE : " + UserBoardDataVo.getPAGE());
		System.out.println("ITEM_COUNT : " + UserBoardDataVo.getITEM_COUNT());
		
		String PAGE = request.getParameter("PAGE") != null ? request
				.getParameter("PAGE") : "0";
		String ITEM_COUNT = request.getParameter("ITEM_COUNT") != null ? request
				.getParameter("ITEM_COUNT") : "10";
		
		UserBoardDataVo.setPAGE(Integer.parseInt(PAGE));
		UserBoardDataVo.setITEM_COUNT(Integer.parseInt(ITEM_COUNT));
		
		int pagelimit = UserBoardDataVo.getPAGE() * UserBoardDataVo.getITEM_COUNT();
		
		UserBoardDataVo.setLIMIT(Integer.parseInt(ITEM_COUNT));
		UserBoardDataVo.setOFFSET(pagelimit);
		
		System.out.println("board_idx : " + request.getParameter("board_idx"));
		UserBoardDataVo.setBoard_idx(request.getParameter("board_idx"));
		
		ModelMap model = new ModelMap();
		model = userBoardDataService.getAllList(UserBoardDataVo);
		
		return new ModelAndView("/user/board_data/list" , "model" , model);
		
	}
	
	@RequestMapping(value="/user/board_data/insert.do" , method = RequestMethod.GET)
	public ModelAndView BoardDataInsertView(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		System.out.println("BOARD_IDX : " + UserBoardDataVo.getBoard_idx());
		
		String Board_idx = UserBoardDataVo.getBoard_idx();
		
		model = userBoardService.getBoard(Board_idx);
		
		return new ModelAndView("user/board_data/insert" , "model" , model);
	}
	
	@RequestMapping(value="/user/board_data/insert.do" , method = RequestMethod.POST)
	public void BoardDataInsertData(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		System.out.println("Board_data_idx : " + UserBoardDataVo.getIdx());
		System.out.println("Board_idx : " + UserBoardDataVo.getBoard_idx());
		
		String Board_idx = UserBoardDataVo.getBoard_idx();
		
		userBoardDataService.setBoardData(UserBoardDataVo , "insert");
		
		SUtil.AlertAndPageMove(response, "게시글 등록이 완료되었습니다.", "/user/board_data/list.do?=" + Board_idx);
		
	}
	
	@RequestMapping(value="/user/board_data/view.do" , method = RequestMethod.GET)
	public ModelAndView BoardDataView(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		model = userBoardDataService.getBoardData(UserBoardDataVo);
		
		return new ModelAndView("user/board_data/view" , "model" , model);
	}
	
	@RequestMapping(value="/user/board_data/update.do" , method = RequestMethod.POST)
	public void BoardDataUpdate(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();;
		
		String member_id = (String) session.getAttribute("UserId");
		
		System.out.println("Id : " + member_id);
		
		UserBoardDataVo.setMember_id(member_id);
		
		userBoardDataService.setBoardData(UserBoardDataVo, "update");
		
		System.out.println("Board_data_idx : " + UserBoardDataVo.getIdx());
		System.out.println("Board_idx : " + UserBoardDataVo.getBoard_idx());
		
		String Board_idx = UserBoardDataVo.getBoard_idx();
		
		SUtil.AlertAndPageMove(response, "게시글 수정이 완료되었습니다.", "/user/board_data/list.do?=" + Board_idx);
		
	}
	
	
	@RequestMapping(value="/user/board_data/delete.do" , method = RequestMethod.POST)
	public void BoardDataDelet(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		userBoardDataService.DelBoardData(UserBoardDataVo);
		
		System.out.println("Board_data_idx : " + UserBoardDataVo.getIdx());
		System.out.println("Board_idx : " + UserBoardDataVo.getBoard_idx());
		
		String Board_idx = UserBoardDataVo.getBoard_idx();
		
		SUtil.AlertAndPageMove(response, "게시글 삭제가 완료되었습니다.", "/user/board_data/list.do?=" + Board_idx);
		
	}
	
	/*board_data_reply 부분*/
	
	@RequestMapping(value="/user/board_reply/api/list.do")
	public @ResponseBody Map<String, Object> apireplyList(@ModelAttribute("UserBoardReplyVo")UserBoardReplyVo UserBoardReplyVo , HttpServletRequest request , HttpServletResponse response) throws Exception{
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		String borad_data_idx = request.getParameter("board_data_idx");
		
		UserBoardReplyVo.setBoard_data_idx(borad_data_idx);
		
		List<Map<String, Object>> list = (List<Map<String, Object>>) userBoardDataService.getReplyAllList(UserBoardReplyVo);
		
		System.out.println(list);
		
		returnMap.put("list", list);
		
		return returnMap;
	}
	
}
