package com.mini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mini.beans.SubjectBean;

public class SubjectDAO {

	public SubjectBean selectSubjectOne(String subNo) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		SubjectBean sb = new SubjectBean();
		ResultSet rs = null;
		String sql = "select * from subject where subNo = ?";
		try {
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sb.setSubNo(rs.getString("subNo"));
				sb.setSubName(rs.getString("subName"));
				if("1".equals(rs.getString("subPart"))) {
					sb.setSubPart("전공");
				}else if("2".equals(rs.getString("subPart"))) {
					sb.setSubPart("교양");
				}else if("3".equals(rs.getString("subPart"))) {
					sb.setSubPart("일반선택");
				}else if("4".equals(rs.getString("subPart"))) {
					sb.setSubPart("군사");
				}else {
					sb.setSubPart("미분류");
				}
				sb.setSubScore(rs.getString("subScore"));
				sb.setSubPrice(rs.getString("subPrice"));
				sb.setSubGrade(rs.getString("subGrade"));
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
