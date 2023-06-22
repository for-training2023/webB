package jp.excd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.excd.bean.SongBean;
import jp.excd.conponent.PlaceHolderInput;

public class S00005 extends HttpServlet {

	public void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		
		getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request, response);
	}

	public void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {

		Connection con = null;
		request.setCharacterEncoding("UTF-8");

		String dbName = "meloko";
		String userName = "meloko";
		String password = "exceed";
		String timeZone = "Asia/Tokyo";

		try {
			// (1)DB接続（コネクションの確立）
			con = MySQLSetting.getConnection (dbName, userName, password, timeZone);
			
			// (2)内部メソッド呼び出し
			this.mainProcessForSearch(request, response, con);

		} catch (Exception e) {
			e.printStackTrace();
			getServletConfig().getServletContext().getRequestDispatcher("/ja/500.jsp").forward(request, response);

		} finally {
			try {
				if (con != null) {
					
					// (3)接続したコネクションの切断を行う。
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				getServletConfig().getServletContext().getRequestDispatcher("/ja/500.jsp").forward(request, response);
			}

		}
	}
	
	private void mainProcessForSearch(HttpServletRequest request, HttpServletResponse response, Connection con)
			throws IOException, Exception {
		
		// 接続URL受け取り
		String URL = request.getRequestURI();

		// (1) 接続URLが「/ja/S00005/searh」以外の場合は、404.jspへフォワーディングする。
		if ("/webB/ja/S00005/search".equals(URL)) {
		} else {
			getServletConfig().getServletContext().getRequestDispatcher("/ja/404.jsp").forward(request, response);
		}

		// POSTパラメタの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// (2) 入力項目(POSTパラメタ)を使って、Requestオブジェクトのアトリビュートの初期化をする。
		String release_date_Radio = request.getParameter("release_date_radio");
		String release_date_from = request.getParameter("release_date_from");
		String release_date_to = request.getParameter("release_date_to");
		String rating_Radio = request.getParameter("rating_radio");
		String rating_from = request.getParameter("rating_from");
		String rating_To = request.getParameter("rating_to");
		String rating_average_radio = request.getParameter("rating_average_radio");
		String rating_average_from = request.getParameter("rating_average_from");
		String rating_average_to = request.getParameter("rating_average_to");
		String views_radio = request.getParameter("views_radio");
		String views_from = request.getParameter("views_from");
		String views_to = request.getParameter("views_to");
		String title_radio = request.getParameter("song_title_radio");
		String title_type_radio = request.getParameter("song_title_match_radio");
		String title = request.getParameter("song_title_text");
		String sort_order = request.getParameter("sort_order");

		//初期化
		request.setAttribute("error", null);
		request.setAttribute("release_date_is_error", null);
		request.setAttribute("release_date_is_radio", release_date_Radio);
		request.setAttribute("release_date_is_from", release_date_from);
		request.setAttribute("release_date_is_to", release_date_to);
		request.setAttribute("rating_is_error", null);
		request.setAttribute("rating_radio", rating_Radio);
		request.setAttribute("rating_from", rating_from);
		request.setAttribute("rating_to", rating_To);
		request.setAttribute("rating_aerage_is_error", null);
		request.setAttribute("rating_average_radio", rating_average_radio);
		request.setAttribute("rating_average_from", rating_average_from);
		request.setAttribute("rating_average_to", rating_average_to);
		request.setAttribute("views_is_error", null);
		request.setAttribute("views_radio", views_radio);
		request.setAttribute("views_from", views_from);
		request.setAttribute("views_to", views_to);
		request.setAttribute("title_is_error", null);
		request.setAttribute("title_radio", title_radio);
		request.setAttribute("title_type_radio", title_type_radio);
		request.setAttribute("title", title);
		request.setAttribute("sort_order", sort_order);

		Integer rf = null;
		Integer rt = null;
		Double rAf = null;
		Double rAt = null;
		Integer vf = null;
		Integer vt = null;

		//-----------------------------------------------------------------------------------------------
		// 入力チェック
		//-----------------------------------------------------------------------------------------------
		
		// (3) 公開日FROMについてエラー判定を行う。
		if ("1".equals(release_date_Radio)) {
			if (release_date_from == null || "".equals(release_date_from)) {
				// 処理継続
			} else if (this.isDateValue(release_date_from) == false) {
				// エラー
				String s = this.getDescription(con, "ES00005_001");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else {
				// 処理継続
			}
		}
		
		// (4) 公開日TOについてエラー判定を行う。
		if ("1".equals(release_date_Radio)) {
			if (release_date_to == null || "".equals(release_date_to)) {
				// 処理継続
			} else if (this.isDateValue(release_date_to) == false) {
				// エラー
				String s = this.getDescription(con, "ES00005_002");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
			} else {
				// 処理継続
			}
		}
		
		// (5) 公開日FROM、公開日TOについてエラー判定を行う。
		if ("1".equals(release_date_Radio)) {
			if (release_date_from == null || "".equals(release_date_from) &&
			(release_date_to == null || "".equals(release_date_to))) {
				//エラー
				String s = this.getDescription(con, "ES00005_003");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else if (release_date_from == null
					|| "".equals(release_date_from) && this.isDateValue(release_date_to) == true) {
				//処理続行
			} else if (this.isDateValue(release_date_from)) {
				//処理続行
			}
		}

		// (6) 公開日FROM、公開日TOについてエラー判定を行う。
		if ("1".equals(release_date_Radio)) {
			int checkResult = release_date_to.compareTo(release_date_from);
			if (checkResult < 0) {
				// エラー
				String s = this.getDescription(con, "ES00005_004");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			}
		}

		// (7) 感動指数FROMについてエラー判定を行う。
		if ("1".equals(rating_Radio)) {
			if (rating_from == null || "".equals(rating_from)) {
				// 処理継続
			} else if (this.isNumber(rating_from) == false) {
				// エラー
				String s = this.getDescription(con, "ES00005_005");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else {
				// 処理継続
				rf = Integer.parseInt(rating_from);
			}
		}

		// (8) 感動指数TOについてエラー判定を行う。
		if ("1".equals(rating_Radio)) {
			if (rating_To == null || "".equals(rating_To)) {
				//処理継続
			} else if (this.isNumber(rating_To) == false) {
				//エラー
				String s = this.getDescription(con, "ES00005_006");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else {
				//処理継続
				rt = Integer.parseInt(rating_To);
			}
		}
		
		// (9) 感動指数FROM 感動指数TO についてエラー判定を行う。
		if ("1".equals(rating_Radio))
			if ((rating_from == null || "".equals(rating_from) &&
			(rating_To == null || "".equals(rating_To)))) {
				//エラー
				String s = this.getDescription(con, "ES00005_007");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;
			} else if (rating_from == null || "".equals(rating_from) && this.isNumber(rating_To) == true) {
				//処理続行
			} else if (this.isNumber(rating_from)) {
				//処理続行
			}

		// (10) 感動指数FROM　感動指数TO（逆転チェック）
		if ("1".equals(rating_Radio)) {
			if (rating_from != null) {
				rf = Integer.parseInt(rating_from);
			}
			if (rating_To != null) {
				rt = Integer.parseInt(rating_To);
			}
			if (rAf != null || rAt != null || rf > rt) {
				//エラー
				String s = this.getDescription(con, "ES00005_008");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request,
						response);
				return;

			} else {
				//処理続行
			}
		}
		
		// (11) 平均感動指数FROM　平均感動指数TO エラーチェック
		if ("1".equals(rating_average_radio)) {
			if (rating_average_from != null) {
				rAf = Double.parseDouble(rating_average_from);
			}
			if (rating_average_to != null) {
				rAt = Double.parseDouble(rating_average_to);
			}
			if ((rAf != null || rAt != null) && (rAf > rAt)) {
				//エラー
				String s = this.getDescription(con, "ES00005_009");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
						.forward(request, response);
				return;
			}
		} else {
			//処理続行
		}

		// (12) 再生回数FROM エラー判定を行う。
		if ("1".equals(views_radio)) {
			if (views_from == null || "".equals(views_from)) {
				//処理継続
			} else if (this.isNumber(views_from) == false) {
				//エラー
				String s = this.getDescription(con, "ES00005_010");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
						.forward(request, response);
				return;
			} else {
				//処理継続
				vf = Integer.parseInt(views_from);
			}
		}
		
		// (13) 再生回数TO エラー判定を行う。
		if ("1".equals(views_radio)) {
			if (views_to == null || "".equals(views_to)) {
				//処理継続
			} else if (this.isNumber(views_to) == false) {
				//えらー
				String s = this.getDescription(con, "ES00005_011");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
						.forward(request, response);
				return;
			} else {
				//処理継続
				vt = Integer.parseInt(views_to);
			}
		}
		
		// (14) 再生回数FROM　再生回数TO エラー判定を行う。
		if ("1".equals(views_radio))
			if (views_from == null || "".equals(views_from) &&
			(views_to == null|| "".equals(views_to))) {
				//エラー
				String s = this.getDescription(con, "ES00005_012");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
						.forward(request, response);
				return;
			} else if (views_from == null
					|| "".equals(views_from) && this.isNumber(views_to) == true) {
				//処理続行
			} else if (this.isNumber(views_from)) {
				//処理続行
			}

		// (15) 再生回数FROM、再生回数TOについて、以下のとおりエラー判定を行う。
		if ("1".equals(views_radio)) {
			if (views_from != null) {
				vf = Integer.parseInt(views_from);
			}
			if (views_to != null) {
				vt = Integer.parseInt(views_to);
			}
			if ((vf != null || vt != null) && (vf > vt)){
				//エラー
				String s = this.getDescription(con, "ES00005_013");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
						.forward(request, response);
				return;
			}
		} else {
			//処理続行

		}

		// (16) 曲名についてエラー判定を行う。
		if ("1".equals(title_radio)) {
			if (title == null || "".equals(title)) {
				//エラー
				String s = this.getDescription(con, "ES00005_014");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "1");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp")
						.forward(request, response);
				return;
			} else {
				//処理続行
			}
		}
		
		// (17) SQLの組み立てと、Where句への値の設定を行う。
		List<SongBean> results = null;
		try {
			results = executeQuery(request, response, con,
					release_date_Radio,
					release_date_from,
					release_date_to,
					rating_Radio,
					rating_from,
					rating_To,
					rating_average_radio,
					rating_average_from,
					rating_average_to,
					views_radio,
					views_from,
					views_to,
					title_radio,
					title_type_radio,
					title,
					sort_order);
		} catch (Exception e) {
			String error = getDescription(con, "ES00005_015");
			request.setAttribute("error", error);
			e.printStackTrace();
			getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request, response);
			return;
		}
		
		if (results == null) {
			results = new ArrayList<SongBean>();
		}
		int listSize = results.size();

		// (18) ゼロ件チェック
		if (listSize == 0) {
			String error = getDescription(con, "ES00005_016");
			request.setAttribute("error", error);
			getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request, response);
			return;
		}
		
		// (19) 前処理で得られたListを用いて、Requestオブジェクトに値を設定していく。
		List<SongBean> newList =  new ArrayList<SongBean>();
		
		int counter = 0;
		
		for (SongBean l : results) {
			counter = counter + 1;
			// 先頭の10件のみ処理を行う。
			if (counter > 10) {
				counter = counter -1;
				break;
			}
			newList.add(l);
		}
		
		//件数表示をする
		int  kensu = 0;
		
		for (SongBean k : results) {
			kensu = kensu + 1;
			// 全hit件数処理を行う。
			if (kensu > kensu) {
				kensu = kensu -1;
				break;
			}
		}
		
		String count = NumberFormat.getNumberInstance().format(kensu);
		if(kensu > 10) {
			count = count + "件が該当します。（うち10件を表示しています。）";
		}else {
			count = count + "件が該当します。";
		}
		
		
		
		request.setAttribute("hits", count);
		request.setAttribute("list", newList);

		// (20) S00006.jsp にフォワーディングする。
		
		//request.getRequestDispatcher("/jsp/S00006.jsp").forward(request,response);
		
		getServletConfig().getServletContext().getRequestDispatcher("/ja/S00006.jsp").forward(request, response);
	}

	//文言マスタより引数で渡されたkeyをIDにもつレコードを取得

	private String getDescription(Connection con, String description_id)
			throws Exception {
		String ret = "";
		String sql = "select description from mst_description where description_id =?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, description_id);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			ret = rs.getString("description");
		}

		rs.close();
		pstmt.close();
		return ret;

	}

	private List<SongBean> executeQuery(HttpServletRequest request, HttpServletResponse response, Connection con,
			String release_date_radio,
			String release_date_from,
			String release_date_to,
			String rating_radio,
			String rating_from,
			String rating_to,
			String rating_average_radio,
			String rating_average_from,
			String rating_average_to,
			String views_radio,
			String views_from,
			String views_to,
			String title_radio,
			String title_type_radio,
			String title,
			String sort_order) throws Exception {

		@SuppressWarnings("unused")
		boolean joinFlg = false; // true:結合した、false：結合していない

		// (1) SQLの断片を準備する
		String sql1 = "SELECT id, title, rating_total, rating_average, total_listen_count, release_datetime ,image_file_name ";
		String sql2 = "FROM song ";
		String sql3 = "WHERE ";
		String sql4 = "release_datetime>=? ";
		String sql5 = "AND ";
		String sql6 = "release_datetime<=? ";
		String sql7 = "AND ";
		String sql8 = "rating_total >= ? ";
		String sql9 = "AND ";
		String sql10 = "rating_total<=? ";
		String sql11 = "AND ";
		String sql12 = "rating_average>=? ";
		String sql13 = "AND ";
		String sql14 = "rating_average<=? ";
		String sql15 = "AND ";
		String sql16 = "total_listen_count>=? ";
		String sql17 = "AND ";
		String sql18 = "total_listen_count<=? ";
		String sql19 = "AND ";
		String sql20 = "title like ";
		String sql21 = "title= ";
		String sql22 = "ORDER BY release_datetime desc";
		String sql23 = "ORDER BY release_datetime asc";
		String sql24 = "ORDER BY rating_total desc";
		String sql25 = "ORDER BY rating_total asc";
		String sql26 = "ORDER BY rating_average desc";
		String sql27 = "ORDER BY rating_average asc";
		String sql28 = "ORDER BY total_listen_count desc";
		String sql29 = "ORDER BY total_listen_count asc";
		String sql30 = " ,id desc;";

		// (2) SQLを連結するための文字列を宣言する。
		String query = sql1 + sql2;
		
		// (3) プレイスホルダに設定する値を格納するためのListを用意する。
		List<PlaceHolderInput> list = new ArrayList<PlaceHolderInput>();

		// (4) 公開日FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(release_date_radio)) {
			if (release_date_from == null || "".equals(release_date_from)) {
				// 処理続行
			}
			if (this.isDateValue(release_date_from)) {
				// No.3を連結する。
				query = query + sql3;
				// No,4を連結する
				query = query + sql4;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("2");
				phi.setDoubleValue(this.getDateValue(release_date_from));
				list.add(phi);
			}
		} else {
			// 処理続行
		}
		// (5) 公開日TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(release_date_radio)) {
			if (release_date_to == null || "".equals(release_date_to)) {
				//処理続行
			}
			if (this.isDateValue(release_date_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql5;
				}
				query = query + sql6;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("2");
				phi.setDoubleValue(this.getDateValue(release_date_to));
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}

		// (6) 感動指数FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(rating_radio)) {
			if (rating_from == null || "".equals(rating_from)) {
				//処理続行
			}
			if (this.isNumber(rating_from)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql7;
				}
				query = query + sql8;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(rating_from);
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (7) 感動指数TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(rating_radio)) {
			if (rating_to == null || "".equals(rating_to)) {
				//処理続行
			}
			if (this.isNumber(rating_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql9;
				}
				query = query + sql10;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(rating_to);
				list.add(phi);

			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (8) 平均感動指数FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(rating_average_radio)) {
			if (rating_average_from == null || "".equals(rating_average_from)) {
				//処理続行
			}
			if (this.isDouble(rating_average_from)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql11;
				}
				query = query + sql12;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("2");
				phi.setDoubleValue(Double.parseDouble(rating_average_from));
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (9) 平均感動指数TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(rating_average_radio)) {
			if (rating_average_to == null || "".equals(rating_average_to)) {
				//処理続行
			}
			if (this.isDouble(rating_average_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql13;
				}
				query = query + sql14;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("2");
				phi.setDoubleValue(Double.parseDouble(rating_average_to));
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (10) 再生回数FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(views_radio)) {
			if (views_from == null || "".equals(views_from)) {
				//処理続行
			} else if (this.isNumber(views_from)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql15;
				}
				query = query + sql16;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("1");
				phi.setIntValue(Integer.parseInt(views_from));
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (11) 再生回数TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(views_radio)) {
			if (views_to == null || "".equals(views_to)) {
				//処理続行
			} else if (this.isNumber(views_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql17;
				}
				query = query + sql18;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("1");
				phi.setIntValue(Integer.parseInt(views_to));
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		// (12) 曲名のSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(title_radio)) {
			if (title == null || "".equals(title)) {
				//処理続行
			} else {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql19;
				}
				if ("3".equals(title_type_radio)) {
					query = query + sql20 + "'%" + title + "%' ";
				} else if ("4".equals(title_type_radio)) {
					query = query + sql21 + "'" + title + "' ";
				} else {
					throw new Exception();
				}
			}
		} else {
			//処理続行
		}

		// (13) 並び順の値に従って、ORDER BY句を連結する。
		if ("01".equals(sort_order)) {
			query = query + sql22;
		} else if ("02".equals(sort_order)) {
			query = query + sql23;
		} else if ("03".equals(sort_order)) {
			query = query + sql24;
		} else if ("04".equals(sort_order)) {
			query = query + sql25;
		} else if ("05".equals(sort_order)) {
			query = query + sql26;
		} else if ("06".equals(sort_order)) {
			query = query + sql27;
		} else if ("07".equals(sort_order)) {
			query = query + sql28;
		} else if ("08".equals(sort_order)) {
			query = query + sql29;
		} else {
			throw new Exception();
		}

		query += sql30;

		// (14) PreparedStatementのインスタンスを得る。
		PreparedStatement pstmt = con.prepareStatement(query);

		// (15) Where句の連結があれば、(4)で生成したプレイスホルダ用のListの内容をすべて、プレイスホルダに設定する。
		for (int i = 0; i < list.size(); i++) {
			PlaceHolderInput option = list.get(i);
			String type = option.getType();
			if ("1".equals(type)) {
				pstmt.setInt(i + 1, option.getIntValue());
			} else if ("2".equals(type)) {
				pstmt.setDouble(i + 1, option.getDoubleValue());
			} else if ("3".equals(type)) {
				pstmt.setString(i + 1, option.getStringValue());
			}
		}

		// (16) executeQueryを実行し、結果の「ResultSet」を得る。
		ResultSet rs = pstmt.executeQuery();
		List<SongBean> songList = new ArrayList<SongBean>();

		while (rs.next()) {
			SongBean bean = new SongBean();
			//ソングID
			String Song_id = rs.getString("id");
			bean.setSong_id(Song_id);
			//曲名
			String Title = rs.getString("title");
			bean.setTitle(Title);
			//総評価数
			String Rating_total = NumberFormat.getNumberInstance().format(rs.getLong("rating_total"));
			bean.setRating_total_formated(Rating_total);
			//平均評価数
			double Rating_average = rs.getDouble("rating_average");
			bean.setRating_average_formated(String.valueOf(isRoundOff(Rating_average)));
			//再生回数
			String Total_listen_count = NumberFormat.getNumberInstance().format(rs.getLong("Total_listen_count"));
			bean.setTotal_listen_count_formated(Total_listen_count);
			//公開日
			double Release_datetime = rs.getDouble("release_datetime");
			bean.setRelease_datetime_formated(getLastUploadTime(Release_datetime));
			//ファイルネーム
			String Image_file_name = rs.getString("Image_file_name");
			if(Image_file_name == null || Image_file_name.equals("")) { //noimageの表示
				Image_file_name = "noimage.png";
			}
			bean.setImage_file_name(Image_file_name);
			//ファイルの高さ
			
			
			

			songList.add(bean);
		}
		
		
		// (17) ResultSetのインスタンス、PreparedStatementのインスタンスをクローズする。
		pstmt.close();

		// (18) 前処理で生成したListを呼び出し元に返却する。
		return songList;
	}
	
	private static double isRoundOff(double d) {
		 d = ((double)Math.round(d * 10))/10;
		 return d;
	}
	

	private boolean isNumber(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false; // エラーにならないように、とりあえずダミー
		}
	}

	private boolean isDouble(String num) {
		try {
			Double.parseDouble(num);
			return true;
		} catch (NumberFormatException e) {
			return false; // エラーにならないように、とりあえずダミー
		}
	}

	private boolean isDateValue(String value) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
			format.parse(value);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	private long getDateValue(String value) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = format.parse(value);
		long miliTime = dt.getTime();
		long retValue = (miliTime / 1000);
		return retValue;
	}

	/**
	 * 公開日時の表示ラベル取得
	 * @param release_datetime
	 * @return
	 */
	private String getLastUploadTime(Double release_datetime) {

		String resultVal;
		double d_releaseDay = 0;

		//現在のエポック秒を取得
		Date date = new Date();
		Double nowEpoch = (double) date.getTime();

		//差分を算出
		Double diff = nowEpoch - release_datetime * 1000;

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