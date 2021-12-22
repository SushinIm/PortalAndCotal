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
		String sql = "select stuId, stuName, stuStat, stuGrade, stuDept, ifnull(stuPhone, '공란') as stuPhone, stuEmail "
				+	"from student where stuId = ? and stuPw = ?";
		
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
				if("1".equals(rs.getString("stuStat"))) {
					sb.setStuStat("재학");
				}else if("2".equals(rs.getString("stuStat"))) {
					sb.setStuStat("휴학");
				}else if("3".equals(rs.getString("stuStat"))) {
					sb.setStuStat("졸업");
				}else {
					sb.setStuStat("자퇴");
				}
				sb.setStuGrade(rs.getString("stuGrade"));	
				sb.setStuDept(rs.getString("stuDept"));
				sb.setStuPhone(rs.getString("stuPhone"));
				sb.setStuEmail(rs.getString("stuEmail"));
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
