package com.mini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDAO {
	public int insertClass(String lecNo, String stuid, int nowStu) { 
		PreparedStatement pstmt = null;
		Connection conn = null;
		int rs = 0;
		int rs2 = 0;
		String insertSql = "insert into inclass values(null, ?, ?, null, default)";
		String updateSql = "update lecture set nowStu = ? where lecNo = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1,lecNo);
			pstmt.setString(2,stuid);
			
			rs = pstmt.executeUpdate(); 
			
			if(rs > 0) {
				pstmt.close();
				pstmt = conn.prepareStatement(updateSql);
				pstmt.setInt(1, nowStu+1);
				pstmt.setString(2, lecNo);
				
				rs2 = pstmt.executeUpdate();
			}
		}
		
		catch(Exception e){ 
			e.printStackTrace();
		}
		
		finally {
			try {
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rs * rs2;
	}

	public int deleteClass(String lecNo, String incNo, int nowStu) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		int rs = 0;
		int rs2 = 0;
		String deleteSql = "delete from inclass where incNo = ?";
		String updateSql = "update lecture set nowStu = ? where lecNo = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(deleteSql);
			pstmt.setString(1,incNo);
			
			rs = pstmt.executeUpdate(); 
			
			if(rs > 0) {
				pstmt.close();
				pstmt = conn.prepareStatement(updateSql);
				pstmt.setInt(1, nowStu-1);
				pstmt.setString(2, lecNo);
				
				rs2 = pstmt.executeUpdate();
			}
		}
		
		catch(Exception e){ 
			e.printStackTrace();
		}
		
		finally {
			try {
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rs * rs2;
	}
}
