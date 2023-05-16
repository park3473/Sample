package egovframework.sample.admin.exam.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.sample.admin.exam.service.AdminExamService;

@Service
@Transactional
public class AdminExamServiceImpl implements AdminExamService {

	@Resource(name="adminExamMapper")
	private AdminExamMapper adminExamMapper;
	
}
