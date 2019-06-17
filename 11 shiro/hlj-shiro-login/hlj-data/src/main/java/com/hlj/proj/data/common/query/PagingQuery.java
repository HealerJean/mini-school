package com.hlj.proj.data.common.query;

import java.io.Serializable;


/**
 * 分页查询
 * @author YuYue
 *
 */
public abstract class PagingQuery implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int pageNo;
	private int pageSize;
	
	/**
	 * 数据记录总数
	 */
	private int itemCount;
	private int startRow;
	private int endRow;
	
	public PagingQuery(int pageNo, int pageSize){
		this.pageNo=pageNo;
		this.pageSize=pageSize;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
    public int getItemCount() {
		return itemCount;
	}
    
    public void setItemCount(int itemCount){
    	this.itemCount = itemCount;
		/*替换oracle分页为mysql  2017-11-14 15:19:13  by shuai.hao
		startRow = this.getStartIndex()< 1 ? 0 : this.getStartIndex() - 1;
		endRow = this.getEndIndex();*/
    	startRow = (pageNo - 1) * pageSize;
    	endRow = pageSize;
    }

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	private int getStartIndex() {
        return (pageNo - 1) * pageSize + 1;
    }
    
	private int getEndIndex() {
        int end = pageNo * pageSize;
        if (end > itemCount) {
            end = itemCount;
        }
        return end;
    }
}
