package egovframework.sample.admin.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.system.util.PageVO;

import egovframework.sample.admin.board.model.AdminBoardDataVo;
import egovframework.sample.admin.board.model.AdminBoardReplyVo;
import egovframework.sample.admin.board.model.AdminBoardVo;
import egovframework.sample.admin.board.service.AdminBoardDataService;


@Service("adminBoardDataService")
@Transactional
public class AdminBoardDataServiceImpl implements AdminBoardDataService {

	@Resource(name="adminBoardDataMapper")
	AdminBoardDataMapper adminBoardDataMapper;
	
	@Override
	public ModelMap getAllList(AdminBoardDataVo adminBoardDataVo) {
		
		ModelMap modelMap = new ModelMap();
		
		List<?> list = adminBoardDataMapper.getAllList(adminBoardDataVo);
		
		System.out.println("size : " + list.size());
		
		int itemtotalcount = adminBoardDataMapper.getAllListCnt(adminBoardDataVo);
		int itemcount  = adminBoardDataVo.getITEM_COUNT();
		int itempage = adminBoardDataVo.getITEM_PAGE();
		
		PageVO pageVo = new PageVO(itemcount, itemtotalcount, itempage);
		
		if(pageVo.isItempagenext() == true){
			modelMap.put("itempagenext", "true");
		}else {
			modelMap.put("itempagenext", "false");
		}
		
		System.out.println(pageVo.getItempage());
		
		modelMap.put("page", pageVo.getItempage());
		modelMap.put("itemcount", pageVo.getItemCount());
		modelMap.put("itempagestart", pageVo.getItempagestart());
		modelMap.put("itempageend", pageVo.getItempageend());
		modelMap.put("itemtotalcount", pageVo.getItemtotalcount());
		modelMap.put("itemtotalpage", pageVo.getItemtotalpage());
		
		modelMap.put("list", list);
		
		return modelMap;
	}

	@Override
	public String setBoardData(AdminBoardDataVo adminBoardDataVo, String type) {
		
		String result ="result";
		
		switch (type) {
		case "insert":
			adminBoardDataMapper.setBoardDataInsert(adminBoardDataVo);
			System.out.println(adminBoardDataVo.getIdx());
			result = adminBoardDataVo.getIdx();
			
			break;
		case "update":
			adminBoardDataMapper.setBoardDataUpdate(adminBoardDataVo);
			break;
		case "delete":
			adminBoardDataMapper.setBoardDataDelete(adminBoardDataVo);
			break;
		default:
			System.out.println("type 오류");
			break;
		}
		
		return result;
		
	}

	@Override
	public ModelMap getBoardData(AdminBoardDataVo adminBoardDataVo) {
		
		ModelMap model = new ModelMap();
		
		AdminBoardDataVo adminDataVo = new AdminBoardDataVo();
		
		adminDataVo = adminBoardDataMapper.getBoardData(adminBoardDataVo);
		
		model.put("view", adminDataVo);
		
		return model;
	}

	@Override
	public ModelMap getReplyAllList(AdminBoardReplyVo adminBoardReplyVo) {
		
		ModelMap model = new ModelMap();
		
		List<?> list = adminBoardDataMapper.getReplyAllList(adminBoardReplyVo);
		
		model.put("replylist", list);
		
		return model;
	}

	@Override
	public void setBoardReplyData(AdminBoardReplyVo adminBoardReplyVo, String type) {
		
		switch (type) {
		case "insert":
			adminBoardDataMapper.setBoardReplyInsert(adminBoardReplyVo);
			break;
		case "update":
			adminBoardDataMapper.setBoardReplyUpdate(adminBoardReplyVo);
			break;
		case "delete":
			adminBoardDataMapper.setBoardReplyDelete(adminBoardReplyVo);
			break;
		default:
			System.out.println("type 오류");
			break;
		}
		
	}

	@Override
	public void setBoardReplyDataList(AdminBoardDataVo adminBoardDataVo, String type) {
		
		adminBoardDataMapper.setBoardReplyListDelete(adminBoardDataVo);
		
	}
	
	

}
