package egovframework.sample.user.accept_log.model;

import com.system.util.SUtil;

public class UserAcceptLogVo {


	String IDX;
	String MEMBER_ID = "";
	String DEVICE;
	String URL;
	String METHOD;
	String PARAMETER;
	String CREATE_TM;
	String IP;

	String START_TM = SUtil.getNowDate();
	String END_TM = SUtil.getNowDate();
	
	int page;
	int pageItemCount;

	public String getSTART_TM() {
		return START_TM;
	}
	public void setSTART_TM(String sTART_TM) {
		START_TM = sTART_TM;
	}
	public String getEND_TM() {
		return END_TM;
	}
	public void setEND_TM(String eND_TM) {
		END_TM = eND_TM;
	}
	public String getIDX() {
		return IDX;
	}
	public void setIDX(String iDX) {
		IDX = iDX;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getDEVICE() {
		return DEVICE;
	}
	public void setDEVICE(String dEVICE) {
		DEVICE = dEVICE;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getMETHOD() {
		return METHOD;
	}
	public void setMETHOD(String mETHOD) {
		METHOD = mETHOD;
	}
	public String getPARAMETER() {
		return PARAMETER;
	}
	public void setPARAMETER(String pARAMETER) {
		PARAMETER = pARAMETER;
	}
	public String getCREATE_TM() {
		return CREATE_TM;
	}
	public void setCREATE_TM(String cREATE_TM) {
		CREATE_TM = cREATE_TM;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageItemCount() {
		return pageItemCount;
	}
	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}
	
	
	
}
