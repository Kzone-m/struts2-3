package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import util.DBConnector;

/**
 * ツイートされた文字列及び写真を格納するためのクラス
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */

public class InsertTweetDAO {
	/**
	 * ツイートされた文字列を格納するメソッド
	 * @param userId 顧客ID
	 * @return insertFlg 更新できたかどうかの真偽値
	 * @throws SQLException
	 */
	public boolean insertTweet(int userId, String tweet, String openRange)throws SQLException{
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con = db.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into tweet (user_id, tweet, open_range) values(?, ?, ?)";
		int rs = 0;
		boolean insertFlg = false;
		
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, tweet);
			ps.setString(3, openRange);
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
	
	/**
	 * ツイートIDの取得するメソッド
	 * @param userId 顧客ID
	 * @return tweetId ツイートID
	 * @throws SQLException
	 */
	public int selectTweetId(int userId) throws SQLException{
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con = db.getConnection();
		PreparedStatement ps = null;
		String sql = "select tweet_id from tweet where user_id = ? "
				   + "order by tweet_date desc limit 1";
		int tweetId = 0;
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) tweetId = rs.getInt("tweet_id");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		/*
		finally{
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
		*/
		System.out.println(tweetId);
		return tweetId;
	}

	/**
	 * ツイートされた文字列を格納するメソッド
	 * @param userId 顧客ID
	 * @return insertFlg 更新できたかどうかの真偽値
	 * @throws SQLException
	 */
	public boolean insertImage(int tweetId, String fileName)throws SQLException{
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con = db.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into photo(tweet_id, photo_url) values(?, ?)";
		int rs = 0;
		boolean insertFlg = false;
		
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, tweetId);
			ps.setString(2, fileName);
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
