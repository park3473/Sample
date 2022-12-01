package egovframework.sample.admin.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import egovframework.sample.admin.board.service.AdminBoardDataService;

@Controller
public class AdminBoardDataContorller {

	@Autowired
	AdminBoardDataService adminBoardDataService;
	
	
	
}
