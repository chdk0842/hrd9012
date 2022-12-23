package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;
import DTO.Vote;

public class MemberDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 데이터 베이스 연결
	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "system", "sys1234");
		return con;
	}

	// 후보 조회
	public String selectAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			conn = getConnection();
			String sql = "SELECT M.M_NO AS 후보번호, M.M_NAME AS 성명, P.P_NAME AS 소속정당, ";
			sql += "DECODE(P_SCHOOL,'1','고졸','2','학사','3','석사','4','박사') AS 학력, ";
			sql += "SUBSTR(M.M_jumin,1,6)||'-'||SUBSTR(M.M_jumin,7) AS 주민번호, M.M_CITY AS 지역구, ";
			sql += "SUBSTR(P.p_tel1,1,2)||'-'||P.p_tel2||'-'||(substr(P.p_tel3,4)||substr(P.p_tel3,4)||substr(P.p_tel3,4)||substr(P.p_tel3,4)) AS 전화번호 ";
			sql += "FROM TBL_MEMBER_202005 M ";
			sql += "JOIN TBL_PARTY_202005 P ";
			sql += "ON(M.P_CODE = P.P_CODE) ";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Member member = new Member();
				member.setM_no(rs.getString(1));
				member.setM_name(rs.getString(2));
				member.setP_code(rs.getString(3));
				member.setP_shool(rs.getString(4));
				member.setM_jumin(rs.getString(5));
				member.setM_city(rs.getString(6));
				member.setTel(rs.getString(7));

				list.add(member);
			}
			System.out.println(list);
			request.setAttribute("list", list);

			conn.close();
			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list.jsp";
	}

	// 투표하기
	public int insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String v_jumin = request.getParameter("v_jumin");
		String v_name = request.getParameter("v_name");
		String m_no = request.getParameter("m_no");
		String v_time = request.getParameter("v_time");
		String v_area = request.getParameter("v_area");
		String v_confirm = request.getParameter("v_confirm");
		int result = 0;

		try {
			conn = getConnection(); // db연결
			String sql = "INSERT INTO TBL_VOTE_202005 values (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, v_jumin);
			ps.setString(2, v_name);
			ps.setString(3, m_no);
			ps.setString(4, v_time);
			ps.setString(5, v_area);
			ps.setString(6, v_confirm);

			// insert, update, delete: 성공한 레코드의 갯수를 반환
			result = ps.executeUpdate();
			System.out.println(result);

			conn.close();
			ps.close();
//					rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 투표검수조회
	public String voteListAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Vote> votelist = new ArrayList<Vote>();
		try {
			conn = getConnection();
               String sql ="SELECT V_NAME, 19|| substr(v_jumin,1,2)||'년'||"; 
               sql += "substr(v_jumin,3,2)||'월'|| substr(v_jumin,5,2)||'일생'"; 
               sql += ",'만 '||(TO_NUMBER(TO_CHAR(SYSDATE,'YYYY'))"; 
               sql += "-TO_NUMBER('19'||SUBSTR(V_JUMIN,1,2)))||'세' V_AGE,"; 
               sql += " DECODE(SUBSTR(V_JUMIN,7,1),'1','남','2','여')V_GENDER, M_NO, "; 
               sql += " substr(v_time,1,2)||':'||substr(v_time,3,2)v_time,"; 
               sql += " DECODE(V_CONFIRM,'Y','확인','미확인')"; 
               sql += " FROM TBL_VOTE_202005"; 
               sql += " WHERE V_AREA ='제1투표장'";
			 
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Vote vote = new Vote();
				vote.setV_name(rs.getString(1));
				vote.setV_jumin(rs.getString(2));
				vote.setV_age(rs.getString(3));
				vote.setV_gender(rs.getString(4));
				vote.setM_no(rs.getString(5));
				vote.setV_time(rs.getString(6));
				vote.setV_confirm(rs.getString(7));

				votelist.add(vote);
			}
			System.out.println(votelist);
			request.setAttribute("votelist", votelist);

			conn.close();
			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "votelist.jsp";
	}

	// 후보자등수
	public String scoreAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Member> scorelist = new ArrayList<Member>();
		try {
			conn = getConnection();
			String sql = "SELECT M.M_NO , M.M_NAME, COUNT(V.M_NO) ";
			sql += "FROM TBL_MEMBER_202005 M ";
			sql += "JOIN TBL_VOTE_202005 V ";
			sql += "ON(M.M_NO = V.M_NO) ";
			sql += "GROUP BY V.M_NO, M.M_NO, M.M_NAME ORDER BY COUNT(V.M_NO) DESC ";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Member member = new Member();
				member.setM_no(rs.getString(1));
				member.setM_name(rs.getString(2));
				member.setScore(rs.getString(3));

				scorelist.add(member);
			}
			System.out.println(scorelist);
			request.setAttribute("scorelist", scorelist);

			conn.close();
			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listscore.jsp";
	}
	
}
