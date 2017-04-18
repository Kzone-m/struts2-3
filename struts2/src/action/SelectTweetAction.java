package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.SQLException;
import java.util.*;
import dto.TweetDTO;
import dao.SelectTweetDAO;

/**
 * ユーザーが投稿した画像ファイルとつぶやきを取得するためのアクション
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */
public class SelectTweetAction extends ActionSupport implements SessionAware {
	
	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = 7724508330903672037L;
	/**
	 * セッション
	 */
	private Map<String, Object> session;
	/**
	 * 最新のツイートを５件
	 */
	private List<TweetDTO> topFiveTweets;
	/**
	 * ログインユーザーのツイート
	 */
	private List<TweetDTO> loginUserTweets;

	
	/**
	 * ユーザーが投稿した画像ファイルとつぶやきを取得するためのメソッド
	 * @author KAZUNE MIYAGI
	 * @return return SUCCESS
	 * @throws SQLException エラー処理
	 */
	public String execute() throws SQLException{
		int userId = 0;
		if(session.containsKey("userId")){
			userId = (int) session.get("userId");
		}		
		SelectTweetDAO dao = new SelectTweetDAO();
		List<TweetDTO> dto = new ArrayList<TweetDTO>();
		dto = dao.selectTopFiveTweets();
		setTopFiveTweets(dto);
		dto = dao.selectUserTweets(userId);
		setLoginUserTweets(dto);
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
	 * 最新のツイートを５件を取得します。
	 * @return topFiveTweets 最新のツイートを５件
	 */
	public List<TweetDTO> getTopFiveTweets() {
		return topFiveTweets;
	}
	/**
	 * 最新のツイートを５件を設定します。
	 * @param topFiveTweets 最新のツイートを５件
	 */
	public void setTopFiveTweets(List<TweetDTO> topFiveTweets) {
		this.topFiveTweets = topFiveTweets;
	}

	/**
	 * ログインユーザーのツイートを取得します。
	 * @return loginUserTweets ログインユーザーのツイート
	 */
	public List<TweetDTO> getLoginUserTweets() {
		return loginUserTweets;
	}
	/**
	 * ログインユーザーのツイートを設定します。
	 * @param loginUserTweets ログインユーザーのツイート
	 */
	public void setLoginUserTweets(List<TweetDTO> loginUserTweets) {
		this.loginUserTweets = loginUserTweets;
	}
		
}