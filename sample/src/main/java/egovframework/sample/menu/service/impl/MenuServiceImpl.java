package egovframework.sample.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.sample.menu.model.MenuVo;
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

	@Override
	public void setMenuData(MenuVo menuVo, String type) {
		
		
		switch (type) {
		case "insert":
			menuMapper.setMenuInsertData(menuVo);
			break;
		case "update":
			menuMapper.setMenuUpdateData(menuVo);
			break;	
		case "delete":
			
			if(menuVo.getDepth().equals("0")) {
				//상위 메뉴 삭제
				menuMapper.setMenuDeleteData(menuVo);
				//하위 메뉴 삭제
				menuMapper.setMenuDeleteList(menuVo);
			}else {
				menuMapper.setMenuDeleteData(menuVo);
			}
			
			//메뉴 순서 재정렬 (title , seq)
			menuMapper.setMenuTitleReOrder(menuVo);
			menuMapper.setMenuSeqReOrder(menuVo);
			
			break;
		}
		
	}
}
