package egovframework.sample.admin.question.serviceimpl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.admin.question.model.AdminQuestionVo;

@Mapper("adminQuestionMapper")
public interface AdminQuestionMapper {

	public List<?> getAllList(AdminQuestionVo adminQuestionVo);

	public void setQuestionDataInsert(AdminQuestionVo adminQuestionVo);

	public void setQuestionDataUpdate(AdminQuestionVo adminQuestionVo);

	public void setQuestionDataDeleteAll(AdminQuestionVo adminQuestionVo);

	public void setQuestionDataDeleteOne(AdminQuestionVo adminQuestionVo);

}
