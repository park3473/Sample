package egovframework.sample.admin.exam.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.admin.exam.model.AdminExamVo;

@Mapper("adminExamMapper")
public interface AdminExamMapper {

	public List<?> getAllList(AdminExamVo adminExamVo);

	public int getAllListCnt(AdminExamVo adminExamVo);

	public void setAdminExamDataInsert(AdminExamVo adminExamVo);

	public void setAdminExamDataUpdate(AdminExamVo adminExamVo);

	public void setAdminExamDataDelete(AdminExamVo adminExamVo);

	public AdminExamVo getExamView(AdminExamVo adminExamVo);

}
