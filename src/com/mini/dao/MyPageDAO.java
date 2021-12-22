package com.mini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.mini.beans.LectureBean;
import com.mini.beans.StudentBean;
import com.mini.beans.SubjectBean;

public class MyPageDAO {
	
	public List<LectureBean> selectCurLectureList(String stuId) {
		List<LectureBean> list = new ArrayList<LectureBean>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		LectureBean lb = null;
		ResultSet rs = null;
		String sql = 	"select	@ROWNUM:=@ROWNUM+1 as rowNo, "
					+	"i.incNo, s.subName, l.* "
					+	"from (SELECT @ROWNUM:=0) r, " 
					+	"lecture l inner join subject s "
					+	"on l.subNo = s.subNo inner join inclass i " 
					+	"on l.lecNo = i.lecNo "
					+	"where i.stuId = ? "
					+	"and i.incComp = 'N' "
					+	"order by rowNo desc";
		try {
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stuId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lb = new LectureBean();
				lb.setRowNo(rs.getInt("rowNo"));
				lb.setIncNo(rs.getString("incNo"));
				lb.setSubName(rs.getString("subName"));
				lb.setLecNo(rs.getString("lecNo"));
				lb.setLecYear(rs.getString("lecYear"));
				lb.setLecDur(rs.getString("lecDur"));
				lb.setProfessor(rs.getString("professor"));
				lb.setNowStu(rs.getString("nowStu"));
				lb.setMaxStu(rs.getString("maxStu"));
				
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


	public List<LectureBean> selectPreLectureList(String stuId) {
		List<LectureBean> list = new ArrayList<LectureBean>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		LectureBean lb = null;
		ResultSet rs = null;
		String sql = "select@ROWNUM:=@ROWNUM+1 as rowNo,l.*,s.subName,i.incGrade "
				   + "from (SELECT @ROWNUM:=0) r, lecture l inner join subject s " 
				   + "on l.subNo = s.subNo inner join inclass i on l.lecNo = i.lecNo " 
				   + "where i.stuId = ? and i.incComp = 'Y' order by rowNo desc" ;
		try {
			
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stuId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lb = new LectureBean();
				lb.setRowNo(rs.getInt("rowNo"));
				lb.setLecNo(rs.getString("lecNo"));
				lb.setLecYear(rs.getString("lecYear"));
				lb.setLecDur(rs.getString("lecDur"));
				lb.setProfessor(rs.getString("professor"));
				lb.setSubName(rs.getString("subName"));
				lb.setIncGrade(rs.getString("incGrade"));
				lb.setNowStu(rs.getString("nowStu"));
				lb.setMaxStu(rs.getString("maxStu"));
						
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
}



