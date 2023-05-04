package egovframework.sample.user.board.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.system.util.PageVO;

import egovframework.sample.user.board.model.UserBoardDataVo;
import egovframework.sample.user.board.model.UserBoardReplyVo;
import egovframework.sample.user.board.service.UserBoardDataService;


@Service("userBoardDataService")
@Transactional
public class UserBoardDataServiceImpl implements UserBoardDataService {

	@Resource(name = "userBoardDataMapper")
	private UserBoardDataMapper userBoardDataMapper;
	
	protected Log log = LogFactory.getLog(UserBoardDataServiceImpl.class);

	@Override
	public ModelMap getAllList(UserBoardDataVo userBoardDataVo) {
		
		ModelMap model = new ModelMap();
		
		List<?> BoardDataList = userBoardDataMapper.getAllList(userBoardDataVo);
		
		System.out.println("size : " + BoardDataList.size());
		
		int itemtotalcount = userBoardDataMapper.getAllListCnt(userBoardDataVo);
		int itemcount = userBoardDataVo.getITEM_COUNT();
		int itempage = userBoardDataVo.getPAGE();
		
		PageVO pageVo = new PageVO(itemcount, itemtotalcount, itempage);
		
		if(pageVo.isItempagenext() == true) {
			model.put("itempagenext", "true");
		}else {
			model.put("itempagenext", "false");
		}
		
		System.out.println(pageVo.getItempage());
		
		model.put("BoardDataList", BoardDataList);
		
		model.put("page" , pageVo.getItempage());
		model.put("itemcount" , pageVo.getItempage());
		model.put("itempagestart", pageVo.getItempagestart());
		model.put("itempageend", pageVo.getItempageend());
		model.put("itemtotalcount", pageVo.getItemtotalcount());
		model.put("itemtotalpage", pageVo.getItemtotalpage());
		
		return model;
	}

	@Override
	public void setBoardData(UserBoardDataVo userBoardDataVo, String SetType) {
		
		System.out.println("SetType : " + SetType);
		if(SetType.equals("insert")) {
			
			userBoardDataMapper.setBoardDataInsert(userBoardDataVo);
			
		}else if(SetType.equals("update")) {
			
			userBoardDataMapper.setBoardDataUpdate(userBoardDataVo);
			
		}else {
			
			System.out.println("SetType Error");
			
		}
		
	}

	@Override
	public ModelMap getBoardData(UserBoardDataVo userBoardDataVo) {
		
		ModelMap model = new ModelMap();
		
		//board_data 가져오기
		
		UserBoardDataVo view = userBoardDataMapper.getBoardData(userBoardDataVo);
		
		UserBoardReplyVo userBoardReplyVo = new UserBoardReplyVo();
		
		//reply List 가져오기
		
		userBoardReplyVo.setBoard_data_idx(userBoardDataVo.getIdx());
		
		userBoardReplyVo.setBoard_idx(userBoardDataVo.getBoard_idx());
		
		List<?> replylist = userBoardDataMapper.getReplyAllList(userBoardReplyVo);
		
		model.put("view", view);
		
		model.put("replylist", replylist);
		
		return model;
	}

	@Override
	public void DelBoardData(UserBoardDataVo userBoardDataVo) {
		
		userBoardDataMapper.DelBoardData(userBoardDataVo);
		
	}

	@Override
	public ModelMap getReplyAllList(UserBoardReplyVo userBoardReplyVo) {
		
		ModelMap model = new ModelMap();
		
		List<?> list = userBoardDataMapper.getReplyAllList(userBoardReplyVo);
		
		model.put("list", list);
		
		return model;
	}
	
	@Override
	public void setBoardReplyDataInsert(UserBoardReplyVo userBoardReplyVo) {
		
		userBoardDataMapper.setBoardReplyInsert(userBoardReplyVo);
		
		
	}

	@Override
	public void setBoardReplyDataUpdate(UserBoardReplyVo userBoardReplyVo) {
		
		userBoardDataMapper.setBoardReplyUpdate(userBoardReplyVo);
		
	}
	
	@Override
	public void DelBoardReplyData(UserBoardReplyVo replyVo , String type) {
		
		if(type.equals("list")) {
			userBoardDataMapper.DelBoardReplyDataList(replyVo);
		}else if(type.equals("one")) {
			userBoardDataMapper.DelBoardReplyDataOne(replyVo);
		}
		
	}

	@Override
	public void setBoardDataRdCntUp(UserBoardDataVo userBoardDataVo) {
		
		userBoardDataMapper.setBoardDataRdCnt(userBoardDataVo);
		
	}

	@Override
	public ModelMap getIndexBoardData() {
		
		ModelMap model = new ModelMap();
		
		//공지 가져올갯수
		int NoticeCnt = 4;
		
		//정보안내 가져올갯수
		int InfoCnt = 4;
		
		List<?> NoticeList = userBoardDataMapper.getNoticeList(NoticeCnt);
		
		List<?> InfoList = userBoardDataMapper.getInfoList(InfoCnt);
		
		model.put("NoticeList", NoticeList);
		
		model.put("InfoList", InfoList);
		
		return model;
	}

	
	
	
}
