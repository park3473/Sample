package egovframework.sample.user.board.model;

import com.system.util.SearchVo;

public class UserBoardVo extends SearchVo {

	String idx = "";			//게시판 번호
	String level = "";			//게시판 레벨
	String reply = "";			//댓글 여부
	String type = "";			//타입
	String name = "";			//게시판 이름
	String file = "";			//게시판 파일 여부
	String file_cnt = "";		//게시판 파일 갯수
	String create_tm = "";		//게시판 생성 시간
	String update_tm = "";		//게시판 수정 시간
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreate_tm() {
		return create_tm;
	}
	public void setCreate_tm(String create_tm) {
		this.create_tm = create_tm;
	}
	public String getUpdate_tm() {
		return update_tm;
	}
	public void setUpdate_tm(String update_tm) {
		this.update_tm = update_tm;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getFile_cnt() {
		return file_cnt;
	}
	public void setFile_cnt(String file_cnt) {
		this.file_cnt = file_cnt;
	}
	
	
	
}
