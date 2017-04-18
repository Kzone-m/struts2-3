package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.GoMyPageDTO;
import util.DBConnector;

/**
 * ユーザーの登録情報を取得するクラス
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */
public class GoMyPageDAO {
	/**
	 * symDBのusers tableからログインユーザーの登録情報を取得するメソッド
	 * @param userId 顧客ID
	 * @return dto ログインユーザーの登録情報
	 */
	public GoMyPageDTO selectUserInfo(int userId) throws SQLException{
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con = db.getConnection();
		GoMyPageDTO dto = new GoMyPageDTO();
		PreparedStatement ps = null;
		String sql = "select user_name, icon_url, icon_flg from users where user_id = ?";
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				dto.setUserName(rs.getString("user_name"));
				dto.setIconUrl(rs.getString("icon_url"));
				dto.setIconFlg(rs.getBoolean("icon_flg"));
			}						
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally{
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
		return dto;
	}
}
