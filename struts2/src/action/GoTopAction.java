package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.SQLException;
import java.util.*;
import dto.TweetDTO;
import dao.SelectTweetDAO;

/**
 * 投稿されている最新のつぶやきを4件取得するためのアクション
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */
public class GoTopAction extends ActionSupport implements SessionAware {
	
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
	private List<TweetDTO> topFourTweets;

	
	/**
	 * ユーザーが投稿した画像ファイルとつぶやきを取得するためのメソッド
	 * @author KAZUNE MIYAGI
	 * @return return SUCCESS
	 * @throws SQLException エラー処理
	 */
	public String execute() throws SQLException{	
		SelectTweetDAO dao = new SelectTweetDAO();
		List<TweetDTO> dto = new ArrayList<TweetDTO>();
		dto = dao.selectTopNumberTweets(4);
		setTopFourTweets(dto);
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
	public List<TweetDTO> getTopFourTweets() {
		return topFourTweets;
	}
	/**
	 * 最新のツイートを５件を設定します。
	 * @param topFiveTweets 最新のツイートを５件
	 */
	public void setTopFourTweets(List<TweetDTO> topFourTweets) {
		this.topFourTweets = topFourTweets;
	}
		
}