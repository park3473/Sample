package egovframework.sample.user.subpage.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.user.subpage.model.UserSubPageVo;

@Mapper("userSubPageMapper")
public interface UserSubPageMapper {

	public UserSubPageVo getSubPageData(UserSubPageVo userSubPageVo);
	
}
