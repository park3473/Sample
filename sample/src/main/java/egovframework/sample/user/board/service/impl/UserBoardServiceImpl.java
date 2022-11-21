package egovframework.sample.user.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import egovframework.sample.user.board.model.UserBoardVo;
import egovframework.sample.user.board.service.UserBoardService;


@Service("userBoardService")
@Transactional
public class UserBoardServiceImpl implements UserBoardService {

	@Resource(name="userBoardMapper")
	private UserBoardMapper userBoardMapper;

	@Override
	public ModelMap getAllList(UserBoardVo userBoardVo) {
		
		ModelMap model = new ModelMap();
		List<?> BoardList = userBoardMapper.getAllList(userBoardVo);
		model.put("BoardList", BoardList);
		
		return model;
	}
	
}
