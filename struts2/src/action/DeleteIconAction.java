package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;

import dao.DeleteIconDAO;

/**
 * アイコンを削除するためのアクション
 * @author KAZUNE MIYAGI
 * @since 2017/04/19
 * @version 1.1
 */
public class DeleteIconAction extends ActionSupport implements SessionAware {
	
	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = 8352528224499107343L;
	/**
	 * 写真を保存するディレクトリーへの絶対パス
	 */
	private final String filePath = "/Users/Kazune/Documents/workspace/struts2/WebContent/img/user_icons/user";
	/**
	 * セッション
	 */
	private Map<String, Object> session;
	
	
	public String execute() throws SQLException{
		// 戻り値を最初はSUCCESSに設定する -> エラーがでた場合は戻り値をERRORに設定する
		String result = SUCCESS;
		
		// ログインユーザーのIDを取得する -> 取得できない場合(userId = 0)は強制ログアウトをする
		int userId = 0;
		if(session.containsKey("userId")){
			userId = (int) session.get("userId");
		}
		if(userId == 0){
			return "logout";
		}
		
		// ログインユーザーの写真が保存されていディレクトリーの絶対パスを取得する
		String FileDir = filePath + String.valueOf(userId) + ".png";
		
		// DAOを作成し、まずDBに保存されているログインユーザーの写真をJSP側で取得するためのパスを書き換える -> 成功ならTrue, 失敗ならFalse
		DeleteIconDAO dao = new DeleteIconDAO();
		boolean deleteFlg = dao.deleteIconURL(userId);
		
		// 写真の削除に成功していたなら
		if(deleteFlg){
			// ユーザーが前回使っていた写真のファイルを取得し、削除する
			File file = new File(FileDir);
			if(file.exists()) file.delete();
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
		this.session = session;
	}
}
