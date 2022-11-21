package egovframework.sample.user.member.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.user.member.model.UserMemberVo;

@Mapper("userMemberMapper")
public interface UserMemberMapper {

	int getUserLoginAllConfirm(UserMemberVo userMemberVo);

	int getUserLoginIdConfirm(UserMemberVo userMemberVo);

	UserMemberVo getUserOneAllInfo(UserMemberVo userMemberVo);


}
