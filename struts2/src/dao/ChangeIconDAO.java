package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.ResultSet;

import util.DBConnector;

/**
 * Icon写真を保存もしくは上書きするクラス
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */

public class ChangeIconDAO {
	/**
	 * Icon写真を保存もしくは上書きするメソッド
	 * @param userId 顧客ID
	 * @return insertFlg 更新できたかどうかの真偽値
	 * @throws SQLException
	 */
	public boolean updateIconURL(int userId, String iconUrl, boolean iconFlg)throws SQLException{
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con = db.getConnection();
		PreparedStatement ps = null;
		String sql = null;
		if(iconFlg){
			sql = "update users set icon_url = ? where user_id = ?";
		}else{
			sql = "update users set icon_url = ?, icon_flg = true where user_id = ?";
		}
		int rs = 0;
		boolean insertFlg = false;
		try{
			ps = con.prepareStatement(sql);
			ps.setString(1, iconUrl);
			ps.setInt(2, userId);
			con.setAutoCommit(false);
			rs = ps.executeUpdate();
		}
		catch(SQLException e1){
			con.rollback();
			e1.printStackTrace();
		}
		/*
		finally{
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
		*/
		con.commit();
		if(rs != 0) insertFlg = true;
		return insertFlg;
	}
}
