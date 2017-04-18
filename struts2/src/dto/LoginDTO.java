package dto;

/**
 * LoginDTO ユーザー情報の値を格納するクラス
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */

public class LoginDTO {
	/**
	 * パズワード
	 */
	private String password;
	/**
	 * ユーザー名
	 */
	private String userName;
	/**
	 * ユーザーID
	 */
	private int userId;
	/**
	 * ログインフラグ
	 */
	private String loginFlg;


	/**
	 * passwordを取得します。
	 * @return password
	 */
	public String getPassword() {
	    return password;
	}
	/**
	 * passwordを設定します。
	 * @param password password
	 */
	public void setPassword(String password) {
	    this.password = password;
	}

	/**
	 * userIdを取得します。
	 * @return userId
	 */
	public int getUserId() {
	    return userId;
	}
	/**
	 * userIdを設定します。
	 * @param userId userId
	 */
	public void setUserId(int userId) {
	    this.userId = userId;
	}

	/**
	 * loginFlgを取得します。
	 * @return loginFlg
	 */
	public String getLoginFlg() {
	    return loginFlg;
	}
	/**
	 * loginFlgを設定します。
	 * @param loginFlg loginFlg
	 */
	public void setLoginFlg(String loginFlg) {
	    this.loginFlg = loginFlg;
	}

	/**
	 * userNameを取得します。
	 * @return userName ユーザー名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * userNameを設定します。
	 * @param userName ユーザー名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}

