package egovframework.sample.admin.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.system.util.PageVO;

import egovframework.sample.admin.member.model.AdminMemberVo;
import egovframework.sample.admin.member.service.AdminMemberService;


@Service("adminMemberService")
@Transactional
public class AdminMemberServiceImpl implements AdminMemberService {

	@Resource(name="adminMemberMapper")
	private AdminMemberMapper adminMemberMapper;

	@Override
	public ModelMap getAllList(AdminMemberVo adminMemberVo) {
		
		ModelMap model = new ModelMap();
		
		List<?> list = adminMemberMapper.getAllList(adminMemberVo);
		
		System.out.println("size : " + list.size());
		
		int itemtotalcount = adminMemberMapper.getAllListCount(adminMemberVo);
		int itemcount = adminMemberVo.getITEM_COUNT();
		int itempage = adminMemberVo.getITEM_PAGE();
		
		PageVO pageVo = new PageVO(itemcount, itemtotalcount, itempage);
		
		if(pageVo.isItempagenext() == true) {
			model.put("itempagenext", "true");
		}else {
			model.put("itempagenext", "false");
		}
		
		System.out.println(pageVo.getItempage());
		
		model.put("page" , pageVo.getItempage());
		model.put("itemcount" , pageVo.getItemCount());
		model.put("itempagestart", pageVo.getItempagestart());
		model.put("itempageend", pageVo.getItempageend());
		model.put("itemtotalcount", pageVo.getItemtotalcount());
		model.put("itemtotalpage", pageVo.getItemtotalpage());
		
		model.put("list", list);
		
		return model;
	}

	@Override
	public void setMemberData(AdminMemberVo adminMemberVo, String type) {
		
		switch (type) {
		case "insert":
			adminMemberMapper.setMemberDataInsert(adminMemberVo);
			break;
		case "update":
			adminMemberMapper.setMemberDataUpdate(adminMemberVo);	
			break;
		case "delete":
			adminMemberMapper.setMemberDataDelete(adminMemberVo);
			break;
		default:
			System.out.println("type 오류");
			break;
		}
		
		
	}

	@Override
	public ModelMap getMemberData(AdminMemberVo adminMemberVo) {
		
		ModelMap model = new ModelMap();
		
		AdminMemberVo memberVo = new AdminMemberVo();
		
		memberVo = adminMemberMapper.getMemberData(adminMemberVo);
		
		model.put("view", memberVo);
		
		return model;
	}

	@Override
	public String getIdCheck(AdminMemberVo adminMemberVo) {
		
		String Result = "";
		
		int IdCheck = adminMemberMapper.getIdCheck(adminMemberVo);
		if(IdCheck == 0) {
			Result = "true";
		}else {
			Result = "false";
		}
		
		return Result;
	}
	
}
