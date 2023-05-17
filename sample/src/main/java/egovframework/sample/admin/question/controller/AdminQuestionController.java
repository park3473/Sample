package egovframework.sample.admin.question.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		ModelMap model = new ModelMap();
		
		model = adminQuestionService.getAllList(AdminQuestionVo);
		
		return new ModelAndView("admin/question/list" , "model" , model);
		
	}
	
	@RequestMapping(value="/admin/question/insert.do" , method = RequestMethod.POST)
	public void AdminQuestionInsertPost(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminQuestionService.setQuestionData(AdminQuestionVo , "insert");
		
		SUtil.AlertAndPageMove(response, "해당 문항이 등록되었습니다.", "/admin/question/list.do?exam_idx="+AdminQuestionVo.getExam_idx());
		
	}
	
	@RequestMapping(value="/admin/question/update.do" , method = RequestMethod.POST)
	public void AdminQuestionUpdatePost(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminQuestionService.setQuestionData(AdminQuestionVo , "update");
		
		SUtil.AlertAndPageMove(response, "해당 문항이 수정되었습니다.", "/admin/question/list.do?exam_idx="+AdminQuestionVo.getExam_idx());
		
	}
	
	@RequestMapping(value="/admin/question/delete.do" , method = RequestMethod.POST)
	public void AdminQuestionDeletePost(@ModelAttribute("AdminQuestionVo")AdminQuestionVo AdminQuestionVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminQuestionService.setQuestionData(AdminQuestionVo , "delete_one");
		
		SUtil.AlertAndPageMove(response, "해당 문항이 삭제되었습니다.", "/admin/question/list.do?exam_idx="+AdminQuestionVo.getExam_idx());
		
	}
	
}
