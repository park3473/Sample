package egovframework.sample.admin.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.system.util.PageVO;

import egovframework.sample.admin.exam.model.AdminExamVo;
import egovframework.sample.admin.exam.service.AdminExamService;

@Service
@Transactional
public class AdminExamServiceImpl implements AdminExamService {

	@Resource(name="adminExamMapper")
	private AdminExamMapper adminExamMapper;

	@Override
	public ModelMap getAllList(AdminExamVo adminExamVo) {
		
		ModelMap modelMap = new ModelMap();
		
		List<?> list = adminExamMapper.getAllList(adminExamVo);
		
		System.out.println("size : " + list.size());
		
		int itemtotalcount = adminExamMapper.getAllListCnt(adminExamVo);
		int itemcount  = adminExamVo.getITEM_COUNT();
		int itempage = adminExamVo.getITEM_PAGE();
		
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
	public void setAdminExamData(AdminExamVo adminExamVo, String type) {
		// TODO Auto-generated method stub
		switch (type) {
		case "insert":
			adminExamMapper.setAdminExamDataInsert(adminExamVo);
			break;
		case "update":
			adminExamMapper.setAdminExamDataUpdate(adminExamVo);
			break;
		case "delete":
			adminExamMapper.setAdminExamDataDelete(adminExamVo);
			break;
		default:
			System.out.println("Type 오류");
			
			break;
		}
		
		
	}
	
}
