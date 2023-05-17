package egovframework.sample.admin.exam.model;

import com.system.util.SearchVo;

public class AdminExamVo extends SearchVo{

	String idx = "";
	String name = "";
	String l_category = "";
	String m_category = "";
	String type = "";
	String content = "";
	String create_tm = "";
	String update_tm = "";
	
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getL_category() {
		return l_category;
	}
	public void setL_category(String l_category) {
		this.l_category = l_category;
	}
	public String getM_category() {
		return m_category;
	}
	public void setM_category(String m_category) {
		this.m_category = m_category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
