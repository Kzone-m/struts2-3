package action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File; 
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import dao.InsertTweetDAO;

/**
 * ユーザーが投稿した画像ファイルとつぶやきを保存するためのアクション
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */

public class InsertTweetAction extends ActionSupport implements SessionAware {
	
	/**
	 * 写真を保存するディレクトリー
	 */
	private final String filePath = "/Users/Kazune/Documents/workspace/struts2/WebContent/img/user_images/user";
	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = 6177458505745096750L;
	/**
	 * セッション
	 */
	private Map<String, Object> session;
	/**
	 * ユーザーがアップロードした画像ファイル群
	 */
	private File[] userImage;  
	/**
	 * 画像ファイルの拡張子
	 */
    private String[] userImageContentType;
    /**
     * 画像ファイルの名前
     */
    private String[] userImageFileName;
    /**
     * ユーザーのつぶやき
     */
    private String tweet;
    /**
     * ツイートの公開範囲
     */
    private String openRange;
	
    
	/**
	 * 実行メソッド ユーザーが投稿したつぶやきと写真をDBに格納する
	 * @author KAZUNE MIYAGI
	 * @return result 成功ならSUCCESS, 失敗ならERROR 
	 * @throws SQLException エラー処理
	 */
	public String execute() throws SQLException{
		String result = SUCCESS;
		int userId = 0;
		int tweetId = 0;
		if(session.containsKey("userId")){
			userId = (int) session.get("userId");
		}
		
		InsertTweetDAO dao = new InsertTweetDAO();
		if(dao.insertTweet(userId, tweet, openRange)){
			// ツイートIDの取得
			tweetId = dao.selectTweetId(userId);
			if(tweetId != 0){
				try{
					String newFileDir = null;
					String newFileName = null;
		    		BufferedImage bImage = null;
		    		for(int i = 0; i < userImage.length; i++){
		    			
		    			// 画像ファイルの保存先のパス名を取得
		    			newFileDir = filePath + String.valueOf(userId) + "/" + userImageFileName[i];
		    			
		    			// 画像ファイルを取得するためのパスを取得
		    			newFileName = "img/user_images/user" + String.valueOf(userId) + "/" + userImageFileName[i];
		    			
		    			// バッファに画像ファイルを保存する
		        		bImage = ImageIO.read(userImage[i]);
		        		
		        		// 画像ファイルの保存に失敗した場合はエラーを返し、ループから抜ける
		        		if(!ImageIO.write(bImage, "png", new File(newFileDir))){
		        			result = ERROR;
		        			break;
		        		}
		        		
		        		// 何らかの理由でDBに画像ファイルのパスの保存に失敗した場合は、エラーを返し、ループから抜ける
		        		if(!dao.insertImage(tweetId, newFileName)){
		        			result = ERROR;
		        		    break;
		        		}
		    		}
				}catch(IOException e){
					System.out.println("IOException occured :" + e.getMessage());
				}
			}else{
				// ツイートIDが取得が取得できなかったのでエラーを返す
				result = ERROR;
			}
		}else{
			// ツイートの保存に失敗したのでエラーを返す
			result = ERROR;
		}
		
		// もし、ツイートと画像ファイルの保存に成功したら、フォルダに写真を反映させるために6秒間待つメソッドを実行
		if(result.equals(SUCCESS)){
			try {
			    Thread.sleep(6000);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException occured :" + e.getMessage());
			}
		}
		return result;
	}
	
	
	/**
	 * つぶやきを取得します。
	 * @return tweet つぶやき
	 */
	public String getTweet() {
		return tweet;
	}
	/**
	 * つぶやきを設定します。
	 * @param tweet つぶやき
	 */
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	/**
	 * ツイートの公開範囲を取得します。
	 * @return openRange ツイートの公開範囲
	 */
	public String getOpenRange() {
		return openRange;
	}
	/**
	 * ツイートの公開範囲を設定します。
	 * @param openRange 
	 */
	public void setOpenRange(String openRange) {
		this.openRange = openRange;
	}

	/**
	 * 画像ファイル群を取得します。
	 * @return userImage 画像ファイル群
	 */
	public File[] getUserImage() {
		return userImage;
	}
	/**
	 * 画像ファイル群を設定します。
	 * @param userImage 画像ファイル群
	 */
	public void setUserImage(File[] userImage) {
		this.userImage = userImage;
	}

	/**
	 * 画像ファイルの拡張子を取得します
	 * @return userImageContentType 画像ファイルの拡張子
	 */
	public String[] getUserImageContentType() {
		return userImageContentType;
	}
	/**
	 * 画像ファイルの拡張子を設定します。
	 * @param userImageContentType 画像ファイルの拡張子
	 */
	public void setUserImageContentType(String[] userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	/**
	 * 画像ファイルの名前を取得します。
	 * @return userImageFileName 画像ファイルの名前
	 */
	public String[] getUserImageFileName() {
		return userImageFileName;
	}
	/**
	 * 画像ファイルの名前を設定します。
	 * @param userImageFileName 画像ファイルの名前
	 */
	public void setUserImageFileName(String[] userImageFileName) {
		this.userImageFileName = userImageFileName;
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
