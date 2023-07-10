package egovframework.sample.admin.question.controller;

import java.io.IOException;
import java.util.List;

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

import egovframework.sample.admin.question.model.AdminQuestionVo;
import egovframework.sample.admin.question.service.AdminQuestionService;

@Controller
public class AdminQuestionController {

	@Autowired
	AdminQuestionService adminQuestionService;
	
	@RequestMapping(value="/admin/question/list.do" , method = RequestMethod.GET)
	public ModelAndView AdminQuestionListGet(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("PAGE : " + AdminQuestionVo.getPAGE());
		System.out.println("ITEM_COUNT : " + AdminQuestionVo.getITEM_COUNT());
		
		String PAGE = request.getParameter("PAGE") != null ? request
				.getParameter("PAGE") : "0";
		String ITEM_COUNT = request.getParameter("ITEM_COUNT") != null ? request
				.getParameter("ITEM_COUNT") : "10";
		
		AdminQuestionVo.setPAGE(Integer.parseInt(PAGE));
		AdminQuestionVo.setITEM_COUNT(Integer.parseInt(ITEM_COUNT));
		
		int pagelimit = AdminQuestionVo.getPAGE() * AdminQuestionVo.getITEM_COUNT();
		
		AdminQuestionVo.setLIMIT(Integer.parseInt(ITEM_COUNT));
		AdminQuestionVo.setOFFSET(pagelimit);
		
		ModelMap model = new ModelMap();
		
		model = adminQuestionService.getAllList(AdminQuestionVo);
		
		model.put("before", AdminQuestionVo);
		
		return new ModelAndView("admin/question/list" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/question/select_list.do" , method = RequestMethod.GET)
	public ModelAndView AdminQuestionListPost(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("PAGE : " + AdminQuestionVo.getPAGE());
		System.out.println("ITEM_COUNT : " + AdminQuestionVo.getITEM_COUNT());
		
		String PAGE = request.getParameter("PAGE") != null ? request
				.getParameter("PAGE") : "0";
		String ITEM_COUNT = request.getParameter("ITEM_COUNT") != null ? request
				.getParameter("ITEM_COUNT") : "10";
		
		AdminQuestionVo.setPAGE(Integer.parseInt(PAGE));
		AdminQuestionVo.setITEM_COUNT(Integer.parseInt(ITEM_COUNT));
		
		int pagelimit = AdminQuestionVo.getPAGE() * AdminQuestionVo.getITEM_COUNT();
		
		AdminQuestionVo.setLIMIT(Integer.parseInt(ITEM_COUNT));
		AdminQuestionVo.setOFFSET(pagelimit);
		
		ModelMap model = new ModelMap();
		
		model = adminQuestionService.getAllList(AdminQuestionVo);
		
		model.put("before", AdminQuestionVo);
		
		return new ModelAndView("admin/question/select" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/question/insert.do" , method = RequestMethod.GET)
	public ModelAndView AdminQuestionInsertGet(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) {
		
		String exam_idx = request.getParameter("exam_idx") != null ? request
				.getParameter("exam_idx") : "false";
		
		ModelMap model = new ModelMap();
		
		List<?> TypeList = adminQuestionService.getTypeAllList();
		
		model.put("TypeList", TypeList);
		
		model.put("exam_idx", exam_idx);
		
		return new ModelAndView("admin/question/insert" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/question/insert.do" , method = RequestMethod.POST)
	public void AdminQuestionInsertPost(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminQuestionService.setQuestionData(AdminQuestionVo , "insert");
		
		SUtil.AlertAndPageMove(response, "해당 문항이 등록되었습니다.", "/admin/question/list.do");
		
	}
	
	/*
	 * ajax 활용 insert
	 */
	@RequestMapping(value="/admin/question/AjaxInsert.do" , method = RequestMethod.POST , produces = "application/json; charset=utf8")
	@ResponseBody
	public String AdminQuestionAjaxInsertPost(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		String Question_idx = adminQuestionService.setQuestionAjaxData(AdminQuestionVo);
		
		return Question_idx;
		
	}
	
	@RequestMapping(value="/admin/question/view.do" , method = RequestMethod.GET)
	public ModelAndView AdminQuestionViewGet(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		model = adminQuestionService.getQuestionData(AdminQuestionVo);
		
		return new ModelAndView("admin/question/view" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/question/update.do" , method = RequestMethod.GET)
	public ModelAndView AdminQuestionUpdateGet(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) {
		
		String exam_idx = request.getParameter("exam_idx") != null ? request
				.getParameter("exam_idx") : "false";
		
		ModelMap model = new ModelMap();
		
		model = adminQuestionService.getQuestionData(AdminQuestionVo);
		
		List<?> TypeList = adminQuestionService.getTypeAllList();
		
		model.put("TypeList", TypeList);
		
		model.put("exam_idx", exam_idx);
		
		return new ModelAndView("admin/question/update" , "model" , model);
		
	}
	
	/*
	 * ajax 활용 update
	 */
	@RequestMapping(value="/admin/question/AjaxUpdate.do" , method = RequestMethod.POST , produces = "application/json; charset=utf8")
	@ResponseBody
	public void AdminQuestionAjaxUpdatePost(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminQuestionService.setQuestionData(AdminQuestionVo , "update");
		
	}
	
	@RequestMapping(value="/admin/question/update.do" , method = RequestMethod.POST)
	public void AdminQuestionUpdatePost(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminQuestionService.setQuestionData(AdminQuestionVo , "update");
		
		SUtil.AlertAndPageMove(response, "해당 문항이 수정되었습니다.", "/admin/question/list.do");
		
	}
	
	@RequestMapping(value="/admin/question/delete.do" , method = RequestMethod.POST)
	public void AdminQuestionDeletePost(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminQuestionService.setQuestionData(AdminQuestionVo , "delete");
		
		SUtil.AlertAndPageMove(response, "해당 문항이 삭제되었습니다.", "/admin/question/list.do");
		
	}
	
	
}
