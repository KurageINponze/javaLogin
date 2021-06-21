package chikuwa.minami.login.entity;

public class User {
	/**
	 * 	ログインID
	 */
	private String _loginId;
	/**
	 * ログインパスワード
	 */
	private String _loginPw = "";
	/**
	 * 姓
	 */
	private String _loginFirstName = "";
	/**
	 * 名
	 */
	private String _loginLastName = "";

	public String getLoginId() {
		return _loginId;
	}
	public void setLoginId(String loginId) {
		_loginId = loginId;
	}
	public String getLoginPw() {
		return _loginPw;
	}
	public void setLoginPw(String loginPw) {
		_loginPw = loginPw;
	}
	public String getLoginFirstName() {
		return _loginFirstName;
	}
	public void setLoginFirstName(String loginFirstName) {
		_loginFirstName = loginFirstName;
	}
	public String getLoginLastName() {
		return _loginLastName;
	}
	public void setLoginLastName(String loginLastName) {
		_loginLastName = loginLastName;
	}
}
