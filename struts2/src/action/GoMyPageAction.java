package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.SQLException;
import java.util.*;

import dto.GoMyPageDTO;
import dao.GoMyPageDAO;

/**
 * マイページへ遷移するためのアクション
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */
public class GoMyPageAction extends ActionSupport implements SessionAware { 
	
	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = 1579772173104125826L;
	/**
	 * セッション
	 */
	private Map<String, Object> session;
	/**
	 * ユーザーの登録情報
	 */
	private List<GoMyPageDTO> dtoLst;
	
	/**
	 * ユーザーの登録情報の取得しマイページへ遷移するメソッド
	 * @author KAZUNE MIYAGI
	 * @return return SUCCESS
	 * @throws SQLException エラー処理
	 */
	public String execute() throws SQLException{
		int userId = 0;
		if(session.containsKey("userId")){
			userId = (int) session.get("userId");
		}	
		GoMyPageDAO dao = new GoMyPageDAO();
		dtoLst = new ArrayList<GoMyPageDTO>();
		dtoLst.add(dao.selectUserInfo(userId));
		return SUCCESS;
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
	
	/**
	 * ユーザーの登録情報を取得します。
	 * @return dtoList ユーザーの登録情報
	 */
	public List<GoMyPageDTO> getDtoLst() {
		return dtoLst;
	}
	/**
	 * ユーザーの登録情報を設定します。
	 * @param dtoLst ユーザーの登録情報
	 */
	public void setDtoLst(List<GoMyPageDTO> dtoLst) {
		this.dtoLst = dtoLst;
	}

}
