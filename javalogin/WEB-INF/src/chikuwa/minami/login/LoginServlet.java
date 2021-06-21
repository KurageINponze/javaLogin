package chikuwa.minami.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chikuwa.minami.login.UserDAO.UserDAO;
import chikuwa.minami.login.entity.User;

@WebServlet(name="LoginServlet", urlPatterns= {"/login"})
public class LoginServlet extends ParentServlet{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String jspPath ="/jsp/loginOk.jsp";

		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		loginId = loginId.trim();
		loginPw = loginPw.trim();

		List<String> validationMsgs = new ArrayList<>();
		Connection con = null;
		try {
			con = getConnection();
			UserDAO userDAO = new UserDAO(con);
			User user = userDAO.findByLoginId(loginId);
			if(user == null) {
				jspPath ="/index.jsp";
				validationMsgs.add("存在しないIDです。正しいIDを入力してください");
			}
			else {
				String userPw = user.getLoginPw();
				if(loginPw.equals(userPw)) {
					String id = user.getLoginId();
					String nameFirst = user.getLoginFirstName();
					String nameLast = user.getLoginLastName();

					HttpSession session = request.getSession();
					session.setAttribute("loginFlg", true);
					session.setAttribute("id", id);
					session.setAttribute("name", nameLast + " " + nameFirst);
				}
				else {
					jspPath ="/index.jsp";
					validationMsgs.add("パスワードが違います。正しいパスワードを入力してください");
				}

			}
		}
		catch(NamingException ex) {
			jspPath = ERROR_JSP;
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします");
		}
		catch(SQLException ex) {
			jspPath = ERROR_JSP;
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします");
		}
		catch(Exception ex) {
			jspPath = ERROR_JSP;
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします");
		}
		finally {
			close(con);
		}
			request.setAttribute("validationMsgs", validationMsgs);
			request.setAttribute("loginId", loginId);
			RequestDispatcher rd = request.getRequestDispatcher(jspPath);
			rd.forward(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		doPost(request,response);
	}
}
