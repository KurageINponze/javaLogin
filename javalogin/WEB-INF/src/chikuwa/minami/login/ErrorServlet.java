package chikuwa.minami.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="ErrorServlet", urlPatterns={"/timeError"})
public class ErrorServlet extends ParentServlet{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String jspPath="/jsp/loginOk.jsp";
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if(id == null) {
			jspPath=LOGIN_JSP;
		}
		else {
			String msg = "セッションはまだ有効です";
			request.getAttribute(msg);
		}
		RequestDispatcher rd = request.getRequestDispatcher(jspPath);
		rd.forward(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		doPost(request,response);
	}
}
