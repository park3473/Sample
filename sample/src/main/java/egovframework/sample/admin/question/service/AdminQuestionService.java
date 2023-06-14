package egovframework.sample.admin.question.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import egovframework.sample.admin.question.model.AdminQuestionVo;

public interface AdminQuestionService {

	public ModelMap getAllList(AdminQuestionVo adminQuestionVo);

	public void setQuestionData(AdminQuestionVo adminQuestionVo, String string);

	public String setQuestionAjaxData(AdminQuestionVo adminQuestionVo);
	
	public List<?> getTypeAllList();

	public ModelMap getQuestionData(AdminQuestionVo adminQuestionVo);

}
