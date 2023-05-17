package egovframework.sample.admin.banner.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.system.util.PageVO;

import egovframework.sample.admin.banner.service.AdminBannerService;
import egovframework.sample.admin.banner.model.AdminBannerVo;
import egovframework.sample.admin.banner.service.impl.AdminBannerMapper;

@Service("adminBannerService")
public class AdminBannerServiceImpl implements AdminBannerService {

	@Resource(name="adminBannerMapper")
	AdminBannerMapper adminBannerMapper;

	@Override
	public ModelMap getAllList(AdminBannerVo adminBannerVo) {
		
		ModelMap model = new ModelMap();
		
		List<?> list = adminBannerMapper.getAllList(adminBannerVo);
		
		int itemtotalcount = adminBannerMapper.getAllListCnt(adminBannerVo);
		int itemcount = adminBannerVo.getITEM_COUNT();
		int itempage = adminBannerVo.getPAGE();
		
		PageVO pageVo = new PageVO(itemcount, itemtotalcount, itempage);
		
		if(pageVo.isItempagenext() == true){
			model.put("itempagenext", "true");
		}else {
			model.put("itempagenext", "false");
		}
		
		System.out.println(pageVo.getItempage());
		
		model.put("page", pageVo.getItempage());
		model.put("itemcount", pageVo.getItemCount());
		model.put("itempagestart", pageVo.getItempagestart());
		model.put("itempageend", pageVo.getItempageend());
		model.put("itemtotalcount", pageVo.getItemtotalcount());
		model.put("itemtotalpage", pageVo.getItemtotalpage());
		
		model.put("list", list);
		
		return model;
	}

	@Override
	public void setBannerData(AdminBannerVo adminBannerVo, String type) {
		
		switch (type) {
		case "insert":
			
			adminBannerMapper.setBannerDataInsert(adminBannerVo);
			
			break;
		case "update":
			
			adminBannerMapper.setBannerDataUpdate(adminBannerVo);
			adminBannerMapper.setBannerReOrder(adminBannerVo);
			
			break;
		case "delete":
			
			adminBannerMapper.setBannerDataDelete(adminBannerVo);
			
			break;

		}
		
		
	}
	
}
