package egovframework.sample.user.board.model;

public class UserBoardReplyVo {

	String idx = "";			//댓글 번호
	String board_idx=  "";		//게시판 번호
	String board_data_idx = "";	//게시글 번호
	String reply_idx = "";		//댓글 번호 (대댓글)
	String depth = "";			//단계
	String content = "";		//내용
	String member_id = "";		//작성자 아이디
	String name = "";			//작성자 이름
	String create_tm = "";		//생성 시간
	String update_tm = "";		//수정 시간
	
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
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
	public String getReply_idx() {
		return reply_idx;
	}
	public void setReply_idx(String reply_idx) {
		this.reply_idx = reply_idx;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
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
	
	
	
}
