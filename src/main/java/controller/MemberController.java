package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

@WebServlet("/")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String context = request.getContextPath(); // 톰캣의 context path를 가져온다.(server.xml에서 확인)
		String command = request.getServletPath(); // 경로의 맨 끝 파일명을 가져온다.
		String site = null;

		System.out.println(context + ", " + command);

		MemberDAO member = new MemberDAO();

		switch (command) {
		case "/home":
			site = "index.jsp";
			break;
		case "/list":
			site = member.selectAll(request, response);
			break;
		case "/add":
			site = "add.jsp";
			break;
		case "/insert":
			int result = member.insert(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();

			if (result == 1) { // 업데이트 성공
				out.println("<script>");
				out.println(" alert('투표하기 정보가 정상적으로 등록 되었습니다!'); location.href='" + context + "';  ");
				out.println("</script>");
				out.flush();
			} else { // 업데이트 실패
				out.println("<script>");
				out.println("alert('투표하기 정보가 등록 실패했습니다.'); location.href='" + context + "'; ");
				out.println("</script>");
				out.flush();
			}
			break;

		case "/votelist":
			site = "votelist.jsp";
			break;
		}
		getServletContext().getRequestDispatcher("/" + site).forward(request, response);

	}

}
