package com.system.util;

public class PageVO {

	int itemtotalcount;
	int itemCount;
	int itempage;
	int itempagestart;
	int itempageend;
	int itemtotalpage;
	boolean itempagenext = true;
	
	public int getItemtotalcount() {
		return itemtotalcount;
	}

	public void setItemtotalcount(int itemtotalcount) {
		this.itemtotalcount = itemtotalcount;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getItempage() {
		return itempage;
	}

	public void setItempage(int itempage) {
		this.itempage = itempage;
	}

	public int getItempagestart() {
		return itempagestart;
	}

	public void setItempagestart(int itempagestart) {
		this.itempagestart = itempagestart;
	}

	public int getItempageend() {
		return itempageend;
	}

	public void setItempageend(int itempageend) {
		this.itempageend = itempageend;
	}

	public boolean isItempagenext() {
		return itempagenext;
	}

	public void setItempagenext(boolean itempagenext) {
		this.itempagenext = itempagenext;
	}

	public int getItemtotalpage() {
		return itemtotalpage;
	}

	public void setItemtotalpage(int itemtotalpage) {
		this.itemtotalpage = itemtotalpage;
	}

	public PageVO(int itemcount, int totalcount, int page)
	{
		if(totalcount == 0)
		{
			itemtotalcount = 0 ; 
			itemCount = itemcount;
			itempage = 0 ;
			itempagestart = 0 ;
			itempageend = 0 ;
			itemtotalpage = 0 ;
			return;
		}
		
		itemtotalcount = totalcount;
		itemCount = itemcount;
		try {
			itempageend = itemtotalcount / itemCount;	
		}catch(java.lang.ArithmeticException e)
		{
			itemtotalcount = 0 ; 
			itemCount = itemcount;
			itempage = 0 ;
			itempagestart = 0 ;
			itempageend = 0 ;
			itemtotalpage = 0 ;
			return;
		}
		
		
		if(itemtotalcount % itemCount == 0)
		{
			itempageend--;
		}
		if(page < 0)
		{
			page = 0;
		}
		
		
		itemtotalpage = itemtotalcount / itemCount;
		
		if(itemtotalcount % itemCount != 0 || itemtotalcount == 0)
		{
			itemtotalpage++;
		}
		
		
		itempage = page;
		if(itempageend < itempage)
		{
			itempage = itempageend;
		}
		
		itempagestart = (itempage / itemCount) * itemCount;
		
		int send = itempagestart + 9;
		
		if(send < itempageend)
		{
			itempageend = send;
		}else
		{
			itempagenext = false;
		}
		/*System.out.println("itemtotalcount : " +  itemtotalcount);
		System.out.println("itemCount : " +  itemCount);
		System.out.println("itempage : " +  itempage);
		System.out.println("itempagestart : " +  itempagestart);
		System.out.println("itempageend : " +  itempageend);
		System.out.println("itemtotalpage : " +  itemtotalpage);*/
		
	}
	


}
