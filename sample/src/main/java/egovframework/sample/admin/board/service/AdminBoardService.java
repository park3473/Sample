package egovframework.sample.admin.board.service;

import org.springframework.ui.ModelMap;

import egovframework.sample.admin.board.model.AdminBoardVo;

public interface AdminBoardService {

	public ModelMap getAllList(AdminBoardVo adminBoardVo);

	public ModelMap getBoardView(AdminBoardVo adminBoardVo);

	public void setBoardInsert(AdminBoardVo adminBoardVo);

	public void setBoardUpdate(AdminBoardVo adminBoardVo);

	public void setBoardDelete(AdminBoardVo adminBoardVo);

	public AdminBoardVo getBoardConfig(AdminBoardVo adminBoardVo);

}
