package egovframework.sample.user.member.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.system.util.SUtil;

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


		@Override
		public ModelMap getMemberData(UserMemberVo userMemberVo) {
			
			ModelMap model = new ModelMap();
			
			UserMemberVo userVo = new UserMemberVo();
			
			userVo = userMemberMapper.getUserMemberData(userMemberVo);
			
			model.put("view", userVo);
			
			return model;
		}


		@Override
		public void setMemberData(UserMemberVo userMemberVo, String type) {
			
			switch (type) {
			case "insert":
				userMemberMapper.setMemberDataInsert(userMemberVo);
				break;
			case "update":
				userMemberMapper.setMemberDataUpdate(userMemberVo);
				break;
			case "delete":
				userMemberMapper.setMemberDataDelete(userMemberVo);
				break;
			default:
				System.out.println("type 오류");
				break;
			}
			
		}


		@Override
		public String getIdCheck(UserMemberVo userMemberVo) {
			
			String Result = "";
			
			int IdCheck = userMemberMapper.getIdCheck(userMemberVo);
			
			if(IdCheck == 0) {
				Result = "true";
			}else {
				Result = "false";
			}
			
			return Result;
		}


		@Override
		public String getSearchId(UserMemberVo userMemberVo) {
			
			String result = "";
			
			result = userMemberMapper.getIdSearch(userMemberVo);
			
			return result;
		}


		@Override
		public String getMemberCheck(UserMemberVo userMemberVo) {
			
			int Cnt = userMemberMapper.getMemberCheck(userMemberVo);
			
			if(Cnt == 1) {
				return "true";
			}else if(Cnt == 0){
				return "false";
			}else {
				return "error";
			}
			
		}


		@Override
		public void setMemberPwChange(UserMemberVo userMemberVo) {
			
			String PW = userMemberVo.getPhone();
			
			PW = PW.substring(PW.length()-4, PW.length());
			
			PW = SUtil.getSHA256(PW);
			
			userMemberVo.setPassword(PW);
			
			userMemberMapper.setMemberPwChange(userMemberVo);
			
		}


}
