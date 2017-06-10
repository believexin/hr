package com.icss.hr.pic.pojo;

import java.io.InputStream;
import java.util.Date;

/**
 * 图片实体类
 * @author Administrator
 *
 */
public class Pic {
	
	private int picId;
	private String picName;
	private String picInfo;
	private long picSize;
	private String picAuthor;
	private InputStream picDta;
	private Date picDatetime;
	
	
	public Pic() {
		super();
	}


	public Pic(int picId, String picName, String picInfo, long picSize, String picAuthor, InputStream picDta,
			Date picDatetime) {
		super();
		this.picId = picId;
		this.picName = picName;
		this.picInfo = picInfo;
		this.picSize = picSize;
		this.picAuthor = picAuthor;
		this.picDta = picDta;
		this.picDatetime = picDatetime;
	}


	public Pic(String picName, String picInfo, long picSize, String picAuthor, InputStream picDta, Date picDatetime) {
		super();
		this.picName = picName;
		this.picInfo = picInfo;
		this.picSize = picSize;
		this.picAuthor = picAuthor;
		this.picDta = picDta;
		this.picDatetime = picDatetime;
	}


	public int getPicId() {
		return picId;
	}


	public void setPicId(int picId) {
		this.picId = picId;
	}


	public String getPicName() {
		return picName;
	}


	public void setPicName(String picName) {
		this.picName = picName;
	}


	public String getPicInfo() {
		return picInfo;
	}


	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo;
	}


	public long getPicSize() {
		return picSize;
	}


	public void setPicSize(long picSize) {
		this.picSize = picSize;
	}


	public String getPicAuthor() {
		return picAuthor;
	}


	public void setPicAuthor(String picAuthor) {
		this.picAuthor = picAuthor;
	}


	public InputStream getPicDta() {
		return picDta;
	}


	public void setPicDta(InputStream picDta) {
		this.picDta = picDta;
	}


	public Date getPicDatetime() {
		return picDatetime;
	}


	public void setPicDatetime(Date picDatetime) {
		this.picDatetime = picDatetime;
	}


	@Override
	public String toString() {
		return "Pic [picId=" + picId + ", picName=" + picName + ", picInfo=" + picInfo + ", picSize=" + picSize
				+ ", picAuthor=" + picAuthor + ", picDta=" + picDta + ", picDatetime=" + picDatetime + "]";
	}
}
