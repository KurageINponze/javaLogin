package chikuwa.minami.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * サーブレットの親クラス
 * DB接続処理、切断処理など
 *
 */
public class ParentServlet extends HttpServlet{
	/**
	 * エラー画面JSPパス
	 */
	protected static final String ERROR_JSP = "/error.jsp";

	/**
	 * データソース名
	 */
	protected static final String DATASOURCE_NAME = "java:comp/env/logindb";

	/**
	 * ログイン画面
	 */
	protected static final String LOGIN_JSP = "/index.jsp";

	/**
	 * データソースからConnectionオブジェクトを取得するメソッド
	 */
	protected Connection getConnection() throws NamingException, SQLException{
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(DATASOURCE_NAME);
		Connection con = ds.getConnection();
		return con;
	}

	/**
	 * DBとの接続を切断
	 */
	protected void close(Connection con) {
		if(con != null) {
			try {
				con.close();
			}
			catch(SQLException ex) {
				System.out.println("DB接続切断中にSQLExceptionが発生しました: " + ex.getMessage());
			}
		}
	}

	/**
	 * PreparedStatementオブジェクトをクローズするメソッド
	 */
	protected void close(PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
			}
			catch(SQLException ex) {
				System.out.println("Statementオブジェクト切断中にSQLExceptionが発生しました: " + ex.getMessage());
			}
		}
	}

	/**
	 * ResultSetオブジェクトをクローズするメソッド
	 */
	protected void close(ResultSet rs){
		if(rs != null) {
			try {
				rs.close();
			}
			catch(SQLException ex) {
				System.out.println("ResultSetオブジェクト切断中にSQLExceptionが発生しました: " + ex.getMessage());
			}
		}
	}

	/**
	 * トランザクションをコミットするメソッド
	 */
	protected void commit(Connection con) {
		if(con != null) {
			try {
				con.commit();
				System.out.println("コミットしました");
			}
			catch(SQLException ex) {
				System.out.println("コミット中にSQLExceptionが発生しました: " + ex.getMessage());
			}
		}
	}

	/**
	 * トランザクションをロールバックするメソッド
	 */
	protected void rollback(Connection con) {
		if(con != null) {
			try {
				con.rollback();
				System.out.println("ロールバックしました");
			}
			catch(SQLException ex) {
				System.out.println("ロールバック中にSQLExceptionが発生しました: " + ex.getMessage());
			}
		}
	}

	/**
	 * 未ログインかチェック
	 */
	protected boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Boolean loginFlg = (Boolean) session.getAttribute("loginFlg");
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");

		boolean result = false;

		if(loginFlg == null || loginFlg == false || id == null || name == null) {
			List<String> validationMsgs = new ArrayList<>();

			validationMsgs.add("ログインしていないか、前回ログインしてから一定時間経過しています。もう一度ログインしなおしてください");

			request.setAttribute("validationMsgs", validationMsgs);
			result = true;
		}
		return result;
	}

	/**
	 * session情報の掃除
	 */
	protected void cleanSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Boolean loginFlg = (Boolean) session.getAttribute("loginFlg");
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		//sessionの破棄
		session.invalidate();

		session = request.getSession();
		session.setAttribute("loginFlg", loginFlg);
		session.setAttribute("id", id);
		session.setAttribute("name", name);
	}
}
