package egovframework.sample.admin.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.system.util.PageVO;

import egovframework.sample.admin.board.model.AdminBoardVo;
import egovframework.sample.admin.board.service.AdminBoardService;

@Service("AdminBoardService")
public class AdminBoardServiceImpl implements AdminBoardService {

	@Resource(name="AdminBoardMapper")
	AdminBoardMapper adminBoardMapper;
	
	@Override
	public ModelMap getAllList(AdminBoardVo adminBoardVo) {
		
		ModelMap modelMap = new ModelMap();
		
		List<?> list = adminBoardMapper.getAllList(adminBoardVo);
		
		System.out.println("size : " + list.size());
		
		int itemtotalcount = adminBoardMapper.getAllListCnt(adminBoardVo);
		int itemcount = adminBoardVo.getITEM_COUNT();
		int itempage = adminBoardVo.getITEM_PAGE();
		
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
	public ModelMap getBoardView(AdminBoardVo adminBoardVo) {
		
		ModelMap model = new ModelMap();
		
		model = adminBoardMapper.getBoardView(adminBoardVo);
		
		return model;
	}

	@Override
	public void setBoardInsert(AdminBoardVo adminBoardVo) {
		
		adminBoardMapper.setBoardInsert(adminBoardVo);
		
	}

	@Override
	public void setBoardUpdate(AdminBoardVo adminBoardVo) {
		
		adminBoardMapper.setBoardUpdate(adminBoardVo);
		
	}

	@Override
	public void setBoardDelete(AdminBoardVo adminBoardVo) {
		
		adminBoardMapper.setBoardDelete(adminBoardVo);
		
	}

}
