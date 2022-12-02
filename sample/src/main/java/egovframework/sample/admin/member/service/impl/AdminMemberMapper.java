package egovframework.sample.admin.member.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.admin.member.model.AdminMemberVo;

@Mapper("adminMemberMapper")
public interface AdminMemberMapper {

	public List<?> getAllList(AdminMemberVo adminMemberVo);

	public int getAllListCount(AdminMemberVo adminMemberVo);

	public void setMemberDataDelete(AdminMemberVo adminMemberVo);

	public void setMemberDataUpdate(AdminMemberVo adminMemberVo);

	public void setMemberDataInsert(AdminMemberVo adminMemberVo);

	public AdminMemberVo getMemberData(AdminMemberVo adminMemberVo);

	public int getIdCheck(AdminMemberVo adminMemberVo);

}
