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

public class MemberDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe","system","sys1234");
		return con;
	}
	
	//후보 조회
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
	
	//투표하기
	public int insert(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
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
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return result;
			}
	
	//투표검수조회
	
	
}
