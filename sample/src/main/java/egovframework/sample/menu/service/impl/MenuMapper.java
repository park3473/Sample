package egovframework.sample.menu.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MenuMapper")
public interface MenuMapper {

	List<?> getMenuList();

}
