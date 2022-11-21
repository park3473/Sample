package egovframework.sample.user.member.service;

import egovframework.sample.user.member.model.UserMemberVo;

public interface UserMemberService {

	int getUserLoginConfirm(UserMemberVo userMemberVo);

	UserMemberVo getMemberOneAllInfo(UserMemberVo userMemberVo);


}
