package egovframework.sample.admin.question.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.admin.question.model.AdminQuestionVo;

@Mapper("adminQuestionMapper")
public interface AdminQuestionMapper {

	public List<?> getAllList(AdminQuestionVo adminQuestionVo);

	public void setQuestionDataInsert(AdminQuestionVo adminQuestionVo);

	public void setQuestionDataUpdate(AdminQuestionVo adminQuestionVo);

	public void setQuestionDataDelete(AdminQuestionVo adminQuestionVo);

	public List<?> getTypeAllList();

	public int getAllListCnt(AdminQuestionVo adminQuestionVo);

	public AdminQuestionVo getQuestionData(AdminQuestionVo adminQuestionVo);

	public void setQuestionDataAjaxInsert(AdminQuestionVo adminQuestionVo);

}
