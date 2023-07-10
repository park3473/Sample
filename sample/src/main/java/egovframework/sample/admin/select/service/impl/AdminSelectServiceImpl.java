package egovframework.sample.admin.select.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import egovframework.sample.admin.select.model.AdminSelectVo;
import egovframework.sample.admin.select.service.AdminSelectService;

@Service("adminSelectService")
@Transactional
public class AdminSelectServiceImpl implements AdminSelectService {

	@Resource(name="adminSelectMapper")
	AdminSelectMapper adminSelectMapper;

	@Override
	public ModelMap getSelectAllList(AdminSelectVo adminSelectVo) {
		
		ModelMap model = new ModelMap();
		
		List<?> list = adminSelectMapper.getSelectAllList(adminSelectVo);
		
		model.put("list", list);
		
		return model;
	}

	@Override
	public void setSelectData(AdminSelectVo adminSelectVo, String type) {
		
		switch (type) {
		case "insert":
			adminSelectMapper.setSelectInsert(adminSelectVo);
			break;
		case "update":
			adminSelectMapper.setSelectUpdate(adminSelectVo);	
				break;
		case "delete":
			adminSelectMapper.setSelectDelete(adminSelectVo);
			break;
		case "Alldelete":
			adminSelectMapper.setSelectAllDelete(adminSelectVo);
			break;
		default:
			break;
		}
		
	}

	@Override
	public ModelMap getSelectView(AdminSelectVo adminSelectVo) {
		
		ModelMap model = new ModelMap();
		
		AdminSelectVo view = new AdminSelectVo();
		
		view = adminSelectMapper.getSelectView(adminSelectVo);
		
		model.put("view", view);
		
		return model;
	}
	
}
