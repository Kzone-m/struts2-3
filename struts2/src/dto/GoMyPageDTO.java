package dto;

/**
 * GoMyPageDTO ユーザーの登録情報を取得するクラス
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */

public class GoMyPageDTO {
	/**
	 * ユーザー名
	 */
	private String userName;
	/**
	 * ユーザーのアイコン用の写真のファイルパス
	 */
	private String iconUrl;
	/**
	 * ユーザーのアイコン用の写真の真偽値
	 */
	private boolean iconFlg;
	
	
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
	
	/**
	 * ユーザーのアイコン用の写真の真偽値を取得します。
	 * @return iconFlg ユーザーのアイコン用の写真の真偽値
	 */
	public boolean isIconFlg() {
		return iconFlg;
	}
	/**
	 * ユーザーのアイコン用の写真の真偽値を設定します。
	 * @param iconUrl ユーザーのアイコン用の写真の真偽値
	 */
	public void setIconFlg(boolean iconFlg) {
		this.iconFlg = iconFlg;
	}	
}
