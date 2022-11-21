package egovframework.sample.user.board.service;

import org.springframework.ui.ModelMap;

import egovframework.sample.user.board.model.UserBoardVo;

public interface UserBoardService {

	public ModelMap getAllList(UserBoardVo userBoardVo);

	
}
