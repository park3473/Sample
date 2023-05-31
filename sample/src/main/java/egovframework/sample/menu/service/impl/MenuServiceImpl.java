package egovframework.sample.menu.service.impl;

import java.util.HashMap;
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
	public List<?> getMenuList(MenuVo menuVo) {
		List<?> list = menuMapper.getMenuList(menuVo);
		return list;
	}

	@Override
	public void setMenuData(MenuVo menuVo, String type) {
		
		//재정렬 부분 Seq = 들어갈 번호 해당 번호보다 아래 것들 번호 하나씩 늘림 - 해당  seq가 0보다 높을때는 번호 늘리기 없을때는 전체 재정렬
		int Seq = 0;
		switch (type) {
		case "insert":
			
			Seq = Integer.parseInt(menuVo.getSeq());
			
			int MenuCnt = menuMapper.getMenuListCnt(menuVo);
			
			if(Seq > MenuCnt) {
				//새로 값 추가한 seq 가 list 맨 밑일경우
				menuMapper.setMenuInsertData(menuVo);
				
			}else {
				/*새로 값 추가한 seq 가 list 중간일 경우
				 * 1. 해당 데이터 보다 seq 높은 값들 리스트 가져오기
				 * 2. 해당 가져온 리스트 들 seq +1 해서 업데이트
				 * 3. 새로운 값 추가
				*/
				
				List<?> list = menuMapper.getMenuList(menuVo);
				
				for(int i = 0; i < list.size(); i++) {
					
					System.out.println("i : " + i);
					HashMap Map = (HashMap) list.get(i);
					MenuVo vo = new MenuVo();
					
					vo.setIdx(Map.get("idx")+"");
					
					System.out.println("idx : " + vo.getIdx());
					
					int SeqNum = Integer.parseInt(Map.get("seq")+ "");
					SeqNum += 1;
					
					vo.setSeq(Integer.toString(SeqNum));
					
					System.out.println("seq : " + vo.getSeq());
					
					
					menuMapper.setMenuUpdateData(vo);
					
				}
				
				
				menuMapper.setMenuInsertData(menuVo);
				
			}
			break;
		case "update":
			menuMapper.setMenuUpdateData(menuVo);
			break;	
			
		case "delete":
			
			if(menuVo.getDepth().equals("0")) {
				
				/*
				 * 1. 삭제 하려는 상위 메뉴 번호 가져오기
				 * 2. 삭제 진헹
				 * 3. 삭제 후 삭제 하려는 상위 메뉴 번호 보다 큰 번호 리스트 가져오기
				 * 4. 가져온 리스트 중 0번에 저장된 seq의 값 확인
				 * 5. 가져온 seq 값 - 삭제하려는 상위 seq 하여 차이값 얻기
				 * 6. 해당 리스트 들 차이 값 만큼 빼고 업데이트
				 */
				
				int DelSeq = Integer.parseInt(menuVo.getSeq());
				
				//상위 메뉴 삭제
				menuMapper.setMenuDeleteData(menuVo);
				//하위 메뉴 삭제
				menuMapper.setMenuDeleteList(menuVo);
				//해당 번호보다 큰 리스트 가져오기
				List<?> list = menuMapper.getMenuList(menuVo);
				HashMap Map = (HashMap) list.get(0);
				int Diff = Integer.parseInt(Map.get("seq")+"") - DelSeq;
				for(int i = 0; i < list.size(); i++) {
					
					HashMap Map2 = (HashMap) list.get(i);
					MenuVo vo = new MenuVo();
					
					vo.setIdx(Map2.get("idx")+"");
					
					System.out.println("idx : " + vo.getIdx());
					
					int SeqNum = Integer.parseInt(Map2.get("seq")+ "");
					SeqNum = SeqNum - Diff;
					
					vo.setSeq(Integer.toString(SeqNum));
					
					System.out.println("seq : " + vo.getSeq());
					
					
					menuMapper.setMenuUpdateData(vo);
					
				}
				
				
			}else {
				
				/*
				 * 1. 삭제
				 * 2. 삭제하려는 seq 값보다 높은 값들 리스트
				 * 3. 해당 리스트들 -1 씩 해주기
				 */
				menuMapper.setMenuDeleteData(menuVo);
				List<?> list = menuMapper.getMenuList(menuVo);
				for(int i = 0; i < list.size(); i++) {
					
					System.out.println("i : " + i);
					HashMap Map = (HashMap) list.get(i);
					MenuVo vo = new MenuVo();
					
					vo.setIdx(Map.get("idx")+"");
					
					System.out.println("idx : " + vo.getIdx());
					
					int SeqNum = Integer.parseInt(Map.get("seq")+ "");
					SeqNum -= 1;
					
					vo.setSeq(Integer.toString(SeqNum));
					
					System.out.println("seq : " + vo.getSeq());
					
					
					menuMapper.setMenuUpdateData(vo);
					
				}
				
			}
			
			break;
		}
		
	}
}
