package egovframework.sample.user.board;

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

import egovframework.sample.user.board.model.UserBoardVo;
import egovframework.sample.user.board.service.UserBoardService;

@Controller
public class UserBoardController {

	private static final Logger Logger = LoggerFactory.getLogger(UserBoardController.class);
	
	@Autowired
	UserBoardService userBoardService;
	
	@RequestMapping(value="/user/board/list.do" , method = RequestMethod.GET)
	public ModelAndView BoardAllList(@ModelAttribute("UserBoardVo")UserBoardVo UserBoardVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		model = userBoardService.getAllList(UserBoardVo);
		
		return new ModelAndView("user/board/list" , "model" , model);
		
	}
	
}
