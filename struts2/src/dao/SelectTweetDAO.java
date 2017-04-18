package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import dto.TweetDTO;
import util.DBConnector;

/**
 * つぶやきを取得するクラス
 * @author KAZUNE MIYAGI
 * @since 2017/04/17
 * @version 1.1
 */
public class SelectTweetDAO {
	
	/**
	 * symDBのusers table, tweet table, photo tableからつぶやきを取得するメソッド
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @return List<TweetDTO> つぶやき情報
	 */
	public List<TweetDTO> selectTopFiveTweets() throws SQLException{
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con = db.getConnection();
		TweetDTO dto = null;
		List<TweetDTO> dtoList = new ArrayList<TweetDTO>();
		PreparedStatement ps = null;
		String sql = "select user_name, tweet, photo_url, icon_url from "
				+ "(users inner join tweet using(user_id)) "
				+ "inner join photo using(tweet_id) "
				+ "order by tweet_date desc limit 5";		
		try{
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				dto = new TweetDTO();
				dto.setUserName(rs.getString("user_name"));
				dto.setTweet(rs.getString("tweet"));
				dto.setPhotoUrl(rs.getString("photo_url"));
				dto.setIconUrl(rs.getString("icon_url"));
				dtoList.add(dto);
			}						
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally{
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
		return dtoList;
	}
	
	/**
	 * symDBのusers table, tweet table, photo tableからログインユーザーのつぶやきを取得するメソッド
	 * @param userId 顧客ID
	 * @return List<TweetDTO> ログインユーザーのつぶやき情報
	 */
	public List<TweetDTO> selectUserTweets(int userId) throws SQLException{
		DBConnector db = new DBConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/","sym","root","KKznzn92");
		Connection con = db.getConnection();
		TweetDTO dto = null;
		List<TweetDTO> dtoList = new ArrayList<TweetDTO>();
		PreparedStatement ps = null;
		String sql = "select user_name, tweet, photo_url, icon_url from "
				+ "(users inner join tweet using(user_id)) "
				+ "inner join photo using(tweet_id) "
				+ "where user_id = ? order by tweet_date desc";		
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				dto = new TweetDTO();
				dto.setUserName(rs.getString("user_name"));
				dto.setTweet(rs.getString("tweet"));
				dto.setPhotoUrl(rs.getString("photo_url"));
				dto.setIconUrl(rs.getString("icon_url"));
				dtoList.add(dto);
			}						
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally{
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
		return dtoList;
	}
}
