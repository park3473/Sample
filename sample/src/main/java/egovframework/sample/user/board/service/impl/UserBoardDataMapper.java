package egovframework.sample.user.board.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.user.board.model.UserBoardDataVo;

@Mapper("userBoardDataMapper")
public interface UserBoardDataMapper {

	public List<?> getAllList(UserBoardDataVo userBoardDataVo);

	public int getAllListCnt(UserBoardDataVo userBoardDataVo);

	public void setBoardDataInsert(UserBoardDataVo userBoardDataVo);

	public void setBoardDataUpdate(UserBoardDataVo userBoardDataVo);

	public UserBoardDataVo getBoardData(UserBoardDataVo userBoardDataVo);

	public void DelBoardData(UserBoardDataVo userBoardDataVo);

}
