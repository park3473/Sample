package egovframework.sample.admin.question.service;

import org.springframework.ui.ModelMap;

import egovframework.sample.admin.question.model.AdminQuestionVo;

public interface AdminQuestionService {

	public ModelMap getAllList(AdminQuestionVo adminQuestionVo);

	public void setQuestionData(AdminQuestionVo adminQuestionVo, String string);

}
