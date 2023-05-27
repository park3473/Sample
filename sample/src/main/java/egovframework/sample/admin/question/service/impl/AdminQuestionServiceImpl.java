package egovframework.sample.admin.question.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.system.util.PageVO;

import egovframework.sample.admin.question.model.AdminQuestionVo;
import egovframework.sample.admin.question.service.AdminQuestionService;


@Service("adminQuestionService")
@Transactional
public class AdminQuestionServiceImpl implements AdminQuestionService {

	@Resource(name="adminQuestionMapper")
	AdminQuestionMapper adminQuestionMapper;

	@Override
	public ModelMap getAllList(AdminQuestionVo adminQuestionVo) {
		
		ModelMap modelMap = new ModelMap();
		
		List<?> list = adminQuestionMapper.getAllList(adminQuestionVo);
		
		System.out.println("size : " + list.size());
		int itemtotalcount = adminQuestionMapper.getAllListCnt(adminQuestionVo);
		int itemcount  = adminQuestionVo.getITEM_COUNT();
		int itempage = adminQuestionVo.getITEM_PAGE();
		
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
	public void setQuestionData(AdminQuestionVo adminQuestionVo, String type) {
		
		switch (type) {
		case "insert":
			adminQuestionMapper.setQuestionDataInsert(adminQuestionVo);
			break;
		case "update":
			adminQuestionMapper.setQuestionDataUpdate(adminQuestionVo);
			break;
		case "delete":
			adminQuestionMapper.setQuestionDataDelete(adminQuestionVo);
			break;
		default:
			System.out.println("Type오류");
			break;
		}
		
	}

	@Override
	public List<?> getTypeAllList() {
		
		List<?> TypeList = adminQuestionMapper.getTypeAllList();
		
		return TypeList;
	}
	
}
