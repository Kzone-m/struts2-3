package action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import dao.LogoutDAO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ログアウトする為のアクション
 * @author KAZUNE MIYAGI
 * @since 2017/04/18
 * @version 1.1
 *
 */

public class LogoutAction extends ActionSupport implements SessionAware{
	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = -5523692462762364548L;
	/**
	 * セッション
	 */
	private Map<String, Object> session;


	/**
	 * 実行メソッド
	 * ユーザーの意思によるログアウト、もしくは予期せぬエラーが発生したための強制的なログアウト
	 * @author KAZUNE MIYAGI
	 * @return result 予期せぬエラーが発生した場合やユーザー認証が取れない場合はERROR、それ以外の場合はSUCCESSを返す
	 * @throws SQLException
	 */
	public String execute()throws SQLException{

		String result = ERROR;
		LogoutDAO dao = new LogoutDAO();
		int userId = 0;
		if(session.containsKey("userId")){
			userId = (int) session.get("userId");
		}else{
			((SessionMap<String, Object>)session).invalidate();
			return result;
		}
		if(dao.updateLoginFlg(userId)){
			((SessionMap<String, Object>)session).invalidate();
			result = SUCCESS;
		}
		return result;
	}

	/**
	 * セッションを取得します。
	 * @return session セッション
	 */
	public Map<String, Object> getSession(){
		return session;
	}
	/**
	 * セッションを設定します。
	 * @param session セッション
	 */
	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>)session;

	}
}

