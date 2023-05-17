package egovframework.sample.user.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.system.util.SUtil;

import egovframework.sample.file.model.FileVo;
import egovframework.sample.file.service.FileService;
import egovframework.sample.user.board.model.UserBoardDataVo;
import egovframework.sample.user.board.model.UserBoardReplyVo;
import egovframework.sample.user.board.model.UserBoardVo;
import egovframework.sample.user.board.service.UserBoardDataService;
import egovframework.sample.user.board.service.UserBoardService;

@Controller
public class UserBoardDataController {

	private static final Logger Logger = LoggerFactory.getLogger(UserBoardDataController.class);
	
	/* 
	 * 기초 설정
	 * 
	 * @ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response
	 * */
	
	@Autowired
	UserBoardService userBoardService;
	
	@Autowired
	UserBoardDataService userBoardDataService;
	
	@Autowired
	FileService fileService;
	
	
	/*board_data 부분*/
	
	@RequestMapping(value="/user/board_data/list.do" , method = RequestMethod.GET)
	public ModelAndView BoardDataAllList(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("PAGE : " + UserBoardDataVo.getPAGE());
		System.out.println("ITEM_COUNT : " + UserBoardDataVo.getITEM_COUNT());
		
		String PAGE = request.getParameter("PAGE") != null ? request
				.getParameter("PAGE") : "0";
		String ITEM_COUNT = request.getParameter("ITEM_COUNT") != null ? request
				.getParameter("ITEM_COUNT") : "10";
		
		UserBoardDataVo.setPAGE(Integer.parseInt(PAGE));
		UserBoardDataVo.setITEM_COUNT(Integer.parseInt(ITEM_COUNT));
		
		int pagelimit = UserBoardDataVo.getPAGE() * UserBoardDataVo.getITEM_COUNT();
		
		UserBoardDataVo.setLIMIT(Integer.parseInt(ITEM_COUNT));
		UserBoardDataVo.setOFFSET(pagelimit);
		
		System.out.println("board_idx : " + request.getParameter("board_idx"));
		UserBoardDataVo.setBoard_idx(request.getParameter("board_idx"));
		
		ModelMap model = new ModelMap();
		model = userBoardDataService.getAllList(UserBoardDataVo);
		
		model.put("beforeDomain", UserBoardDataVo);
		
		return new ModelAndView("/user/board_data/list" , "model" , model);
		
	}
	
	@RequestMapping(value="/user/board_data/insert.do" , method = RequestMethod.GET)
	public ModelAndView BoardDataInsertView(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		
		//게시판 설정 내용
		UserBoardVo BoardConfig = new UserBoardVo();
		
		BoardConfig = userBoardService.getBoardConfig(UserBoardDataVo.getBoard_idx());
		
		model.put("BoardConfig", BoardConfig);
		
		return new ModelAndView("user/board_data/insert" , "model" , model);
	}
	
	@RequestMapping(value="/user/board_data/insert.do" , method = RequestMethod.POST)
	public void BoardDataInsertData(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , MultipartHttpServletRequest request , HttpServletResponse response) throws IOException {
		
		System.out.println("Board_data_idx : " + UserBoardDataVo.getIdx());
		System.out.println("Board_idx : " + UserBoardDataVo.getBoard_idx());
		
		String Board_idx = UserBoardDataVo.getBoard_idx();
		String Board_data_idx = UserBoardDataVo.getIdx();
		
		FileVo filevo = new FileVo();
		
		//파일 등록
		String drv = request.getRealPath("");
		drv = drv.substring(0 , drv.length()) + "./resources/" + ((HttpServletRequest) request).getContextPath() + "/upload/file/";
		
		String filename = SUtil.setFileUpload(request, drv);
		
		String files[] = filename.split(",");
		
		for(int i = 0; i < files.length; i ++) {
			
			String saveFile = files[i];
			
			filevo.setType("insert");
			filevo.setFilename(saveFile);
			filevo.setUrl(request.getRequestURI());
			filevo.setBoard_idx(Board_idx);
			filevo.setBoard_data_idx(Board_data_idx);
			
			fileService.setFileData(filevo);
			
		}
		
		userBoardDataService.setBoardData(UserBoardDataVo , "insert");
		
		SUtil.AlertAndPageMove(response, "게시글 등록이 완료되었습니다.", "/user/board_data/list.do?Board_idx=" + Board_idx);
		
	}
	
	@RequestMapping(value="/user/board_data/view.do" , method = RequestMethod.GET)
	public ModelAndView BoardDataView(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) {
		
		ModelMap model = new ModelMap();
		
		//board_data 조회수 증가
		userBoardDataService.setBoardDataRdCntUp(UserBoardDataVo);
		
		//board_data 가져오기
		model = userBoardDataService.getBoardData(UserBoardDataVo);
		
		
		//board_config 가져오기
		UserBoardVo BoardConfig = new UserBoardVo();
		
		BoardConfig = userBoardService.getBoardConfig(UserBoardDataVo.getBoard_idx());
		
		model.put("BoardConfig", BoardConfig);
		
		//board_data_file 가져오기
		FileVo filevo = new FileVo();
		filevo.setBoard_data_idx(UserBoardDataVo.getIdx());
		filevo.setBoard_idx(UserBoardDataVo.getBoard_idx());
		List<?> filelist = fileService.getFileList(filevo);
		
		model.put("filelist", filelist);
		
		return new ModelAndView("user/board_data/view" , "model" , model);
	}
	
