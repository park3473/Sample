package egovframework.sample.user.board.service;

import org.springframework.ui.ModelMap;

import egovframework.sample.user.board.model.UserBoardVo;

public interface UserBoardService {

	public ModelMap getAllList(UserBoardVo userBoardVo);

	public void setBoard(UserBoardVo userBoardVo, String SetType);

	public ModelMap getBoard(UserBoardVo userBoardVo);

	public void delBoard(UserBoardVo userBoardVo);

	public ModelMap getBoard(String board_idx);

	public UserBoardVo getBoardConfig(String board_idx);

	
}
