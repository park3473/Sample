package egovframework.sample.admin.exam.service;

import org.springframework.ui.ModelMap;

import egovframework.sample.admin.exam.model.AdminExamVo;

public interface AdminExamService {

	public ModelMap getAllList(AdminExamVo adminExamVo);

	public void setAdminExamData(AdminExamVo adminExamVo, String string);

}
