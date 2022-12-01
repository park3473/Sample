package egovframework.sample.admin.board.service.impl;

import java.util.List;

import org.springframework.ui.ModelMap;

import egovframework.sample.admin.board.model.AdminBoardVo;

public interface AdminBoardMapper {

	public List<?> getAllList(AdminBoardVo adminBoardVo);

	public int getAllListCnt(AdminBoardVo adminBoardVo);

	public ModelMap getBoardView(AdminBoardVo adminBoardVo);

	public void setBoardInsert(AdminBoardVo adminBoardVo);

	public void setBoardUpdate(AdminBoardVo adminBoardVo);

	public void setBoardDelete(AdminBoardVo adminBoardVo);

}
