package com.mini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mini.beans.LectureBean;
import com.mini.util.UtilTools;

public class RegisterDAO {
	public int insertClass(String lecNo, String stuId, int nowStu, int subScore) { 
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int irs1 = 0;
		int irs2 = 0;
		String beforeSql = 	"select	ifnull(sum(s.subScore),0) as score " + 
							"from inclass i inner join lecture l " + 
							"on i.lecNo = l.lecNo inner join subject s " + 
							"on l.subNo = s.subNo " + 
							"where i.incComp = 'N' " + 
							"and i.stuId = ?";
		String insertSql = "insert into inclass values(null, ?, ?, null, default)";
		String updateSql = "update lecture set nowStu = ? where lecNo = ?";
		
		try {
			conn = DBConn.getConnection();
			
			pstmt = conn.prepareStatement(beforeSql);
			pstmt.setString(1,stuId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if((rs.getInt("score") + subScore) <= 18) {
					pstmt.close();
					
					pstmt = conn.prepareStatement(insertSql);
					pstmt.setString(1,lecNo);
					pstmt.setString(2,stuId);
					
					irs1 = pstmt.executeUpdate(); 
					
					if(irs1 > 0) {
						pstmt.close();
						pstmt = conn.prepareStatement(updateSql);
						pstmt.setInt(1, nowStu+1);
						pstmt.setString(2, lecNo);
						
						irs2 = pstmt.executeUpdate();
					}
				}else {
					return -1;
				}
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
		return irs1 * irs2;
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
			System.out.println(rs);
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
