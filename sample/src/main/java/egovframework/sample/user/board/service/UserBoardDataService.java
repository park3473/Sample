package egovframework.sample.user.board.service;

import org.springframework.ui.ModelMap;

import egovframework.sample.user.board.model.UserBoardDataVo;

public interface UserBoardDataService {

	public ModelMap getAllList(UserBoardDataVo userBoardDataVo);

	public void setBoardData(UserBoardDataVo userBoardDataVo, String SetType);

	public ModelMap getBoardData(UserBoardDataVo userBoardDataVo);

	public void DelBoardData(UserBoardDataVo userBoardDataVo);

}
