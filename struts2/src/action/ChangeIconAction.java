package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.imageio.ImageIO;

import dao.ChangeIconDAO;



/**
 * アイコンを変更するためのアクション
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */
public class ChangeIconAction extends ActionSupport implements SessionAware{
	/**
	 * 写真を保存するディレクトリーへの絶対パス
	 */
	private final String filePath = "/Users/Kazune/Documents/workspace/struts2/WebContent/img/user_icons/user";
	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = 5917378712510895429L;
	/**
	 * セッション
	 */
	private Map<String, Object> session;
	/**
	 * ユーザーがアップロードした画像ファイル
	 */
	private File userImage;  
	/**
	 * 画像ファイルの拡張子
	 */
    private String userImageContentType;
    /**
     * 画像ファイルの名前
     */
    private String userImageFileName;
	/**
	 * ユーザーのアイコン用の写真の真偽値
	 */
	private boolean iconFlg;
	
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
		// ログインユーザーの写真をJSP側で取得するためのパスを生成する
		String iconUrl = "img/user_icons/user" + String.valueOf(userId) + ".png";
		// アップロードされた画像データを保存するためのバッファを作成する
		BufferedImage bImage = null;
		
		// DAOを作成し、まずDBに保存されているログインユーザーの写真をJSP側で取得するためのパスを書き換える -> 成功ならTrue, 失敗ならFalse
		ChangeIconDAO dao = new ChangeIconDAO();
		boolean updateIconFlg = dao.updateIconURL(userId, iconUrl, iconFlg);
		
		// ユーザーが過去にアイコン画像を設定していた、かつ、新しい写真のパスをDBに保存することに成功していたなら
		if(iconFlg && updateIconFlg){
			// ユーザーが前回使っていた写真のファイルを取得し、削除する
			File file = new File(FileDir);
			if(file.exists()) file.delete();
		}
		
		// アップロードされた画像データをbImageに書き写し、ログインユーザーのアイコン用のファイルを作成し、その値をそのファイルに保存する
		try{
			bImage = ImageIO.read(userImage);
    		if(!ImageIO.write(bImage, "png", new File(FileDir))){
    			result = ERROR;
    		}
		}catch(IOException e){
			System.out.println("IOException occured :" + e.getMessage());
		}
		
		// もし、ツイートと画像ファイルの保存に成功したら、プロジェクトフォルダに写真を反映させるために6秒間待つメソッドを実行
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
	 * 画像ファイル群を取得します。
	 * @return userImage 画像ファイル群
	 */
	public File getUserImage() {
		return userImage;
	}
	/**
	 * 画像ファイル群を設定します。
	 * @param userImage 画像ファイル群
	 */
	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	/**
	 * 画像ファイルの拡張子を取得します
	 * @return userImageContentType 画像ファイルの拡張子
	 */
	public String getUserImageContentType() {
		return userImageContentType;
	}
	/**
	 * 画像ファイルの拡張子を設定します。
	 * @param userImageContentType 画像ファイルの拡張子
	 */
	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}
	/**
	 * 画像ファイルの名前を取得します。
	 * @return userImageFileName 画像ファイルの名前
	 */
	public String getUserImageFileName() {
		return userImageFileName;
	}
	/**
	 * 画像ファイルの名前を設定します。
	 * @param userImageFileName 画像ファイルの名前
	 */
	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
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
