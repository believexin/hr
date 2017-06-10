package com.icss.hr.job.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.hr.common.DbUtil;
import com.icss.hr.job.pojo.Job;

public class JobDao {

	public void insert(Job job) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "insert into job values(job_seq.Nextval,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, job.getJobName());
		pstmt.setInt(2, job.getJobMinSal());
		pstmt.setInt(3, job.getJobMaxSal());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	public void delete(int jobId) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "delete from job where job_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, jobId);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	public void update(Job job) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "update job set job_name=?,job_min_sal=?,job_max_sal=? where job_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, job.getJobName());
		pstmt.setInt(2, job.getJobMinSal());
		pstmt.setInt(3, job.getJobMaxSal());
		pstmt.setInt(4, job.getJobId());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();

	}
	public Job query(int jobId) throws SQLException{
		Connection conn = DbUtil.getConnection();
		String sql = "select * from job where job_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, jobId);
		ResultSet rs = pstmt.executeQuery();
		Job job = new Job();
		if(rs.next()){
			job.setJobId(jobId);
			job.setJobName(rs.getString(2));
			job.setJobMinSal(rs.getInt(3));
			job.setJobMaxSal(rs.getInt(4));			
		}
		rs.close();
		pstmt.close();
		conn.close();
		return job;
	}
	public ArrayList<Job> queryList() throws SQLException{
		Connection conn = DbUtil.getConnection();
		String sql = "select * from job";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Job> jobList = new ArrayList<>();
		Job job = null;
		while(rs.next()){
			job = new Job(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
			jobList.add(job);
			
		}
		rs.close();
		pstmt.close();
		conn.close();
		return jobList;
	}

}
