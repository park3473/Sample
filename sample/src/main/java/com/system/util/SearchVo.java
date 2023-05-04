package com.system.util;

public class SearchVo {
	int PAGE;
	int LIMIT;
	int OFFSET;
	int ITEM_COUNT = 10;
	
	int ITEM_TOTAL_COUNT;
	int ITEM_PAGE;
	int ITEM_PAGE_START;
	int ITEM_PAGE_END;
	int ITEM_TOTAL_PAGE;
	
	String SEARCH_TYPE = "";
	String SEARCH_TEXT = "";
	
	String UP = "";
	String DOWN = "";
	
	
	public String getUP() {
		return UP;
	}
	public void setUP(String uP) {
		UP = uP;
	}
	public String getDOWN() {
		return DOWN;
	}
	public void setDOWN(String dOWN) {
		DOWN = dOWN;
	}

	boolean itempagenext = true;
	
	public int getPAGE() {
		return PAGE;
	}
	public void setPAGE(int pAGE) {
		OFFSET = OFFSET * (pAGE * ITEM_COUNT);
		PAGE = pAGE;
	}
	public int getLIMIT() {
		return LIMIT;
	}
	public void setLIMIT(int lIMIT) {
		LIMIT = lIMIT;
	}
	public int getOFFSET() {
		return OFFSET;
	}
	public void setOFFSET(int oFFSET) {
		OFFSET = oFFSET;
	}
	public int getITEM_COUNT() {
		return ITEM_COUNT;
	}
	public void setITEM_COUNT(int iTEM_COUNT) {
		ITEM_COUNT = iTEM_COUNT;
		OFFSET = OFFSET * (PAGE * ITEM_COUNT);
	}
	public int getITEM_TOTAL_COUNT() {
		return ITEM_TOTAL_COUNT;
	}
	public void setITEM_TOTAL_COUNT(int iTEM_TOTAL_COUNT) {
		ITEM_TOTAL_COUNT = iTEM_TOTAL_COUNT;
		
		ITEM_PAGE_END = ITEM_TOTAL_COUNT / ITEM_COUNT;
		
		if(ITEM_TOTAL_COUNT % ITEM_COUNT == 0)
		{
			ITEM_PAGE_END--;
		}
		if(PAGE < 0)
		{
			PAGE = 0;
		}
		
		
		ITEM_TOTAL_PAGE = ITEM_TOTAL_COUNT / ITEM_COUNT;
		
		if(ITEM_TOTAL_COUNT % ITEM_COUNT != 0 || ITEM_COUNT == 0)
		{
			ITEM_TOTAL_PAGE++;
		}
		
		
		ITEM_PAGE = PAGE;
		if(ITEM_PAGE_END < ITEM_PAGE)
		{
			ITEM_PAGE = ITEM_PAGE_END;
		}
		
		ITEM_PAGE_START = (ITEM_PAGE / ITEM_COUNT) * ITEM_COUNT;
		
		int send = ITEM_PAGE_START + 9;
		
		if(send < ITEM_PAGE_END)
		{
			ITEM_PAGE_END = send;
		}else
		{
			itempagenext = false;
		}
	}
	public int getITEM_PAGE() {
		return ITEM_PAGE;
	}
	public void setITEM_PAGE(int iTEM_PAGE) {
		ITEM_PAGE = iTEM_PAGE;
	}
	public int getITEM_PAGE_START() {
		return ITEM_PAGE_START;
	}
	public void setITEM_PAGE_START(int iTEM_PAGE_START) {
		ITEM_PAGE_START = iTEM_PAGE_START;
	}
	public int getITEM_PAGE_END() {
		return ITEM_PAGE_END;
	}
	public void setITEM_PAGE_END(int iTEM_PAGE_END) {
		ITEM_PAGE_END = iTEM_PAGE_END;
	}
	public int getITEM_TOTAL_PAGE() {
		return ITEM_TOTAL_PAGE;
	}
	public void setITEM_TOTAL_PAGE(int iTEM_TOTAL_PAGE) {
		ITEM_TOTAL_PAGE = iTEM_TOTAL_PAGE;
	}
	public boolean isItempagenext() {
		return itempagenext;
	}

	public void setItempagenext(boolean itempagenext) {
		this.itempagenext = itempagenext;
	}
	public String getSEARCH_TYPE() {
		return SEARCH_TYPE;
	}
	public void setSEARCH_TYPE(String sEARCH_TYPE) {
		SEARCH_TYPE = sEARCH_TYPE;
	}
	public String getSEARCH_TEXT() {
		return SEARCH_TEXT;
	}
	public void setSEARCH_TEXT(String sEARCH_TEXT) {
		SEARCH_TEXT = sEARCH_TEXT;
	}
	
	
}
