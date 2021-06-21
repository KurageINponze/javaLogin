package chikuwa.minami.login.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAOクラスの親クラス
 *
 */
public class ParentDAO {
	/**
	 * DBコネクションオブジェクト
	 */
	protected Connection _con;

	/**
	 * コンストラクタ
	 */
	public ParentDAO(Connection con) {
		_con = con;
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
}