	@RequestMapping(value="/user/board_data/update.do" , method = RequestMethod.GET)
	public ModelAndView BoardDataUpdateView(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) {
	
		ModelMap model = new ModelMap();
		
		//borad_data 가져오기
		model = userBoardDataService.getBoardData(UserBoardDataVo);
		
		//board_config 가져오기
		UserBoardVo BoardConfig = new UserBoardVo();
		
		BoardConfig = userBoardService.getBoardConfig(UserBoardDataVo.getBoard_idx());
		
		model.put("BoardConfig", BoardConfig);
		
		//board_data_file 가져오기
		FileVo filevo = new FileVo();
		filevo.setBoard_data_idx(UserBoardDataVo.getIdx());
		filevo.setBoard_idx(UserBoardDataVo.getBoard_idx());
		List<?> filelist = fileService.getFileList(filevo);
				
		model.put("filelist", filelist);
		
		return new ModelAndView("user/board_data/update" , "model" , model);
		
	}
	
	@RequestMapping(value="/user/board_data/update.do" , method = RequestMethod.POST)
	public void BoardDataUpdate(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , MultipartHttpServletRequest request , HttpServletResponse response) throws IOException {
		
		String member_id = SUtil.getUserId(request);
		
		System.out.println("Id : " + member_id);
		
		UserBoardDataVo.setMember_id(member_id);
		
		userBoardDataService.setBoardData(UserBoardDataVo, "update");
		
		System.out.println("Board_data_idx : " + UserBoardDataVo.getIdx());
		System.out.println("Board_idx : " + UserBoardDataVo.getBoard_idx());
		
		String Board_idx = UserBoardDataVo.getBoard_idx();
		String Board_data_idx = UserBoardDataVo.getIdx();
		
		FileVo filevo = new FileVo();
		
		//파일 등록
		String drv = request.getRealPath("");
		drv = drv.substring(0 , drv.length()) + "./resources/" + ((HttpServletRequest) request).getContextPath() + "/upload/file/";
		
		String filename = SUtil.setFileUpload(request, drv);
		
		String files[] = filename.split(",");
		
		for(int i = 0; i < files.length; i ++) {
			
			String saveFile = files[i];
			
			filevo.setType("insert");
			filevo.setFilename(saveFile);
			filevo.setUrl(request.getRequestURI());
			filevo.setBoard_idx(Board_idx);
			filevo.setBoard_data_idx(Board_data_idx);
			
			fileService.setFileData(filevo);
			
		}
		
		SUtil.AlertAndPageMove(response, "게시글 수정이 완료되었습니다.", "/user/board_data/list.do?Board_idx=" + Board_idx);
		
	}
	
	
	@RequestMapping(value="/user/board_data/delete.do" , method = RequestMethod.POST)
	public void BoardDataDelete(@ModelAttribute("UserBoardDataVo")UserBoardDataVo UserBoardDataVo , HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		String member_id = SUtil.getUserId(request);
		
		UserBoardDataVo.setMember_id(member_id);
		
		//게시글 삭제
		userBoardDataService.DelBoardData(UserBoardDataVo);
		
		UserBoardReplyVo replyVo = new UserBoardReplyVo();
		
		replyVo.setMember_id(member_id);
		
		replyVo.setBoard_data_idx(UserBoardDataVo.getIdx());
		replyVo.setBoard_idx(UserBoardDataVo.getBoard_idx());
		
		//게시글 연관된 댓글 삭제
		userBoardDataService.DelBoardReplyData(replyVo , "list");
		
		
		System.out.println("Board_data_idx : " + UserBoardDataVo.getIdx());
		System.out.println("Board_idx : " + UserBoardDataVo.getBoard_idx());
		
		String Board_idx = UserBoardDataVo.getBoard_idx();
		
		SUtil.AlertAndPageMove(response, "게시글 삭제가 완료되었습니다.", "/user/board_data/list.do?Board_idx=" + Board_idx);
		
	}
	
	/*board_data_reply 부분*/
	
	@RequestMapping(value="/user/api/board_reply/list.do")
	public @ResponseBody ModelMap apireplyList(@ModelAttribute("UserBoardReplyVo")UserBoardReplyVo UserBoardReplyVo , HttpServletRequest request , HttpServletResponse response) throws Exception{
		
		ModelMap returnMap = new ModelMap();
		
		returnMap = userBoardDataService.getReplyAllList(UserBoardReplyVo);
		
		return returnMap;
		
	}
	
	@RequestMapping(value="/user/board_reply/insert.do" , method = RequestMethod.POST)
	public void BoardReplyInsert(@ModelAttribute("UserBoardReplyVo")UserBoardReplyVo UserBoardReplyVo , HttpServletRequest request , HttpServletResponse response) {
		
		String member_id = SUtil.getUserId(request);
		UserBoardReplyVo.setMember_id(member_id);
		
		//댓글 등록
		userBoardDataService.setBoardReplyDataInsert(UserBoardReplyVo);
		
	}
	
	@RequestMapping(value ="/user/board_reply/update.do" , method = RequestMethod.POST)
	public void BoardReplyUpdate(@ModelAttribute("UserBoardReplyVo")UserBoardReplyVo UserBoardReplyVo , HttpServletRequest request , HttpServletResponse response) {
		
		String member_id = SUtil.getUserId(request);
		UserBoardReplyVo.setMember_id(member_id);
		
		//댓글 수정
		userBoardDataService.setBoardReplyDataUpdate(UserBoardReplyVo);
		
	}
	
	@RequestMapping(value ="/user/board_reply/delete.do" , method = RequestMethod.POST)
	public void BoardReplyDelete(@ModelAttribute("UserBoardReplyVo")UserBoardReplyVo UserBoardReplyVo , HttpServletRequest request , HttpServletResponse response) {
		
		String member_id = SUtil.getUserId(request);
		
		UserBoardReplyVo.setMember_id(member_id);
		
		//댓글 삭제 (1개)
		userBoardDataService.DelBoardReplyData(UserBoardReplyVo, "one");
		
	}
	
	
}
