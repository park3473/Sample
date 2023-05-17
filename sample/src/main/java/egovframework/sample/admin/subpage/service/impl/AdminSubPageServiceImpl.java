package egovframework.sample.admin.subpage.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import egovframework.sample.admin.subpage.service.AdminSubPageService;
import egovframework.sample.admin.subpage.service.impl.AdminSubPageMapper;
import egovframework.sample.admin.subpage.model.AdminSubPageVo;

@Service("adminSubPageService")
@Transactional
public class AdminSubPageServiceImpl implements AdminSubPageService {

	@Resource(name="adminSubPageMapper")
	AdminSubPageMapper adminSubPageMapper;
	
	@Override
	public ModelMap getAllList(AdminSubPageVo adminSubpageVo) {
		
		ModelMap model = new ModelMap();
		
		List<?> list = adminSubPageMapper.getAllList(adminSubpageVo);
		
		model.put("list", list);
		
		return model;
	}

	@Override
	public ModelMap getViewData(AdminSubPageVo adminSubpageVo) {
		
		ModelMap model = new ModelMap();
		
		AdminSubPageVo view = new AdminSubPageVo();
		
		view = adminSubPageMapper.getViewData(adminSubpageVo);
		
		model.put("view", view);
		
		return model;
	}

	@Override
	public void setAdminSubPageData(AdminSubPageVo adminSubpageVo, String type) {
		
		switch (type) {
		case "insert":
			
			adminSubPageMapper.setAdminSubPageInsertData(adminSubpageVo);
			break;
		case "update":

			adminSubPageMapper.setAdminSubPageUpdateData(adminSubpageVo);
			break;
		case "delete":
			
			adminSubPageMapper.setAdminSubPageDeleteData(adminSubpageVo);
			break;
		}
		
		
	}
	
}
