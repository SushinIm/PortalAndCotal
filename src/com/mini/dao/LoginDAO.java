package com.mini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mini.beans.StudentBean;

public class LoginDAO {
	public StudentBean loginCheck(String stuId, String stuPw) {
		
		PreparedStatement pstmt = null;
		StudentBean sb = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "select stuId, stuName, stuStat, stuGrade, stuDept from student where stuId = ? and stuPw = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,stuId);
			pstmt.setString(2,stuPw);
			
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				sb = new StudentBean();
				sb.setStuId(rs.getString("stuId"));
				sb.setStuName(rs.getString("stuName"));	
				sb.setStuStat(rs.getString("stuStat"));	
				sb.setStuGrade(rs.getString("stuGrade"));	
				sb.setStuDept(rs.getString("stuDept"));	
			}
		}
		catch(Exception e){ 
			e.printStackTrace();
		}
		
		finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return sb;
	}
}
