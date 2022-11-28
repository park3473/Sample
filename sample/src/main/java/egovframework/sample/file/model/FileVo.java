package egovframework.sample.file.model;

public class FileVo {
	
	String idx = "";			//파일 번호
	String type = "";			//파일 타입
	String filename = "";		//파일 이름
	String url ="";				//파일 저장 페이지
	String board_idx = "";		//게시판 번호
	String board_data_idx = "";	//게시글 번호
	String create_tm = "";		//생성 시간
	String update_tm = "";		//수정 시간
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(String board_idx) {
		this.board_idx = board_idx;
	}
	public String getBoard_data_idx() {
		return board_data_idx;
	}
	public void setBoard_data_idx(String board_data_idx) {
		this.board_data_idx = board_data_idx;
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
	
	
	
}
