package egovframework.sample.user.config.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userConfigMapper")
public interface UserConfigMapper {

	public String getParking();

}
