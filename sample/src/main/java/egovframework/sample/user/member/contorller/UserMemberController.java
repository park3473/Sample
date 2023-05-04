package egovframework.sample.user.member.contorller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.system.util.SUtil;

import egovframework.sample.user.member.model.UserMemberVo;
import egovframework.sample.user.member.service.UserMemberService;

@Controller
public class UserMemberController {

	@Autowired
	UserMemberService userMemberService;
	
	@RequestMapping(value="/user/api/member/idCheck.do")
	public @ResponseBody ModelMap UserMemberIdCehck(@ModelAttribute("UserMemberVo")UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		String Boolean = userMemberService.getIdCheck(UserMemberVo); 
		
		model.put("IdCheck", Boolean);
		
		return model;
		
	}
	
	@RequestMapping(value="/user/mypage/view.do" , method = RequestMethod.GET)
	public ModelAndView UserMemberView(@ModelAttribute("UserMemberVo")UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response ) {
		
		ModelMap model = new ModelMap();
		
		String UserId = SUtil.getUserId(request);
		
		UserMemberVo.setMember_id(UserId);
		
		model = userMemberService.getMemberData(UserMemberVo);
		
		return new ModelAndView("user/mypage/view" , "model" , model);
		
	}
	
	@RequestMapping(value="/user/mypage/update.do" , method =  RequestMethod.GET)
	public ModelAndView UserMemberUpdateView(@ModelAttribute("UserMemberVo")UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		String UserId = SUtil.getUserId(request);
		
		UserMemberVo.setMember_id(UserId);
		
		model = userMemberService.getMemberData(UserMemberVo);
		
		return new ModelAndView("user/mypage/update" , "model" , model);
		
	}
	
	@RequestMapping(value="/user/mypage/update.do" , method = RequestMethod.POST)
	public void UserMemberUpdate(@ModelAttribute("UserMemberVo")UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		String UserId = SUtil.getUserId(request);
		
		UserMemberVo.setMember_id(UserId);
		
		userMemberService.setMemberData(UserMemberVo , "update");
		
	}
	
	@RequestMapping(value="/user/mypage/delete.do" , method = RequestMethod.POST)
	public void UserMemberDelete(@ModelAttribute("UserMemberVo")UserMemberVo UserMemberVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		String UserId = SUtil.getUserId(request);
		
		UserMemberVo.setMember_id(UserId);
		
		userMemberService.setMemberData(UserMemberVo , "delete");
		
		SUtil.AlertAndPageMove(response, "회원 정보가 삭제되었습니다.", "/index.do");
		
	}
	
	
}
