package com.icss.hr.leavemessage.pojo;

import java.util.Date;

import com.icss.hr.emp.pojo.Emp;

/**
 * 留言板实体类
 * @author Administrator
 *
 */
public class Lmb {
	
	private int lmbId;
	private Emp lmbEmp;
	private String lmbText;
	private Date lmbDate;
	private Emp commentEmp;
	public Lmb() {
		super();
	}
	public Lmb(Emp lmbEmp, String lmbText, Date lmbDate) {
		super();
		this.lmbEmp = lmbEmp;
		this.lmbText = lmbText;
		this.lmbDate = lmbDate;
	}
	public Lmb(int lmbId, Emp lmbEmp, String lmbText, Date lmbDate) {
		super();
		this.lmbId = lmbId;
		this.lmbEmp = lmbEmp;
		this.lmbText = lmbText;
		this.lmbDate = lmbDate;
	}
	
	public Lmb(int lmbId, Emp lmbEmp, String lmbText, Date lmbDate, Emp commentEmp) {
		super();
		this.lmbId = lmbId;
		this.lmbEmp = lmbEmp;
		this.lmbText = lmbText;
		this.lmbDate = lmbDate;
		this.commentEmp = commentEmp;
	}
	public Lmb(Emp lmbEmp, String lmbText, Date lmbDate, Emp commentEmp) {
		super();
		this.lmbEmp = lmbEmp;
		this.lmbText = lmbText;
		this.lmbDate = lmbDate;
		this.commentEmp = commentEmp;
	}
	public int getLmbId() {
		return lmbId;
	}
	public void setLmbId(int lmbId) {
		this.lmbId = lmbId;
	}
	public Emp getLmbEmp() {
		return lmbEmp;
	}
	public void setLmbEmp(Emp lmbEmp) {
		this.lmbEmp = lmbEmp;
	}
	public String getLmbText() {
		return lmbText;
	}
	public void setLmbText(String lmbText) {
		this.lmbText = lmbText;
	}
	public Date getLmbDate() {
		return lmbDate;
	}
	public void setLmbDate(Date lmbDate) {
		this.lmbDate = lmbDate;
	}
	
	public Emp getCommentEmp() {
		return commentEmp;
	}
	public void setCommentEmp(Emp commentEmp) {
		this.commentEmp = commentEmp;
	}
	@Override
	public String toString() {
		return "Lmb [lmbId=" + lmbId + ", lmbEmp=" + lmbEmp + ", lmbText=" + lmbText + ", lmbDate=" + lmbDate + "]";
	}	
}
