package egovframework.sample.user.board.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.user.board.model.UserBoardVo;

@Mapper("userBoardMapper")
public interface UserBoardMapper {

	List<?> getAllList(UserBoardVo userBoardVo);

	int getAllListCnt(UserBoardVo userBoardVo);

	UserBoardVo getBoard(UserBoardVo userBoardVo);

	void setBoardInsert(UserBoardVo userBoardVo);

	void setBoardUpdate(UserBoardVo userBoardVo);

	void delBoard(UserBoardVo userBoardVo);

	UserBoardVo getBoardView(String board_idx);

	UserBoardVo getBoardConfig(String board_idx);

	
	
}
