package egovframework.sample.user.board.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.user.board.model.UserBoardVo;

@Mapper("userBoardMapper")
public interface UserBoardMapper {

	List<?> getAllList(UserBoardVo userBoardVo);

	
	
}
