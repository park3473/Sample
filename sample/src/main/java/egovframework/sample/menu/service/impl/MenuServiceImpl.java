package egovframework.sample.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.sample.menu.service.MenuService;

@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

	@Resource(name = "MenuMapper")
	private MenuMapper menuMapper;

	@Override
	public List<?> getMenuList() {
		List<?> list = menuMapper.getMenuList();
		return list;
	}
}
