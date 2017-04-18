package dto;

/**
 * TweetDTO ユーザーのつぶやき情報を格納するクラス
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */

public class TweetDTO {
	/**
	 * ユーザー名
	 */
	private String userName;
	/**
	 * つぶやき
	 */
	private String tweet;
	/**
	 * 投稿された写真のファイルパス
	 */
	private String photoUrl;
	/**
	 * ユーザーのアイコン用の写真のファイルパス
	 */
	private String iconUrl;
		

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
	 * 投稿された写真のファイルパスを取得します。
	 * @return photoUrl 投稿された写真のファイルパス
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}
	/**
	 * 投稿された写真のファイルパスを設定します。
	 * @param photoUrl 投稿された写真のファイルパス
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	/**
	 * ユーザーのアイコン用の写真のファイルパスを取得します。
	 * @return iconUrl 
	 */
	public String getIconUrl() {
		return iconUrl;
	}
	/**
	 * ユーザーのアイコン用の写真のファイルパスを設定します。
	 * @param iconUrl ユーザーのアイコン用の写真のファイルパス
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
}
