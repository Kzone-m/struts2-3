package action;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.SQLException;
import java.util.Map;

import dao.LoginDAO;
import dto.LoginDTO;


/**
 * ログインする為のアクション
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */

public class LoginAction extends ActionSupport implements SessionAware {
	
	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = -7297106149968247613L;

	/**
	 * パスワード
	 */
	private String password;
	/**
	 * ユーザー名 
	 */
	private String userName;
	/**
	 * ユーザーID
	 */
	private String userId;
	/**
	 * ログインフラッグ
	 */
	private String loginFlg;
	/**
	 * セッション
	 */
	private Map<String, Object> session;
	/**
	 * エラーメッセージ表示フラグ
	 */
	private boolean errorFlg;

	/**
	 * 実行メソッド ユーザーIDとパスワードでログインするメソッド
	 * @author KAZUNE MIYAGI
	 * @return ret ログインできればSUCCESS できなければERROR
	 * @throws SQLException エラー処理
	 */
	public String execute() throws Exception {
		String ret = ERROR;
		errorFlg = false;
		LoginDAO dao = new LoginDAO();
		LoginDTO dto = dao.select(userName, password);
		
		// ログアウトしないで退出した可能性もあるので、セッションのユーザーIDとログインフラグを使って確認する
		int userId = 0;
		
		if(session.containsKey("userId")){
			userId = (int) session.get("userId");
		}
		
		if(userId != 0){
			if(userId == dto.getUserId() && dto.getLoginFlg() == "1"){
				if(!dao.updateClearLoginFlg(userId)){
					// 例外が発生したのでエラーを返す
					return ERROR;
				}else{
					((SessionMap<String, Object>)session).invalidate();
				}
			}
		}
		
		if (userName.equals(dto.getUserName())) {
			if (password.equals(dto.getPassword())) {
				if (dao.updateLoginFlg(userName, password)) {
					session.put("userId", dto.getUserId());
					ret = SUCCESS;
				} else {
					// ログインフラグがアップデート出来なかったのでエラー
					errorFlg = true;
				}
			} else {
				// パスワードが違うのでエラー
				errorFlg = true;
			}
		} else {
			// ユーザー名が違うのでエラー
			errorFlg = true;
		}
		return ret;
	}


	/**
	 * パスワードを取得します。
	 * @return password パスワード
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * パスワードを設定します。
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
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

	/**
	 * 顧客IDを取得します。
	 * @return userId 顧客ID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 顧客IDを設定します。
	 * @param userId 顧客ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ログインフラグを取得します。
	 * @return loginFlg ログインフラグ
	 */
	public String getLoginFlg() {
		return loginFlg;
	}
	/**
	 * ログインフラッグを設定します。
	 * @param loginFlg ログインフラグ
	 */
	public void setLoginFlg(String loginFlg) {
		this.loginFlg = loginFlg;
	}

	/**
	 * エラーフラグを取得
	 * @return errorFlg エラーフラグ
	 */
	public boolean isErrorFlg() {
		return errorFlg;
	}
	/**
	 * エラーフラグを設定
	 * @param errorFlg エラーフラグ
	 */
	public void setErrorFlg(boolean errorFlg) {
		this.errorFlg = errorFlg;
	}
	
	/**
	 * セッションを取得します。
	 * @return session セッション
	 */
	public Map<String, Object> getSession() {
		return session;
	}
	/**
	 * セッションを設定します。
	 * @param session セッション
	 */
	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}
}
