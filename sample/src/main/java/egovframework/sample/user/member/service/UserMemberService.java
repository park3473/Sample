package egovframework.sample.user.member.service;

import org.springframework.ui.ModelMap;

import egovframework.sample.user.member.model.UserMemberVo;

public interface UserMemberService {

	public int getUserLoginConfirm(UserMemberVo userMemberVo);

	public UserMemberVo getMemberOneAllInfo(UserMemberVo userMemberVo);

	public ModelMap getMemberData(UserMemberVo userMemberVo);

	public void setMemberData(UserMemberVo userMemberVo, String string);

	public String getIdCheck(UserMemberVo userMemberVo);

	public String getSearchId(UserMemberVo userMemberVo);

	public String getMemberCheck(UserMemberVo userMemberVo);

	public void setMemberPwChange(UserMemberVo userMemberVo);


}
