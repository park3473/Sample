package egovframework.sample.user.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.system.util.PageVO;

import egovframework.sample.user.board.model.UserBoardVo;
import egovframework.sample.user.board.service.UserBoardService;


@Service("userBoardService")
@Transactional
public class UserBoardServiceImpl implements UserBoardService {

	@Resource(name="userBoardMapper")
	private UserBoardMapper userBoardMapper;

	@Override
	public ModelMap getAllList(UserBoardVo userBoardVo) {
		
		ModelMap modelMap = new ModelMap();
		List<?> BoardList = userBoardMapper.getAllList(userBoardVo);
		
		System.out.println("size : " + BoardList.size());
		
		int itemtotalcount = userBoardMapper.getAllListCnt(userBoardVo);
		int itemcount = userBoardVo.getITEM_COUNT();
		int itempage = userBoardVo.getITEM_PAGE();
		
		PageVO pageVo = new PageVO(itemcount, itemtotalcount, itempage);
		
		if(pageVo.isItempagenext() == true) {
			modelMap.put("itempagenext", "true");
		} else {
			modelMap.put("itempagenext", "false");
		}
		
		System.out.println(pageVo.getItempage());
		
		modelMap.put("page", pageVo.getItempage());
		modelMap.put("itemcount", pageVo.getItemCount());
		modelMap.put("itempagestart", pageVo.getItempagestart());
		modelMap.put("itempageend", pageVo.getItempageend());
		modelMap.put("itemtotalcount", pageVo.getItemtotalcount());
		modelMap.put("itemtotalpage", pageVo.getItemtotalpage());
		
		modelMap.put("BoardList", BoardList);
		
		return modelMap;
	}

	@Override
	public void setBoard(UserBoardVo userBoardVo , String SetType) {
		
		System.out.println("SetType : " + SetType);
		
		if(SetType.equals("insert")) {
		
			userBoardMapper.setBoardInsert(userBoardVo);
			
		} else if(SetType.equals("update")) {
			
			userBoardMapper.setBoardUpdate(userBoardVo);
			
		} else {
			
			System.out.println("SetType Error");
			
		}
		
		
	}

	@Override
	public ModelMap getBoard(UserBoardVo userBoardVo) {
		
		ModelMap model = new ModelMap();
		
		UserBoardVo view = userBoardMapper.getBoard(userBoardVo);
		
		model.put("view", view);
		
		return model;
	}

	@Override
	public void delBoard(UserBoardVo userBoardVo) {
		userBoardMapper.delBoard(userBoardVo);
		
	}

	@Override
	public ModelMap getBoard(String board_idx) {
		
		ModelMap model = new ModelMap();
		
		UserBoardVo view = userBoardMapper.getBoardView(board_idx);
		
		model.put("view", view);
		
		return model;
	}

	@Override
	public UserBoardVo getBoardConfig(String board_idx) {
		UserBoardVo UserBoardVo = new UserBoardVo();
		
		UserBoardVo = userBoardMapper.getBoardConfig(board_idx);
		
		return UserBoardVo;
	}
	
}
