package egovframework.sample.user.config.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.sample.user.config.service.UserConfigService;


@Service("userConfigService")
@Transactional
public class UserConfigServiceImpl implements UserConfigService {

	@Resource(name = "userConfigMapper")
	private UserConfigMapper userConfigMapper;

	@Override
	public String getParking() {
		
		String Parking = "";
		
		Parking = userConfigMapper.getParking();
		
		return Parking;
	}
	
	
	
}
