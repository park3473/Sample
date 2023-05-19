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
		
		//재정렬 부분 Seq = 들어갈 번호 해당 번호보다 아래 것들 번호 하나씩 늘림 - 해당  seq가 0보다 높을때는 번호 늘리기 없을때는 전체 재정렬
		int Seq = 0;
		switch (type) {
		case "insert":
			
			Seq = Integer.parseInt(menuVo.getSeq());
			
			List<?> list = menuMapper.getMenuList();
			
			if(list.size() >= Seq) {
				
				System.out.println("아래것이 왜 오류가 날까 진짜 난 잘 모르겠어");
				
				menuMapper.setMenuReSeq(Seq);
			}
			
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
			//재정렬
			Seq = 0;
			menuMapper.setMenuReSeq(Seq);
			
			break;
		}
		
	}
}
