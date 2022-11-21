package egovframework.sample.user.accept_log.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.user.accept_log.model.UserAcceptLogVo;
         
@Mapper("UserAcceptLogMapper")
public interface UserAcceptLogMapper {

	public void setLog(String clientIp);

	
}
