package com.mini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mini.util.UtilTools;

import com.mini.beans.LectureBean;

public class LectureDAO {

	public List<LectureBean> selectLectureList() {
		List<LectureBean> list = new ArrayList<LectureBean>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		LectureBean lb = null;
		ResultSet rs = null;
		String sql = "select @ROWNUM:=@ROWNUM+1 as rowNo, s.subName, l.* "
					+	"from (SELECT @rownum:=0) r, "
					+	"lecture l inner join subject s " 
					+	"on l.subNo = s.subNo "
					+	"where l.lecUseYn = 'Y' and l.maxStu >= l.nowStu "
					+	"order by rowNo desc";
		try {
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lb = new LectureBean();
				lb.setRowNo(rs.getInt("rowNo"));
				lb.setLecNo(rs.getString("lecNo"));
				lb.setSubNo(rs.getString("subNo"));
				lb.setNowStu(rs.getString("nowStu"));
				lb.setMaxStu(rs.getString("maxStu"));
				lb.setLecYear(rs.getString("lecYear"));
				lb.setLecDur(rs.getString("lecDur"));
				lb.setProfessor(rs.getString("professor"));
				lb.setLecDay(rs.getString("lecDay"));
				lb.setLecStart(UtilTools.toTimeFour(rs.getString("lecStart")));
				lb.setLecEnd(UtilTools.toTimeFour(rs.getString("lecEnd")));
				lb.setLecRoom(rs.getString("lecRoom"));
				lb.setLecUseYn(rs.getString("lecUseYn"));
				lb.setSubName(rs.getString("subName"));
				
				list.add(lb);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	public LectureBean selectLectureOne(String lecNo) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		LectureBean lb = new LectureBean();
		ResultSet rs = null;
		String sql = "select * from lecture where lecNo = ?";
		try {
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lecNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lb.setLecNo(rs.getString("lecNo"));
				lb.setSubNo(rs.getString("subNo"));
				lb.setNowStu(rs.getString("nowStu"));
				lb.setMaxStu(rs.getString("maxStu"));
				lb.setLecYear(rs.getString("lecYear"));
				lb.setLecDur(rs.getString("lecDur"));
				lb.setProfessor(rs.getString("professor"));
				lb.setLecDay(rs.getString("lecDay"));
				lb.setLecStart(UtilTools.toTimeFour(rs.getString("lecStart")));
				lb.setLecEnd(UtilTools.toTimeFour(rs.getString("lecEnd")));
				lb.setLecRoom(rs.getString("lecRoom"));
				lb.setLecUseYn(rs.getString("lecUseYn"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lb;
	}

}
