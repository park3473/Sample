package egovframework.sample.menu.service;

import java.util.List;

import egovframework.sample.menu.model.MenuVo;

public interface MenuService {

	public List<?> getMenuList();

	public void setMenuData(MenuVo menuVo, String string);

}
