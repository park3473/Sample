package egovframework.sample.menu.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.menu.model.MenuVo;

@Mapper("MenuMapper")
public interface MenuMapper {

	List<?> getMenuList(MenuVo menuVo);

	void setMenuInsertData(MenuVo menuVo);

	void setMenuUpdateData(MenuVo menuVo);

	void setMenuDeleteData(MenuVo menuVo);

	void setMenuDeleteList(MenuVo menuVo);

	void setMenuTitleReOrder(MenuVo menuVo);

	void setMenuSeqReOrder(MenuVo menuVo);

	void setMenuReSeq(int seq);

	int getMenuListCnt(MenuVo menuVo);

}
