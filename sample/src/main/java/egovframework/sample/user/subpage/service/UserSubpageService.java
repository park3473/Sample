package egovframework.sample.user.subpage.service;

import org.springframework.ui.ModelMap;

import egovframework.sample.user.subpage.model.UserSubPageVo;

public interface UserSubpageService {

	public ModelMap getSubPageData(UserSubPageVo userSubPageVo);
	
}
