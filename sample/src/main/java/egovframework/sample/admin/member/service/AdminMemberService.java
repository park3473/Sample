package egovframework.sample.admin.member.service;

import org.springframework.ui.ModelMap;

import egovframework.sample.admin.member.model.AdminMemberVo;

public interface AdminMemberService {

	public ModelMap getAllList(AdminMemberVo adminMemberVo);

	public void setMemberData(AdminMemberVo adminMemberVo, String string);

	public ModelMap getMemberData(AdminMemberVo adminMemberVo);

	public String getIdCheck(AdminMemberVo adminMemberVo);

}
