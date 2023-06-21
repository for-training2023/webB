package jp.excd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

@WebServlet("/S00003")
public class S00003 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        Connection con = null; // コネクション
 
        try {
			String hostName = "192.168.1.68";//ホスト名
	        String connectUserName = "meloko";// ユーザ
	        String connectPassword = "exceed";// パスワード
	        String dbName = "meloko";// DB名
	        String timeZone = "Asia/Tokyo";// timeZone
	        final String URL = "jdbc:mysql://"// コネクション用のSQL
	                + hostName
	                + ":3306/"
	                + dbName
	                + "?serverTimezone="
	                + timeZone
	                + "&allowPublicKeyRetrieval=true"
	                + "&useSSL=false";
	        
	        con = DriverManager.getConnection(URL, connectUserName, connectPassword);

//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

	        String url = request.getRequestURI();  //URL取得
	        int index = url.indexOf("/webB/ja/S00003/");  //""内の文字数取得
	        String strSongId = url.substring(index+16);  //文字数分「url」から除き、それを変数に代入
	        
	        for( int i = 0 ; i < strSongId.length();i++) {  //「"/web/ja/S00003/」以降が文字列の場合は404に遷移
	        	if(Character.isDigit(strSongId.charAt(i))) {
	        	    continue;//数字の場合は次の文字の判定へ
	        	}else {
	    			getServletConfig().getServletContext().
	    			getRequestDispatcher("/ja/404.jsp").
	    			forward( request, response );
	        	}
	        }
	        
	        int intSongId = Integer.parseInt(strSongId);  //曲IDをint型に変換
	        
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
	        
	        String songId = null;
	        String title = null;  //タイトル
	        String songComposerId = null;  //曲の作曲者id
	        int ratingTotal = 0;  //総感動指数
	        double ratingAverage = 0;  //平均感動指数
	        int totalListeningCount = 0;  //再生回数
	        double releaseDatetime = 0;  //公開日
	        double lastUpdateDatetime = 0;  //最終更新日
	        String message = null;  //メッセージ
	        String key = null;  //キー
	        String scoreType = null;  //楽譜表記
	        String bpm = null;  //BPM
	        String imageFileName = null;  //画像名
	        int imageFileHeight = 0;  //画像の高さ
	        int imageFileWidth = 0;  //画像の幅
	        String otherLinkUrl = null;  //関連リンクURL
	        String otherLinkDescription = null;  //関連リンク文字列
	        
	        String commentId = null;  //コメントID
	        String commentSongId = null;  //曲ID
	        String sequense = null;  //シーケンス
	        String comment = null;  //コメント
	        String commentComposerId = null;  //コメントの作曲者ID
	        String type = null;  //コメントタイプ
	        String toCommentId = null;  //元コメントID
	        double writeDatetime = 0;  //書き込み時期
	        String nickName = null;  //ニックネーム
	        String uniqueCode = null;
	        String commentNickName = null;
	        String commentUniqueCode = null;
	        double rating = 0;
        
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////        

	    	request.setCharacterEncoding("UTF-8");
	    	//コメント以外の情報取得のSQL文
	    	String sql ="select song.id,"
	    			+ "song.title,"
	    			+ "song.composer_id,"
	    			+ "song.rating_total,"
	    			+ "song.rating_average,"
	    			+ "song.total_listen_count,"
	    			+ "song.release_datetime,"
	    			+ "song.last_update_datetime,"
	    			+ "song.message,"
	    			+ "song.key,"
	    			+ "song.score_type,"
	    			+ "song.bpm,"
	    			+ "song.image_file_name,"
	    			+ "song.image_file_height,"
	    			+ "song.image_file_width,"
	    			+ "song.other_link_url,"
	    			+ "song.other_link_description,"
	    			+ "composer.unique_code,"
	    			+ "composer.nickname "
	    			+ "from song "
	    			+ "left outer join "
	    			+ "composer on song.composer_id = composer.id "
	    			+ "where song.id = ?;";  
	    	//コメント情報取得のSQL文
	    	String sql2 ="select * from comment "
	    			+ "left outer join "
	    			+ "composer "
	    			+ "on comment.composer_id = composer.id "
	    			+ "left outer join "
	    			+ "rating "
	    			+ "on rating.composer_id = comment.composer_id "
	    			+ "where comment.song_id = ? "
	    			+ "order by sequence asc;";
        	
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

	    	PreparedStatement pstmt = null;
	    	PreparedStatement pstmt2 = null;
	    	ResultSet rs = null;
	    	ResultSet rs2 = null;
	    	pstmt = con.prepareStatement(sql);
	    	pstmt2 = con.prepareStatement(sql2);
	    	pstmt.setInt(1,intSongId);
	    	pstmt2.setInt(1,intSongId);
	    	rs = pstmt.executeQuery();
	    	rs2 = pstmt2.executeQuery();
        	
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////       	

        	while(rs.next()) {
            	songId = rs.getString("song.id");
                title = rs.getString("song.title");
                songComposerId = rs.getString("song.composer_id");
                ratingTotal = rs.getInt("song.rating_total");
                ratingAverage = rs.getDouble("song.rating_average");
                totalListeningCount = rs.getInt("song.total_listen_count");
                releaseDatetime = rs.getDouble("song.release_datetime");
                lastUpdateDatetime = rs.getDouble("song.last_update_datetime");
                message = rs.getString("song.message");
                key = rs.getString("song.key");
                scoreType = rs.getString("song.score_type");
                bpm = rs.getString("song.bpm");
                imageFileName = rs.getString("song.image_file_name");
                imageFileHeight = rs.getInt("song.image_file_height");
                imageFileWidth = rs.getInt("song.image_file_width");
                otherLinkUrl = rs.getString("song.other_link_url");
                otherLinkDescription = rs.getString("song.other_link_description");
                uniqueCode = rs.getString("composer.unique_code");
                nickName = rs.getString("composer.nickname");
            }

//////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////// 

            List<Map<String,String>> commentList = new ArrayList<>();  //一人ひとりのコメントを格納するリストを定義

            while(rs2.next()) {
            	commentId = rs2.getString("id");  //コメントテーブルのコメントのidを取得
                commentSongId = rs2.getString("song_id");  //コメントテーブルのコメントのsongid取得
                sequense = rs2.getString("sequence");  //コメントテーブルのコメントのsongid取得
                comment = rs2.getString("comment");  //コメントテーブルのコメントのsongid取得
                commentComposerId = rs2.getString("composer_id");  //コメントテーブルのコメントを書いた人取得
                type = rs2.getString("type");  //コメントが返信かどうかを取得
                toCommentId = rs2.getString("to_comment_id");  //誰にリプライしているか取得
                writeDatetime = rs2.getDouble("write_datetime");  //書いた時間を取得
                commentUniqueCode = rs2.getString("composer.unique_code");  //コメント投稿者のユニークコード取得
                commentNickName = rs2.getString("composer.nickname");  //コメント投稿者のニックネーム取得
                rating = rs2.getDouble("rating.rating");  //レーティングを取得
                
                Map<String,String> map = new HashMap<String,String>();  //コメント内の各パラメータを格納するマップを定義
                
                String s =getDatetime(writeDatetime);  //投稿時間（エポック秒）を「～日前」の形に変更し、変数「s」に格納
                String rate = getTenTimes(rating);  //レーティングを10倍して変数「rate」に格納
                
                map.put("commentId",commentId);
                map.put("commentSongId",commentSongId);
                map.put("sequense",sequense);
                map.put("comment",comment);
                map.put("commentId",commentId);
                map.put("commentComposerId",commentComposerId);
                map.put("type",type);
                map.put("toCommentId",toCommentId);
                map.put("writeDatetime",s);
                map.put("commentUniqueCode",commentUniqueCode);
                map.put("commentNickName",commentNickName);
                map.put("rating",rate);
                
                
                commentList.add(map);  //コメントリストにマップを格納
            }
            
            request.setAttribute("commentList",commentList);          

//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////	

	        NumberFormat changeFormat = NumberFormat.getNumberInstance(); //カンマ区切り
	        if(scoreType.equals("1")) {
	        	scoreType="1オクターブ上(男性ボーカル)";
	        }
			if(imageFileName == null) {
				imageFileName = "noimage.png";
			}
			
			ratingAverage = ratingAverage * 10;  //平均感動指数の小数点第２位を四捨五入
			double afterratingAverage = Math.round(ratingAverage);			
			ratingAverage = afterratingAverage/10;
			
			
			
		    request.setAttribute("songId",songId);
			request.setAttribute("title",title);  //リクエストスコープへオブジェクト設定
			request.setAttribute("songComposerId",songComposerId);
			request.setAttribute("ratingTotal",changeFormat.format(ratingTotal));
			request.setAttribute("ratingAverage",ratingAverage);
			request.setAttribute("totalListeningCount",changeFormat.format(totalListeningCount));
			request.setAttribute("releaseDatetime",getDatetime(releaseDatetime));
			request.setAttribute("lastUpdateDatetime",getDatetime(lastUpdateDatetime));
			request.setAttribute("message",message);
			request.setAttribute("key",changeKey(key));
			request.setAttribute("scoreType",scoreType);
			request.setAttribute("bpm",bpm);
			request.setAttribute("imageFileName",imageFileName);
			request.setAttribute("imageFileHeight",imageFileHeight);
			request.setAttribute("imageFileWidth",imageFileWidth);
			request.setAttribute("otherLinkUrl",otherLinkUrl);
			request.setAttribute("otherLinkDescription",otherLinkDescription);
			request.setAttribute("uniqueCode",uniqueCode);
			request.setAttribute("nickName",nickName);
		
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
		
		if(title==null) {  //曲名が何も受け取れなかった場合、404に遷移
			getServletConfig().getServletContext().
			getRequestDispatcher("/ja/404.jsp").
			forward( request, response );
		}else {
			getServletConfig().getServletContext().  //作品紹介に遷移
			getRequestDispatcher("/ja/S00003.jsp").
			forward( request, response );
		}
		
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////	
		
		} catch (Exception e) {
			getServletConfig().getServletContext().
			getRequestDispatcher("/ja/500.jsp").
			forward( request, response );
		} finally {
		    if (con != null) {
		    	try {
		    		con.close();
		            }catch(SQLException e) {
		                e.printStackTrace();
		            }
		    }
		}
	}

	
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
	
	private String getDatetime(double datetime) {
		//「～前」
		String resultVal;
		double d_releaseDay = 0;
		//現在のエポック秒を取得
		Date date = new Date();
		Double nowEpoch = (double) date.getTime();
		//差分を算出
		Double diff = nowEpoch - datetime * 1000;
		//小数点以下を切り捨てる処理
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(0);
		//公開時間を取得
		//1秒未満
		if (diff < 1000) {
			resultVal = "たった今";
		}
		//1秒以上かつ2秒未満
		else if (diff < 2000) {
			resultVal = "1秒前";
		}
		//2秒以上かつ60秒未満
		else if (diff < 60000) {
			resultVal = diff + "秒前";
		}
		//1分以上かつ2分未満
		else if (diff < 120000) {
			resultVal = "1分前";
		}
		//2分以上かつ60分未満
		else if (diff < 3600000) {
			d_releaseDay = (diff / 60000);
			resultVal = numberFormat.format(d_releaseDay) + "分前";
		}
		//1時間以上かつ2時間未満
		else if (diff < 7200000) {
			resultVal = "1時間前";
		}
		//2時間以上かつ24時間未満
		else if (diff < 86400000) {
			d_releaseDay = (diff / 3600000);
			resultVal = numberFormat.format(d_releaseDay) + "時間前";
		}
		//1日以上かつ2日未満
		else if (diff < 172800000) {
			resultVal = "1日前";
		}
		//2日以上かつ7日未満
		else if (diff < 604800000) {
			d_releaseDay = (diff / 86400000);
			resultVal = numberFormat.format(d_releaseDay) + "日前";
		}
		//7日以上かつ14日未満
		else if (diff < 1209600000) {
			resultVal = "1週間前";
		}
		//14日以上かつ30日未満
		else if (diff < 2592000000L) {
			d_releaseDay = (diff / 604800000);
			resultVal = numberFormat.format(d_releaseDay) + "週間前";
		}
		//30日以上かつ60日未満
		else if (diff < 5184000000L) {
			resultVal = "1ヶ月前";
		}
		//60日以上かつ365日未満
		else if (diff < 31536000000L) {
			d_releaseDay = (diff / 2592000000L);
			resultVal = numberFormat.format(d_releaseDay) + "ヶ月前";
		}
		//1年以上かつ2年未満
		else if (diff < 63072000000L) {
			resultVal = "1年前";
		}
		//2年以上
		else {
			d_releaseDay = (diff / 31536000000L);
			resultVal = numberFormat.format(d_releaseDay) + "年前";
		}
		return resultVal;
	}
	
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

	private String getTenTimes(double num) {
		double ten = num * 10;
		String result = String.valueOf(ten);
		result = result.substring(0, 2);
		return result;
	}
	
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
	
	private String changeKey(String key) {
		Map<String, String> map = new HashMap<>();
		map.put("01", "Cメジャー");
		map.put("02", "Cシャープメジャー");
		map.put("03", "Dフラットメジャー");
		map.put("04", "Dメジャー");
		map.put("05", "Dシャープメジャー");
		map.put("06", "Eフラットメジャー");
		map.put("07", "Eメジャー");
		map.put("08", "Fメジャー");
		map.put("09", "Fシャープメジャー");
		map.put("10", "Gフラットメジャー");
		map.put("11", "Gメジャー");
		map.put("12", "Gシャープメジャー");
		map.put("13", "Aフラットメジャー");
		map.put("14", "Aメジャー");
		map.put("15", "Aシャープメジャー");
		map.put("16", "Bフラットメジャー");
		map.put("17", "Bメジャー");
		map.put("18", "Cマイナー");
		map.put("19", "Cシャープマイナー");
		map.put("20", "Dフラットマイナー");
		map.put("21", "Dマイナー");
		map.put("22", "Dシャープマイナー");
		map.put("23", "Eフラットマイナー");
		map.put("24", "Eマイナー");
		map.put("25", "Fマイナー");
		map.put("26", "Fシャープマイナー");
		map.put("27", "Gフラットマイナー");
		map.put("28", "Gマイナー");
		map.put("29", "Gシャープマイナー");
		map.put("30", "Aフラットマイナー");
		map.put("31", "Aマイナー");
		map.put("32", "Aシャープマイナー");
		map.put("33", "Bフラットマイナー");
		map.put("34", "Bマイナー");
		return map.get(key);
	}
}

//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////


