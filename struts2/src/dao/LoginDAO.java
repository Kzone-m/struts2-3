package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.LoginDTO;
import util.DBConnector;

/**
 * ログイン情報を取得するクラス
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */
public class LoginDAO {

	/**
	 * symDBのusers tableから情報を取得するメソッド
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @return LoginDTO ログイン情報
	 */
	public LoginDTO select(String userName, String password){
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con =db.getConnection();
		PreparedStatement ps = null;
		LoginDTO dto = new LoginDTO();

		String sql = "select user_id, user_name, password, login_flg from users where user_name = ? and password = ?";
		try{
			ps= con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				dto.setUserId(rs.getInt("user_id"));
				dto.setUserName(rs.getString("user_name"));
				dto.setPassword(rs.getString("password"));
				dto.setLoginFlg(rs.getString("login_flg"));
			}
		}catch(SQLException e){
			e.printStackTrace();
        }try{
        	if(ps != null) ps.close();
        	if(con != null) con.close();
        }catch(SQLException e){
        	e.printStackTrace();
        }
        return dto;
	}


	/**
	 * symDBのusers tableのログインフラグをtrueにするメソッド
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @return updateFlg symDBのupdate後のloginFlgの真偽値
	 * @throws SQLException 
	 */
	public boolean updateLoginFlg(String userName, String password) throws SQLException{
		
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con =db.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		boolean updateFlg = true;
		String sql = "update users set login_flg = true where user_name = ? and password = ?";
		
		try{
			ps= con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2,password);
			rs =ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
        }finally{
        	if(ps != null) ps.close();
        	if(con != null) con.close();
        }
		
        if(rs != 0) updateFlg = true;
        return updateFlg;
	}

	
	/**
	 * symDBのusers tableのログインフラグをfalseにするメソッド
	 * @param userId 顧客ID
	 * @return updateFlg symDBのupdate後のloginFlgの真偽値
	 * @throws SQLException 
	 */
	public boolean updateClearLoginFlg(int userId) throws SQLException{
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con = db.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		boolean updateFlg = true;
		String sql = "update users set login_flg = false where user_id = ?";
		try{
			ps= con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs =ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
        }finally{
        	if(ps != null) ps.close();
        	if(con != null) con.close();
        }
        if(rs != 0) updateFlg = true;
        return updateFlg;
	}
	

	
	
	
	
	
	
	
// ------ ------ ------------	------	------	------	------	------	------	------			
	/**
	 * symDBに情報をinsertするメソッド
	 * @param userId 顧客ID
	 * @param phoneEmail アドレス
	 * @param loginFlg ログインフラグ
	 * @param userFlg ユーザーフラグ
	 * @param password パスワード
	 * @return insertFlg 値の挿入に成功したかどうかの真偽値
	 */
	public boolean insertIntoUsers(int userId, String phoneEmail, String loginFlg, String userFlg, String password){
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","gochikag","root","KKznzn92");
		Connection con =db.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		boolean insertFlg = false;
		String sql = "insert into  users (user_id, phone_email, login_flg, user_flg, password) values (?, ?, ?, ?, ?)";
		try{
			ps= con.prepareStatement(sql);
			ps.setInt(1,userId);
			ps.setString(2,phoneEmail);
			ps.setString(3,loginFlg);
			ps.setString(4,userFlg);
			ps.setString(5, password);
			rs =ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
        }try{
        	if(ps != null) ps.close();
        	if(con != null) con.close();
        }catch(SQLException e){
        	e.printStackTrace();
        }
        if(rs != 0) insertFlg = true;
        return insertFlg;
	}

	/**
	 * @param itemId
	 * @param userId
	 * @return deleteFlg symDBのcartテーブルのデータの削除をした時の結果の真偽値
	 * @throws SQLException エラー処理
	 */
	public void delete(int userId) throws SQLException{
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","gochikag","root","KKznzn92");
		Connection con =db.getConnection();
		PreparedStatement ps = null;
		String sql = "delete from cart2 where user_id = ?";
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1,userId);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		}
	}
}
