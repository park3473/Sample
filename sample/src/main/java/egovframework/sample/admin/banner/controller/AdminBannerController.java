package egovframework.sample.admin.banner.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.system.util.SUtil;

import egovframework.sample.admin.banner.model.AdminBannerVo;
import egovframework.sample.admin.banner.service.AdminBannerService;

@Controller
public class AdminBannerController {
	
	@Autowired
	AdminBannerService adminBannerService;
	
	@RequestMapping(value="/admin/banner/list.do" , method = RequestMethod.GET)
	public ModelAndView AdminBannerList(@ModelAttribute("AdminBannerVo")AdminBannerVo AdminBannerVo , HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("PAGE : " + AdminBannerVo.getPAGE());
		System.out.println("ITEM_COUNT : " + AdminBannerVo.getITEM_COUNT());
		
		String PAGE = request.getParameter("PAGE") != null ? request
				.getParameter("PAGE") : "0";
		String ITEM_COUNT = request.getParameter("ITEM_COUNT") != null ? request
				.getParameter("ITEM_COUNT") : "10";
		
		AdminBannerVo.setPAGE(Integer.parseInt(PAGE));
		AdminBannerVo.setITEM_COUNT(Integer.parseInt(ITEM_COUNT));
		
		int pagelimit = AdminBannerVo.getPAGE() * AdminBannerVo.getITEM_COUNT();
		
		AdminBannerVo.setLIMIT(Integer.parseInt(ITEM_COUNT));
		AdminBannerVo.setOFFSET(pagelimit);
		
		ModelMap model = new ModelMap();
		
		model = adminBannerService.getAllList(AdminBannerVo);
		
		model.put("before", AdminBannerVo);
		
		
		return new ModelAndView("admin/banner/list","model" , model);
	}
	
	@RequestMapping(value="/admin/banner/insert.do" , method = RequestMethod.GET)
	public String AdminBannerInsert(HttpServletRequest request , HttpServletResponse response) {
		
		return "admin/banner/insert";
		
	}
	
	@RequestMapping(value="/admin/banner/view.do" , method = RequestMethod.GET)
	public String AdminBannerUpdate(HttpServletRequest request , HttpServletResponse response) {
		
		return "admin/banner/view";
		
	}
	
	@RequestMapping(value="/admin/banner/insert.do" , method = RequestMethod.POST)
	public void AdminBannerInsertData(@ModelAttribute("AdminBannerVo")AdminBannerVo AdminBannerVo , MultipartHttpServletRequest request , HttpServletResponse response) throws IOException {
		
		String drv = request.getRealPath("");
		drv = drv.substring(0 , drv.length()) + "./resources/" + ((HttpServletRequest) request).getContextPath() + "/upload/banner/";
		
		String filename = SUtil.setFileUpload(request, drv);
		
		AdminBannerVo.setImage(filename);
		
		adminBannerService.setBannerData(AdminBannerVo , "insert");
		
		SUtil.AlertAndPageMove(response, "배너 정보가 저장되었습니다.", "/admin/banner/list.do");
		
	}
	
	@RequestMapping(value="/admin/banner/update.do" , method = RequestMethod.POST)
	public void AdminBannerUpdateData(@ModelAttribute("AdminBannerVo")AdminBannerVo AdminBannerVo , MultipartHttpServletRequest request , HttpServletResponse response) throws IOException {
		
		String drv = request.getRealPath("");
		drv = drv.substring(0 , drv.length()) + "./resources/" + ((HttpServletRequest) request).getContextPath() + "/upload/banner/";
		
		String filename = SUtil.setFileUpload(request, drv);
		
		if(filename.equals("")) {
			System.out.println("file_no");
		}else {
			System.out.println("file_yes");
			AdminBannerVo.setImage(filename);
		}
		
		adminBannerService.setBannerData(AdminBannerVo , "update");
		
		SUtil.AlertAndPageMove(response, "배너 정보가 저장되었습니다.", "/admin/banner/list.do");
		
	}
	
	@RequestMapping(value="/admin/banner/delete.do" , method = RequestMethod.POST)
	public void AdminBannerDeleteData(@ModelAttribute("AdminBannerVo")AdminBannerVo AdminBannerVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		adminBannerService.setBannerData(AdminBannerVo , "delete");
		
		SUtil.AlertAndPageMove(response, "배너 정보가 삭제되었습니다.", "/admin/banner/list.do");
		
	}

}
