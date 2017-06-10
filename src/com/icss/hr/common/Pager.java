package com.icss.hr.common;
/**
 * 分页工具类
 * @author Administrator
 *
 */
public class Pager {
	
	public int recordCount;//总记录数
	
	private int pageSize;//每页多少条
	
	private int pageCount;//共几页
	
	private int pageNum;//当前页码
	
	private int start;//记录起始页
	
	private int end;//记录终止数
	
	public Pager(int recordCount,int pageSize,int pageNum){
		
		if(recordCount == 0){
			
			return;
		}
		
//		属性赋值
		this.recordCount = recordCount;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		
		if(this.recordCount < this.pageSize){
			this.pageSize = this.recordCount;
		}
		
//		计算共几页
		this.pageCount = this.recordCount / this.pageSize;
		if(this.pageCount % this.pageSize != 0){
			this.pageCount ++;
		}
		
//		计算当前页码	
		if(this.pageNum < 1){
			this.pageNum = 1;
		}
		if(this.pageNum > this.pageCount){
			this.pageNum = this.pageCount;
		}
		
//		计算起始和终止位置
		this.start =  (this.pageNum - 1) * this.pageSize + 1;
		if(pageNum != this.pageCount){
			this.end = this.pageNum * this.pageSize;
		}else{
			this.end = recordCount;
		}
		
		
		
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
}
