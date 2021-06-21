package chikuwa.minami.login.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chikuwa.minami.login.entity.User;

public class UserDAO extends ParentDAO{

/**
 * コンストラクタ
*/
public UserDAO(Connection con) {
	super(con);
}

/**
 * ログインidによる検索
 */
public User findByLoginId(String loginId) throws SQLException{
	PreparedStatement stmt = null;
	ResultSet rs = null;
	User user = null;

		String sql = "SELECT * FROM user WHERE loginId = ?";

		try {
			stmt = _con.prepareStatement(sql);

			stmt.setString(1, loginId);

			rs = stmt.executeQuery();

			if(rs.next()) {
				String login = rs.getString("loginId");
				String nameLast = rs.getString("last_name");
				String nameFirst = rs.getString("first_name");
				String passwd = rs.getString("passWd");

				user = new User();
				user.setLoginId(login);
				user.setLoginPw(passwd);
				user.setLoginFirstName(nameFirst);
				user.setLoginLastName(nameLast);
			}
		}
		finally {
			close(rs);
			close(stmt);
		}
		return user;
	}
}
