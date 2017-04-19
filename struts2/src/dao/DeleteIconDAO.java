package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.ResultSet;

import util.DBConnector;

/**
 * Icon写真を削除するクラス
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */

public class DeleteIconDAO {
	/**
	 * Icon写真を削除するメソッド
	 * @param userId 顧客ID
	 * @return insertFlg 更新できたかどうかの真偽値
	 * @throws SQLException
	 */
	public boolean deleteIconURL(int userId)throws SQLException{
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con = db.getConnection();
		PreparedStatement ps = null;
		String sql = null;
		sql = "update users set icon_url = ?, icon_flg = false where user_id = ?";
		String iconUrl = "img/user_icons/unset.png";
		int rs = 0;
		boolean updateFlg = false;
		try{
			ps = con.prepareStatement(sql);
			ps.setString(1, iconUrl);
			ps.setInt(2, userId);
			con.setAutoCommit(false);
			rs = ps.executeUpdate();
		}
		catch(SQLException e){
			con.rollback();
			e.printStackTrace();
		}
		/*
		finally{
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
		*/
		con.commit();
		if(rs != 0) updateFlg = true;
		return updateFlg;
	}
}