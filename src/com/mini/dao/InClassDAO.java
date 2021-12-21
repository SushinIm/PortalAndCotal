package com.mini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mini.beans.InClassBean;

public class InClassDAO {

	public InClassBean selectEdLecture(String subNo, String stuId) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		InClassBean icb = null;
		String sql = "select * from inclass where stuId = ? and lecNo in(select lecNo from lecture where subNo = ?)";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stuId);
			pstmt.setString(2, subNo);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				icb = new InClassBean();
				icb.setIncNo(rs.getString("incNo"));
				icb.setLecNo(rs.getString("lecNo"));
				icb.setStuId(rs.getString("stuId"));
				icb.setIncGrade(rs.getString("incGrade"));
				icb.setIncComp(rs.getString("incComp"));
				if("Y".equals(rs.getString("incComp"))) {
					icb.setTakeFlag("Y");
				}
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return icb;
	}

}
