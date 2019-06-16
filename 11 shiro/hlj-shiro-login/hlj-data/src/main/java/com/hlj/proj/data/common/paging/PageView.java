package com.hlj.proj.data.common.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class PageView<T> implements Serializable{
	
	private static final long serialVersionUID = 5469885070686509407L;
	
	private int pageNo = 1;//页码，默认是第一页
    private int pageSize = 5;//每页显示的记录数，默认是5
    private int total;//总记录数
    private int totalPage;//总页数

    private List<T> rows;//对应的当前页记录
 
    
    public PageView() {
		super();
	}

	public PageView (List<T> list,Pagenation page) {
    	pageNo = page.getPageNo();
    	pageSize = page.getPageSize();
    	total = page.getItemCount();
    	totalPage = page.getPageCount();
    	if (list == null) {
    		rows = new ArrayList<T>();
    	} else {
    		rows = list;
    	}
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
 
    public int getTotal() {
       return total;
    }
 
    public void setTotal(int total) {
       this.total = total;
       //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
       int totalPage = total%pageSize==0 ? total/pageSize : total/pageSize + 1;
       this.setTotalPage(totalPage);
    }
 
    public int getTotalPage() {
       return totalPage;
    }
 
    public void setTotalPage(int totalPage) {
       this.totalPage = totalPage;
    }
 
    public List<T> getRows() {
       return rows;
    }
 
    public void setRows(List<T> rows) {
       this.rows = rows;
    }
   
    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
       builder.append("Page [pageNo=").append(pageNo).append(", pageSize=")
              .append(pageSize).append(", rows=").append(rows).append(
                     ", totalPage=").append(totalPage).append(
                     ", total=").append(total).append("]");
       return builder.toString();
    }
 
}