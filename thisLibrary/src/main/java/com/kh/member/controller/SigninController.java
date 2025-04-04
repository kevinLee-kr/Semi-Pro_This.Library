package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class SigninController
 */
@WebServlet("/signin.me")
public class SigninController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memName = request.getParameter("name");
		String memId = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String memPwd = request.getParameter("password");
		String address = request.getParameter("address");
		String phone = request.getParameter("email");
		String email = request.getParameter("phone");
		String snsKey = request.getParameter("key");

		Member m = new Member(memName, memId, memPwd, nickname,  address, phone, email, snsKey);

		int result = new MemberService().insertMember(m);
		if(result > 0) { // 성공적으로 회원가입 완료 됨 (Member 테이블에 Insert 완료 됨)
	         
	         request.getSession().setAttribute("alertMsg", "회원가입성공");
	         response.sendRedirect(request.getContextPath()+"/views/common/mainPage.jsp");
	      }else {
	         request.setAttribute("alertMsg", "회원가입실패");
	         request.getRequestDispatcher("views/member/signin.jsp").forward(request, response);
	      }
	   }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
