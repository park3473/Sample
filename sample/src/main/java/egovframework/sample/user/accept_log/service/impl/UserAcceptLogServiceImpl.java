package egovframework.sample.user.accept_log.service.impl;
 


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import egovframework.sample.manager.accept_log.service.UserAcceptLogService;
import egovframework.sample.user.accept_log.model.UserAcceptLogVo;


@Service("UserAcceptLogService")
@Transactional
public class UserAcceptLogServiceImpl implements UserAcceptLogService {

	
	@Resource(name = "UserAcceptLogMapper")
	private UserAcceptLogMapper UserAcceptLogMapper;
	
	protected Log log = LogFactory.getLog(UserAcceptLogServiceImpl.class);

	@Override
	public void setLog(String clientIp) {
		// TODO Auto-generated method stub\
		System.out.println("여기까지 오나??");
		UserAcceptLogMapper.setLog(clientIp);
		return;
	}
		
}
