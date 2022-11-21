package egovframework.sample.user.member.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.sample.user.member.model.UserMemberVo;
import egovframework.sample.user.member.service.UserMemberService;

@Service("userMemberService")
@Transactional
public class UserMemberServiceImpl implements UserMemberService {
	
	@Resource(name="userMemberMapper")
	private UserMemberMapper userMemberMapper;

	
	//로그인 확인 결과 Confirm = 0 전체 불일치 , Confirm = 1 로그인 성공 , Confirm = 2 아이디만 성공  , Confirm = 3 로그인 이상 | 로그인은 가능하게)
	@Override
	public int getUserLoginConfirm(UserMemberVo userMemberVo) {
		
		int Confirm = userMemberMapper.getUserLoginAllConfirm(userMemberVo);
		
		if(Confirm == 0) {
			
			//아이디 일치하는지 확인
			Confirm = userMemberMapper.getUserLoginIdConfirm(userMemberVo);
			if(Confirm == 1) {
				
				Confirm = 2;
				
			}else if(Confirm == 0){
				
				Confirm = 0;
				
			}else {
				
				Confirm = 3;
				
			}
			
		}else if(Confirm == 1) {
			
			System.out.println("정상적으로 로그인");
			Confirm = 1;
			
		}else {
			System.out.println("나오는 갯수 (중복 아이디) : " + Confirm);
			Confirm = 3;
		}
		
		return Confirm;
	}


	@Override
	public UserMemberVo getMemberOneAllInfo(UserMemberVo userMemberVo) {
		
		UserMemberVo userMemberVo2 = userMemberMapper.getUserOneAllInfo(userMemberVo);
		
		return userMemberVo2;
	}


}
