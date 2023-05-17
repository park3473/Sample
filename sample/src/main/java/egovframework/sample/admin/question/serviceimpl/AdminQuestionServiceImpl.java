package egovframework.sample.admin.question.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import egovframework.sample.admin.question.model.AdminQuestionVo;
import egovframework.sample.admin.question.service.AdminQuestionService;


@Service("adminQuestionService")
@Transactional
public class AdminQuestionServiceImpl implements AdminQuestionService {

	@Resource(name="adminQuestionMapper")
	AdminQuestionMapper adminQuestionMapper;

	@Override
	public ModelMap getAllList(AdminQuestionVo adminQuestionVo) {
		
		ModelMap model = new ModelMap();
		
		List<?> list = adminQuestionMapper.getAllList(adminQuestionVo);
		
		model.put("list", list);
		
		return model;
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
		case "delete_all":
			adminQuestionMapper.setQuestionDataDeleteAll(adminQuestionVo);
			break;
		case "delete_one":
			adminQuestionMapper.setQuestionDataDeleteOne(adminQuestionVo);
			break;
		default:
			System.out.println("Type오류");
			break;
		}
		
	}
	
}
