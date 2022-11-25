package egovframework.sample.user.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import egovframework.sample.user.board.model.UserBoardDataVo;
import egovframework.sample.user.board.model.UserBoardReplyVo;

public interface UserBoardDataService {

	public ModelMap getAllList(UserBoardDataVo userBoardDataVo);

	public void setBoardData(UserBoardDataVo userBoardDataVo, String SetType);

	public ModelMap getBoardData(UserBoardDataVo userBoardDataVo);

	public void DelBoardData(UserBoardDataVo userBoardDataVo);

	public List<Map<String, Object>> getReplyAllList(UserBoardReplyVo userBoardReplyVo);



}
