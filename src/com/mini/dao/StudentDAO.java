package com.mini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mini.beans.LectureBean;
import com.mini.beans.StudentBean;

public class StudentDAO {

		public StudentBean findIdInfo(String stuname, String studob, String stuphone) {
			
			PreparedStatement pstmt = null;
			Connection conn = null;
			StudentBean sb = null;
			ResultSet rs = null;
			String sql = " select stuid from student "
					+ "where stuname =? and studob = ? and stuphone = ? ";		
			try {
				
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,stuname);
				pstmt.setString(2,studob);
				pstmt.setString(3,stuphone);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					sb = new StudentBean();
					sb.setStuId(rs.getString("stuid"));								
				}
				
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return sb;
	}

	public StudentBean findPwInfo(String stuid,String stuphone){
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		StudentBean sb = null;
		ResultSet rs = null;
		String sql = " select stupw from student "
				+ " where stuid =? and stuphone = ? ";		
				
		try {
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,stuid);
			pstmt.setString(2,stuphone);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sb = new StudentBean();
				sb.setStuPw(rs.getString("stupw"));

			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return sb;
	}

}
