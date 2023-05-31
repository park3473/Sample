package egovframework.sample.admin.member.contorller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.system.util.SUtil;

import egovframework.sample.admin.member.model.AdminMemberVo;
import egovframework.sample.admin.member.service.AdminMemberService;

@Controller
public class AdminMemberController {

	@Autowired
	AdminMemberService adminMemberService;
	
	
	@RequestMapping(value="/admin/member/list.do" , method = RequestMethod.GET)
	public ModelAndView AdminMemberList(@ModelAttribute("AdminMemberVo")AdminMemberVo AdminMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("PAGE : " + AdminMemberVo.getPAGE());
		System.out.println("ITEM_COUNT : " + AdminMemberVo.getITEM_COUNT());
		
		String PAGE = request.getParameter("PAGE") != null ? request
				.getParameter("PAGE") : "0";
		String ITEM_COUNT = request.getParameter("ITEM_COUNT") != null ? request
				.getParameter("ITEM_COUNT") : "10";
		
		AdminMemberVo.setPAGE(Integer.parseInt(PAGE));
		AdminMemberVo.setITEM_COUNT(Integer.parseInt(ITEM_COUNT));
		
		int pagelimit = AdminMemberVo.getPAGE() * AdminMemberVo.getITEM_COUNT();
		
		AdminMemberVo.setLIMIT(Integer.parseInt(ITEM_COUNT));
		AdminMemberVo.setOFFSET(pagelimit);
		
		ModelMap model = new ModelMap();
		
		model = adminMemberService.getAllList(AdminMemberVo);
		
		model.put("before", AdminMemberVo);
		
		return new ModelAndView("admin/member/list" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/member/insert.do" , method = RequestMethod.GET)
	public String AdminMemberInsertView(@ModelAttribute("AdminMemberVo")AdminMemberVo AdminMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		return "admin/member/insert";
	}
	
	@RequestMapping(value="/admin/member/insert.do" , method = RequestMethod.POST)
	public void AdminMemberDataInsert(@ModelAttribute("AdminMemberVo")AdminMemberVo AdminMemberVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		System.out.println("기초 비밀번호 = " + AdminMemberVo.getPassword());
		
		String pwd = SUtil.getSHA256(AdminMemberVo.getPassword());
		
		AdminMemberVo.setPassword(pwd);
		
		adminMemberService.setMemberData(AdminMemberVo , "insert");
	
		SUtil.AlertAndPageMove(response, "신규 회원이 등록되었습니다." , "/admin/member/list.do");
		
	}
	
	@RequestMapping(value="/admin/member/view.do" , method = RequestMethod.GET)
	public ModelAndView AdminMemberDataView(@ModelAttribute("AdminMemberVo")AdminMemberVo AdminMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		model = adminMemberService.getMemberData(AdminMemberVo);
		
		return new ModelAndView("admin/member/view" , "model" , model);
	}
	
	@RequestMapping(value="/admin/member/update.do" , method = RequestMethod.GET)
	public ModelAndView AdminMemberUpdateView(@ModelAttribute("AdminMemberVo")AdminMemberVo AdminMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		model = adminMemberService.getMemberData(AdminMemberVo);
		
		return new ModelAndView("admin/member/update" , "model" , model);
	}
	
	@RequestMapping(value="/admin/member/update.do" , method = RequestMethod.POST)
	public void AdminMemberUpdate(@ModelAttribute("AdminMemberVo")AdminMemberVo AdminMemberVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		System.out.println("기초 비밀번호 : " + AdminMemberVo.getPassword());
		
		if(!AdminMemberVo.getPassword().equals("")) {
			
			String pwd = SUtil.getSHA256(AdminMemberVo.getPassword());
			
			AdminMemberVo.setPassword(pwd);
			
		}
		
		adminMemberService.setMemberData(AdminMemberVo , "update");
		
		SUtil.AlertAndPageMove(response, "회원이 수정되었습니다." , "/admin/member/list.do");
		
	}
	
	@RequestMapping(value="/admin/member/delete.do" , method = RequestMethod.POST)
	public void AdminMemberDelete(@ModelAttribute("AdminMemberVo")AdminMemberVo AdminMemberVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminMemberService.setMemberData(AdminMemberVo , "delete");
		
		SUtil.AlertAndPageMove(response, "회원이 삭제되었습니다." , "/admin/member/list.do");
		
	}
	
	@RequestMapping(value="/admin/api/member/idCheck.do")
	public @ResponseBody ModelMap AdminMemberIdCheck(@ModelAttribute("AdminMemberVo")AdminMemberVo AdminMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		String Boolean = adminMemberService.getIdCheck(AdminMemberVo);
		
		model.put("IdCheck", Boolean);
		
		return model;
		
	}
	
	@RequestMapping(value="/admin/api/member/view.do")
	public @ResponseBody ModelMap apiMemberView(@ModelAttribute("AdminMemberVo")AdminMemberVo AdminMemberVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap returnMap = new ModelMap();
	
		returnMap = adminMemberService.getMemberData(AdminMemberVo);
		
		return returnMap;
		
	}
	
	
	
}
