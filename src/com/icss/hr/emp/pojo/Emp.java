package com.icss.hr.emp.pojo;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.job.pojo.Job;

/**
 * 员工实体类 
 */
public class Emp {
	
	private int empId;
	private String empName;
	private String empLoginName;
	private String empPwd;
	private String empEmail;
	private String phone;
	private double empSalary;
	private Dept dept;//多对一
	private Job job;//多对一
	private String empPic;
	private String empInfo;
	
	public Emp() {
		super();
	}

	public Emp(int empId, String empName, String empLoginName, String empPwd, String empEmail, String phone,
			double empSalary, Dept dept, Job job, String empPic, String empInfo) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empLoginName = empLoginName;
		this.empPwd = empPwd;
		this.empEmail = empEmail;
		this.phone = phone;
		this.empSalary = empSalary;
		this.dept = dept;
		this.job = job;
		this.empPic = empPic;
		this.empInfo = empInfo;
	}

	public Emp(String empName, String empLoginName, String empPwd, String empEmail, String phone, double empSalary,
			Dept dept, Job job, String empPic, String empInfo) {
		super();
		this.empName = empName;
		this.empLoginName = empLoginName;
		this.empPwd = empPwd;
		this.empEmail = empEmail;
		this.phone = phone;
		this.empSalary = empSalary;
		this.dept = dept;
		this.job = job;
		this.empPic = empPic;
		this.empInfo = empInfo;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpLoginName() {
		return empLoginName;
	}

	public void setEmpLoginName(String empLoginName) {
		this.empLoginName = empLoginName;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getEmpPic() {
		return empPic;
	}

	public void setEmpPic(String empPic) {
		this.empPic = empPic;
	}

	public String getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(String empInfo) {
		this.empInfo = empInfo;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", empName=" + empName + ", empLoginName=" + empLoginName + ", empPwd=" + empPwd
				+ ", empEmail=" + empEmail + ", phone=" + phone + ", empSalary=" + empSalary + ", dept=" + dept
				+ ", job=" + job + ", empPic=" + empPic + ", empInfo=" + empInfo + "]";
	}
	
}
