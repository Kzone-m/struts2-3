package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBConnector;

/**
* ログインアウトをするクラス
* @author KAZUNE MIYAGI
* @since 2017/04/18
* @version 1.1
*/
public class LogoutDAO {

	/**
	 * symDBのusers tableのlogin_flgの値をfalseにするメソッド
	 * @param userId
	 * @return updateFlg symDBのusers tableのlogin_flgの値をfalseしようと試みたときの真偽値
	 */
	public boolean updateLoginFlg(int userId){
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con =db.getConnection();
		PreparedStatement ps = null;
		String sql = "update users set login_flg = false where user_id = ?";
		boolean updateFlg = false;
		try{
			ps= con.prepareStatement(sql);
			ps.setInt(1, userId);
			int rs = ps.executeUpdate();
			if(rs != 0) updateFlg = true;
		}catch(SQLException e){
			e.printStackTrace();
       }try{
       	if(ps != null) ps.close();
       	if(con != null)	con.close();
       }catch(SQLException e){
       	e.printStackTrace();
       }
       return updateFlg;
	}
}