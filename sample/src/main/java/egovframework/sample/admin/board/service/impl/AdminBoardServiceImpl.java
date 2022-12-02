package egovframework.sample.admin.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.system.util.PageVO;

import egovframework.sample.admin.board.model.AdminBoardVo;
import egovframework.sample.admin.board.service.AdminBoardService;

@Service("adminBoardService")
@Transactional
public class AdminBoardServiceImpl implements AdminBoardService {

	@Resource(name="adminBoardMapper")
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
		
		AdminBoardVo adminVo = new AdminBoardVo();
		adminVo = adminBoardMapper.getBoardView(adminBoardVo);
		
		model.put("view", adminVo);
		
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

	@Override
	public AdminBoardVo getBoardConfig(AdminBoardVo adminBoardVo) {
		
		AdminBoardVo adminBoardConfig = new AdminBoardVo();
		
		adminBoardConfig = adminBoardMapper.getBoardConfig(adminBoardVo);
		
		return adminBoardConfig;
	}

}
