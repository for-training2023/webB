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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class S00004 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

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
		Connection con = null; // コネクション

		String url = request.getRequestURI();  //URL取得
		String azasu = url.substring(16);  //文字数分「url」から除く

		//SQL結果を格納する変数の宣言
		String id = null;
		String nickname = null;  
		String message = null;
		String joined_date = null; 
		String unique_code = null; 
		String gender = null; 
		String birthday = null; 
		String listener_count = null; 
		String fb_link = null; 
		String tw_link = null; 
		String other_link_url = null; 
		String other_link_description = null;
		Double rating_average = null;  //平均感動指数
		String total_listen_count = null;  //再生回数
		String songsum = null; 
		String title = null; //曲名
		String image_file_name = null; //画像サイズ群
		String image_file_height = null; 
		String image_file_width = null; 
		String rating_total = null;  //総感動指数
		Double release_datetime = null; //公開日
		String song_id = null;
		String listensum = null;
		Double averageAll = null;//作曲家情報の平均感動指数
		String ratingAll = null;

		//Listの宣言
		List<Map<String,String>> SongList = new ArrayList<>();

		try {

			try {
				con = DriverManager.getConnection(URL, connectUserName, connectPassword);
			} catch (SQLException e) {
				getServletConfig().getServletContext().  
				getRequestDispatcher("/ja/500.jsp").
				forward( request, response );
			}
			try {
				request.setCharacterEncoding("UTF-8");

				//SQL文の設定 作曲家情報
				String sql_Composer = "SELECT nickname,message,id,unique_code,DATE_FORMAT(composer.joined_date, '%Y/%m/%d') as joined_date,gender, DATE_FORMAT(composer.birthday, '%Y/%m/%d') as birthday, listener_count,fb_link, tw_link,other_link_url,other_link_description FROM composer where composer.unique_code = ? ;";

				//総曲数
				String sql_Sum ="select COUNT(song.title) as count FROM song left join composer on song.composer_id = composer.id  where composer.unique_code = ? group by song.composer_id;";

				//公開曲情報
				String sql_Song = "SELECT song.title,song.image_file_name,song.image_file_height,song.image_file_width,song.rating_total,song.rating_average,song.release_datetime,song.total_listen_count,song.id as song_id  FROM song left join composer on song.composer_id = composer.id  where composer.unique_code = ? order by song.release_datetime desc;";

				//再生回数
				String listenSum = "select SUM(song.total_listen_count) as total FROM song left join composer on song.composer_id = composer.id  where composer.unique_code = ? group by song.composer_id;";

				//総平均
				String sql_averageAll ="select AVG(rating_average) as averageAll from song left join composer on song.composer_id = composer.id where composer.unique_code = ? ;";

				//総感動指数
				String sql_ratingAll = "select SUM(song.rating_total) as ratingAll from song left join composer on song.composer_id = composer.id where composer.unique_code = ? ;";

				PreparedStatement pstmt = null;
				PreparedStatement pstmt2 = null;
				PreparedStatement pstmt3 = null;
				PreparedStatement pstmt4 = null;
				PreparedStatement pstmt5 = null;
				PreparedStatement pstmt6 = null;

				ResultSet rs = null; //作曲家
				ResultSet rs2 = null; //曲数
				ResultSet rs3 = null; //曲情報
				ResultSet rs4 = null;//総再生回数
				ResultSet rs5 = null;//作曲家の平均感動指数
				ResultSet rs6 = null;//総感動指数

				pstmt = con.prepareStatement(sql_Composer);//作曲家テーブル
				pstmt2 = con.prepareStatement(sql_Sum);//曲数
				pstmt3 = con.prepareStatement(sql_Song);//曲情報
				pstmt4 = con.prepareStatement(listenSum);//総再生回数
				pstmt5 = con.prepareStatement(sql_averageAll);//作曲者の平均感動指数
				pstmt6 = con.prepareStatement(sql_ratingAll);//総感動指数

				pstmt.setString(1,azasu);//作曲家テーブル
				pstmt2.setString(1,azasu);//曲数
				pstmt3.setString(1,azasu);//曲情報
				pstmt4.setString(1,azasu);//総再生回数
				pstmt5.setString(1,azasu);//作曲者の平均感動指数
				pstmt6.setString(1,azasu);//総感動指数

				rs = pstmt.executeQuery();//作曲家
				rs2 = pstmt2.executeQuery();//曲数
				rs3 = pstmt3.executeQuery();//曲情報
				rs4 = pstmt4.executeQuery();//総再生回数
				rs5 = pstmt5.executeQuery();//作曲者の平均感動指数
				rs6 = pstmt6.executeQuery();//総感動指数

				//SQL結果を変数に格納 ①作曲家
				while(rs.next()) {
					nickname = rs.getString("nickname");
					message = rs.getString("message");
					id = rs.getString("id");
					unique_code = rs.getString("unique_code");
					joined_date = rs.getString("joined_date");
					gender = rs.getString("gender");
					birthday = rs.getString("birthday");
					listener_count = rs.getString("listener_count");
					fb_link = rs.getString("fb_link");
					tw_link = rs.getString("tw_link");
					other_link_url = rs.getString("other_link_url");
					other_link_description = rs.getString("other_link_description");
				}

				//②曲数
				while(rs2.next()) {
					songsum = rs2.getString("count");
				}

				//③曲情報
				while(rs3.next()) {
					title = rs3.getString("title");
					image_file_name = rs3.getString("image_file_name");
					// 遊び用（のちに消す予定）
					// パッションフルーツをgifに変換
					if(image_file_name.equals("passionfruit.png")) {
						image_file_name = "passionfruit.gif";
					}
					image_file_height = rs3.getString("image_file_height");
					image_file_width = rs3.getString("image_file_width");
					rating_total= rs3.getString("rating_total");
					rating_average= rs3.getDouble("rating_average");
					release_datetime= rs3.getDouble("release_datetime");
					total_listen_count= rs3.getString("total_listen_count");
					song_id = rs3.getString("song_id");

					//Mapの宣言
					Map<String,String>map = new HashMap<String,String>();

					String s = getDatetime(release_datetime);
					String r = String.format("%.1f", rating_average);

					//Mapに追加
					map.put("title",title);
					map.put("image_file_name",image_file_name);
					map.put("image_file_height",image_file_height);
					map.put("image_file_width",image_file_width);
					map.put("rating_total",rating_total);
					map.put("r_average",r);
					map.put("total_listen_count",total_listen_count);
					map.put("release_datetime",s);
					map.put("song_id",song_id);

					SongList.add(map);

				}

				//④再生回数
				while(rs4.next()) {
					listensum = rs4.getString("total");
				}

				//⑤作曲家の平均感動指数
				while(rs5.next()) {
					averageAll = rs5.getDouble("averageAll");
				}

				//⑥総感動指数
				while(rs6.next()) {
					ratingAll = rs6.getString("ratingAll");
				}

			}catch (SQLException e) {
				e.printStackTrace();
			}	
			if (con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}

			//性別　1は男、2は女、nullはスルー
			if(gender==null) {
				;
			}else if (gender.equals("1")) {
				gender = "男";
			} else if (gender.equals("2")) {
				gender = "女";
			}

			//平均感動指数の表示形式
			String s_averageAll = String.format("%.1f", averageAll);

			//リクエストスコープへオブジェクト設定
			request.setAttribute("nickname",nickname);  //作曲家情報
			request.setAttribute("message",message); //メッセージ
			request.setAttribute("id",id); //作曲家テーブルid
			request.setAttribute("unique_code",unique_code); //作曲家ユニークコード
			request.setAttribute("joined_date",joined_date); //登録日
			request.setAttribute("gender",gender);//性別
			request.setAttribute("birthday",birthday);//誕生日
			request.setAttribute("listener_count",listener_count);//再生数
			request.setAttribute("fb_link",fb_link);//Facebook
			request.setAttribute("tw_link",tw_link);//Twitter
			request.setAttribute("other_link_url",other_link_url);//関連リンク
			request.setAttribute("other_link_description",other_link_description);//関連リンク文字列
			request.setAttribute("songsum",songsum);//曲数の合計値
			request.setAttribute("rating_total",rating_total); //総感動指数(公開曲一覧)
			request.setAttribute("rating_average",rating_average);//平均感動指数
			request.setAttribute("total_listen_count",total_listen_count);//再生回数

			request.setAttribute("title",title);//曲名
			request.setAttribute("image_file_name",image_file_name);
			request.setAttribute("image_file_height",image_file_height);
			request.setAttribute("image_file_width",image_file_width);
			request.setAttribute("song_id",song_id);
			request.setAttribute("listensum",listensum);//再生回数の合計値
			request.setAttribute("s_averageAll",s_averageAll);//曲の総合平均
			request.setAttribute("ratingAll",ratingAll);//総感動指数の合計値(作曲家情報)

			request.setAttribute("SongList",SongList);//List

			if(nickname == null){
				getServletConfig().getServletContext().  
				getRequestDispatcher("/ja/404.jsp").
				forward( request, response );
			}else {
				//作曲者紹介に遷移
				getServletConfig().getServletContext().  
				getRequestDispatcher("/ja/S00004.jsp").
				forward( request, response );
			}

		} catch (Exception e) {
			getServletConfig().getServletContext().  
			getRequestDispatcher("/ja/500.jsp").
			forward( request, response );
		}

	}

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
}