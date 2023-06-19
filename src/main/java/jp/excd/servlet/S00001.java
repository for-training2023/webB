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

/**
 * 本機能は、トップページに表示するデータをSQLで操作し、取得した値をListに格納する。
 *  格納した値をデータとして返却する。
 *  
 * @author k-asakura
 *
 */
public class S00001 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// UTF-8でエンコードする。
		request.setCharacterEncoding("UTF-8");
		
		// ホスト名
		String hostName = "192.168.1.68";
		
		// ユーザ
		String connectUserName = "meloko";
		
		// パスワード
		String connectPassword = "exceed";
		
		// DB名
		String dbName = "meloko";
		// timeZone
		String timeZone = "Asia/Tokyo";
		
		// コネクション用のSQL
		final String URL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=" + timeZone
				+ "&allowPublicKeyRetrieval=true" + "&useSSL=false";
		
		// コネクション
		Connection con = null;

		try {
			// コネクション
			con = DriverManager.getConnection(URL, connectUserName, connectPassword);
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// メインメソッドを呼び出す。
		this.prmainProcessForTopocess(request, response, con);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// UTF-8でエンコードする。
		request.setCharacterEncoding("UTF-8");
		
		// ホスト名
		String hostName = "192.168.1.68";
		
		// ユーザ
		String connectUserName = "meloko";
		
		// パスワード
		String connectPassword = "exceed";
		
		// DB名
		String dbName = "meloko";
		
		// timeZone
		String timeZone = "Asia/Tokyo";
		
		// コネクション用のSQL
		final String URL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=" + timeZone
				+ "&allowPublicKeyRetrieval=true" + "&useSSL=false";
		
		// コネクション
		Connection con = null;
		
		// DB接続テスト結果
		String result = "Failed!";
		try {
			// コネクション
			con = DriverManager.getConnection(URL, connectUserName, connectPassword);
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// メインメソッドを呼び出す。
		this.prmainProcessForTopocess(request, response, con);
	}

	/**
	 * メイン機能です。本機能が呼び出されたらまずこのメソッドが呼び出され、
	 * 	各種機能の制御メソッドを呼び出す。
	 * 
	 * @param request	リクエスト情報を取得する。
	 * @param response	表示画面へ値を出力する。
	 * @throws IOException
	 * @throws ServletException
	 */
	private void prmainProcessForTopocess(HttpServletRequest request, HttpServletResponse response, Connection con)
			throws IOException, ServletException {
		
		// URLパラメータ「category」を取得する
		String category = request.getParameter("category");
		
		// URLパラメータ「from」を取得する
		String from = request.getParameter("from");
		
		// ソート機能メソッドを呼び出す
		category =  getSort(request, category, con);
		request.setAttribute("Category", category);
		
		// 追加読み込み機能メソッドを呼び出す
		from = getAdd(request, response, from);

		// トップページに遷移する
		getServletConfig().getServletContext().getRequestDispatcher("/ja/S00001.jsp").forward(request, response);

	}

	/**
	 * ソート機能です。メインメソッドから呼び出される。
	 * 	取得したcategoryの値によってソートの内容が変化する。
	 * 		category:1 = 新着順（登録されてから一ヶ月未満のデータを、公開日の降順で表示します。）
	 * 		category:2 = 人気順（登録されてから一ヶ月未満のデータを、総感動指数の降順で表示します。）
	 * 		category:3 = 高評価順（登録されてから一ヶ月未満のデータを、平均感動指数の降順で表示します。）
	 * 		category:4 = 名作順（登録されて一年未満のデータを、総感動指数の降順で表示します。）
	 * 
	 * @param request	request.setAttributeで値を送信する。
	 * @param category	選択したソート機能に設定されている値。
	 * @param con
	 * 
	 * @return	category	呼び出されたとき値がnullであった場合に「1」を返却する。
	 */
	private String getSort(HttpServletRequest request, String category, Connection con) {
		// SQL文
		String sql = "SELECT s.id, s.title, s.composer_id, s.rating_total, s.rating_average, s.total_listen_count, s.release_datetime, s.image_file_name,  s.image_file_height, s.image_file_width, c.nickname, c.unique_code "
				+ "FROM song AS s LEFT JOIN composer AS c ON  s.composer_id = c.id " + "WHERE s.release_datetime > ? ";
		
		// プリペアードステートメントに代入する変数
		String sortTimeAgo = "";

		/* 変数categoryの値をチェックします
		 * 	nullである場合には初期値の「1」を設定します
		 */
		if(category == null) {
			category ="1";
		}		
		
		// SQL文の組み立て
		/* categoryが「1」の時
		 * 	SQL文に公開日の降順で並びかえる
		 *  公開日の日付が30日前から現在までのデータのみ取得する
		 */
		if (category.equals("1")) {
			sql += "ORDER BY s.release_datetime DESC;";
			long Epoch = 2592000;
			sortTimeAgo = Long.toString(getAgo(Epoch));
		
		/* categoryが「2」の時
		 * 	SQL文に総感動指数の降順で並びかえる
		 *  公開日の日付が30日前から現在までのデータのみ取得する
		 */
		}else if (category.equals("2")) {
			sql += "ORDER BY s.rating_total DESC;";
			long Epoch = 2592000;
			sortTimeAgo = Long.toString(getAgo(Epoch));
		
		/* categoryが「3」の時
		 * 	SQL文に平均感動指数の降順で並びかえる
		 *  公開日の日付が30日前から現在までのデータのみ取得する
		 */
		}else if (category.equals("3")) {
			sql += "ORDER BY s.rating_average DESC;";
			long Epoch = 2592000;
			sortTimeAgo = Long.toString(getAgo(Epoch));
		
		/* categoryが「4」の時
		 * 	SQL文に総感動指数の降順で並びかえる
		 *  公開日の日付が365日前から現在までのデータのみ取得する
		 */
		}else if (category.equals("4")) {
			sql += "ORDER BY s.rating_total DESC;";
			long Epoch = 31536000;
			sortTimeAgo = Long.toString(getAgo(Epoch));
	
			
			
		// 遊び用（のちに消す予定）
		/* categoryが「5」の時
		 * 	SQL文に総感動指数の降順で並びかえる
		 * 	全件表示する
		 */	
		}else if (category.equals("5")) {
			sql += "ORDER BY s.rating_total DESC;";
			Date date = new Date();
			Long nowEpoch = new Long(date.getTime()/1000);
			long Epoch = nowEpoch;
			sortTimeAgo = Long.toString(getAgo(Epoch));
		
			
		// その他の場合は、categoryが「1」の時と同様の処理を行う
		}else {
			sql += "ORDER BY s.release_datetime DESC;";
			long Epoch = 2592000;
			sortTimeAgo = Long.toString(getAgo(Epoch));
		}
		System.out.println(sql);
		
		// プリペアードステートメント
		PreparedStatement pstmt = null;
		
		// ResultSet
		ResultSet rs = null;
		
		// SQL文を実行した結果を格納するMapのListを作成する
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();

		try {	
			// 告知に表示する値を取得する
			String noticeSql = "SELECT notice FROM top_notice;";
			pstmt = con.prepareStatement(noticeSql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String notice = rs.getString("notice");
				request.setAttribute("Notice", notice);
			}
			pstmt.close();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sortTimeAgo);

			// 実行
			rs = pstmt.executeQuery();

			// Listのレコード値を格納するListを設定する
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				// SQLの結果を格納する
				String id = rs.getString("s.id");
				String title = rs.getString("s.title");
				String composerId = rs.getString("s.composer_id");
				String ratingTotal = rs.getString("s.rating_total");
				String ratingAverage = rs.getString("s.rating_average");
				String totalListenCount = rs.getString("s.total_listen_count");
				double releaseDatetime = rs.getDouble("s.release_datetime");
				String imageFileName = rs.getString("s.image_file_name");
				String imageFileHeight = rs.getString("s.image_file_height");
				String imageFileWidth = rs.getString("s.image_file_width");
				String nickName = rs.getString("c.nickname");
				String uniqueCode = rs.getString("c.unique_code");
				
				System.out.println(imageFileName);
				// imageFileNameがnullの場合にnoimage.pngを設定する
				if(imageFileName == null) {
					imageFileName = "noimage.png";
				}
				// 遊び用（のちに消す予定）
				// パッションフルーツをgifに変換
				if(imageFileName.equals("passionfruit.png")) {
					imageFileName = "passionfruit.gif";
				}
				
				// ｎ日前を取得する
				String ago = getLastUploadTime(releaseDatetime);

				// 格納したものをMapで格納する。
				Map<String, String> map = new HashMap<String, String>();
				map.put("Id", id);
				map.put("Title", title);
				map.put("ComposerId", composerId);
				map.put("RatingTotal", ratingTotal);
				map.put("RatingAverage", ratingAverage);
				map.put("TotalListenCount", totalListenCount);
				map.put("ReleaseDatetime", ago);
				map.put("ImageFileName", imageFileName);
				map.put("ImageFileHeight", imageFileHeight);
				map.put("ImageFileWidth", imageFileWidth);
				map.put("NickName", nickName);
				map.put("UniqueCode", uniqueCode);
				
				// Mapに格納したものをListで格納する
				listMap.add(map);
				request.setAttribute("ListMap", listMap);
				list.addAll(map.values());
				request.setAttribute("List", list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			// 各種クローズ
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Listの件数を取得する
		String listSize = Integer.toString(listMap.size());
		request.setAttribute("ListSize", listSize);
System.out.println("ListSize"+ listSize);
		return category;
	}
	
	/**
	 * 追加でデータを読み込む機能です。
	 * 
	 * @param request	リクエスト情報を取得する。
	 * @param response	表示画面へ値を出力する。
	 * @param from		表示されるデータのレコード値+1。
	 * 
	 * @return from		
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws ServletException 
	 */
	private String getAdd(HttpServletRequest request, HttpServletResponse response, String from) throws NumberFormatException, IOException, ServletException {
		
		/* 変数fromの値をチェックします
		 * 	nullである場合には初期値の「6」を設定します
		 * 	nullではない場合にはfromの値を変数outPutMaxに代入します
		 * 	数字以外の値であった場合には500エラーで500.jspに遷移します
		 */
		int outPutMax = 5;
		int outPutMin = 0;
		if ("".equals(from)) {
			from = null;
		}
		if(from != null) {
			try {
				// 表示するレコードの最大値を設定する（初期値）
				outPutMax = Integer.valueOf(from);
				outPutMax -=1;
				
				// 変数outPutMaxを-5した値を表示するレコードの最小値に設定する
				outPutMin = outPutMax - 5;
System.out.println(outPutMax);
System.out.println(outPutMin);
				
				// レコードの最小値が0以下の時の最小値に「0」を設定する
				if (outPutMin < 0 ) {
					outPutMin = 0;
				}

				// レコードの最大値が0以下の時に最大値に「0」を設定する
				if(outPutMax < 0) {
					outPutMax = 0;
				}

				// URLパラメータの値-1を表示するレコードの最大値に代入する
				outPutMax = Integer.parseInt(from);
				outPutMax -= 1;
			} catch (NumberFormatException e) {
				// 数字以外の値が含まれているため500エラー
				getServletConfig().getServletContext().getRequestDispatcher("/web/ja/500.jsp").forward(request, response);

			}
			
		}else {
			
			from="6";
		}
		
		// レコード値の最小値をString型に変換し、値を送信する。
		String min = Integer.toString(outPutMin);
		request.setAttribute("OutPutMin", min);

		// レコード値の最大値をString型に変換し、値を送信する。
		String max = Integer.toString(outPutMax);
		request.setAttribute("OutPutMax", max);
		
		return from;
	}
	
	/**
	 * 一ヶ月前または、一年前の日付のエポック秒を取得する。
	 * 
	 * @param time	一ヶ月分のエポック秒または、一年分のエポック秒
	 * 
	 * @return	diff	現在時刻のエポック秒から一ヶ月または、一年分のエポック秒を引いた値
	 */
	private Long getAgo(long time) {

		// 現在のエポック秒を取得
		Date date = new Date();
		Long nowEpoch = new Long(date.getTime()/1000);
	
		// 差分を算出
		Long diff = nowEpoch - time;
		
		// 小数点以下を切り捨てる処理
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(0);

		return diff;

	}

	/**
	 * 公開日時の表示ラベルを取得する。
	 * 
	 * @param release_datetime	公開日
	 * 
	 * @return	resultVal	公開日時の表示ラベル
	 */
	private String getLastUploadTime(Double release_datetime) {

		String resultVal;
		double d_releaseDay = 0;

		// 現在のエポック秒を取得
		Date date = new Date();
		Double nowEpoch = (double) date.getTime();

		// 差分を算出
		Double diff = nowEpoch - release_datetime * 1000;

		// 小数点以下を切り捨てる処理
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(0);

		// 公開時間を取得
		// 1秒未満
		if (diff < 1000) {
			resultVal = "たった今";

		}
		// 1秒以上かつ2秒未満
		else if (diff < 2000) {
			resultVal = "1秒前";

		}
		// 2秒以上かつ60秒未満
		else if (diff < 60000) {
			resultVal = diff + "秒前";

		}
		// 1分以上かつ2分未満
		else if (diff < 120000) {
			resultVal = "1分前";

		}
		// 2分以上かつ60分未満
		else if (diff < 3600000) {
			d_releaseDay = (diff / 60000);
			resultVal = numberFormat.format(d_releaseDay) + "分前";

		}
		// 1時間以上かつ2時間未満
		else if (diff < 7200000) {
			resultVal = "1時間前";

		}
		// 2時間以上かつ24時間未満
		else if (diff < 86400000) {
			d_releaseDay = (diff / 3600000);
			resultVal = numberFormat.format(d_releaseDay) + "時間前";

		}
		// 1日以上かつ2日未満
		else if (diff < 172800000) {
			resultVal = "1日前";

		}
		// 2日以上かつ7日未満
		else if (diff < 604800000) {
			d_releaseDay = (diff / 86400000);
			resultVal = numberFormat.format(d_releaseDay) + "日前";

		}
		// 7日以上かつ14日未満
		else if (diff < 1209600000) {
			resultVal = "1週間前";

		}
		// 14日以上かつ30日未満
		else if (diff < 2592000000L) {
			d_releaseDay = (diff / 604800000);
			resultVal = numberFormat.format(d_releaseDay) + "週間前";

		}
		// 30日以上かつ60日未満
		else if (diff < 5184000000L) {
			resultVal = "1ヶ月前";

		}
		// 60日以上かつ365日未満
		else if (diff < 31536000000L) {
			d_releaseDay = (diff / 2592000000L);
			resultVal = numberFormat.format(d_releaseDay) + "ヶ月前";

		}
		// 1年以上かつ2年未満
		else if (diff < 63072000000L) {
			resultVal = "1年前";

		}
		// 2年以上
		else {
			d_releaseDay = (diff / 31536000000L);
			resultVal = numberFormat.format(d_releaseDay) + "年前";

		}
		return resultVal;
	}
}
