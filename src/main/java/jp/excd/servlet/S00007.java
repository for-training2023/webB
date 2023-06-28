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

public class S00007 extends HttpServlet {

	private static final String retValue = null;

	public void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		
		getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request, response);
	}

	public void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {

		Connection con = null;
		request.setCharacterEncoding("UTF-8");
		
		String dbName = "meloko";//DB名
		String userName = "meloko";//ユーザ
		String password = "exceed";//パスワード
		String timeZone = "Asia/Tokyo";//timeZone


		try {
			// (1)DB接続（コネクションの確立）
			con = MySQLSetting.getConnection (dbName, userName, password, timeZone);
			
			// (2)内部メソッド呼び出し
			this.mainProcessForSearchComposers(request, response, con);

		} catch (Exception e) {
			e.printStackTrace();
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/500.jsp").forward(request, response);

		} finally {
			try {
				if (con != null) {
					
					// (3)接続したコネクションの切断を行う。
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/500.jsp").forward(request, response);
			}

		}
	}
	
	private void mainProcessForSearchComposers(HttpServletRequest request, HttpServletResponse response, Connection con)
			throws IOException, Exception {
		
		// 接続URL受け取り
		String URL = request.getRequestURI();

		// (1) 接続URLが「/ja/S00007/searh」以外の場合は、404.jspへフォワーディングする。
		if ("/webB/ja/S00007/search".equals(URL)) {
		} else {
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
		}

		// POSTパラメタの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// (2) 入力項目(POSTパラメタ)を使って、Requestオブジェクトのアトリビュートの初期化をする。
		String nickname_radio = request.getParameter("nickname_radio");
		String nickname_type_radio = request.getParameter("nickname_type_radio");
		String nickname = request.getParameter("nickname");
		String joined_date_radio = request.getParameter("joined_date_radio");
		String joined_date_from = request.getParameter("joined_date_from");
		String joined_date_to = request.getParameter("joined_date_to");
		String gender_radio = request.getParameter("gender_radio");
		String gender = request.getParameter("gender");
		String birthday_radio = request.getParameter("birthday_radio");
		String birthday_from = request.getParameter("birthday_from");
		String birthday_to = request.getParameter("birthday_to");
		String listener_count_radio = request.getParameter("listener_count_radio");
		String listener_count_from = request.getParameter("listener_count_from");
		String listener_count_to = request.getParameter("listener_count_to");
		String language_type_jp = request.getParameter("language_type_jp");
		String language_type_en = request.getParameter("language_type_en");
		String sort_order = request.getParameter("sort_order");

		//初期化
		request.setAttribute("error", null);
		request.setAttribute("nickname_is_error", null);
		request.setAttribute("nickname_radio", nickname_radio);
		request.setAttribute("nickname_type_radio", nickname_type_radio);
		request.setAttribute("nickname", nickname);
		request.setAttribute("joined_date_is_error", null);
		request.setAttribute("joined_date_radio", joined_date_radio);
		request.setAttribute("joined_date_from", joined_date_from);
		request.setAttribute("joined_date_to", joined_date_to);
		request.setAttribute("gender_is_error", null);
		request.setAttribute("gender_radio", gender_radio);
		request.setAttribute("gender", gender);
		request.setAttribute("birthday_is_error", null);
		request.setAttribute("birthday_radio", birthday_radio);
		request.setAttribute("birthday_from", birthday_from);
		request.setAttribute("birthday_to", birthday_to);
		request.setAttribute("listener_count_is_error", null);
		request.setAttribute("listener_count_radio", listener_count_radio);
		request.setAttribute("listener_count_from", listener_count_from);
		request.setAttribute("listener_count_to", listener_count_to);
		request.setAttribute("language_type_is_error", null);
		request.setAttribute("language_type_jp", language_type_jp);
		request.setAttribute("language_type_en", language_type_en);
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
		
		// (3) ニックネームについてエラー判定を行う。
		//「ニックネーム」を入力するか、「指定しない」を選択してください。
				if ("1".equals(nickname_radio)) {
					if (nickname == null || "".equals(nickname)) {
						//エラー
						String s = this.getDescription(con, "ES00007_001");
						request.setAttribute("error", s);
						request.setAttribute("rating_from_error", "1");
						getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp")
								.forward(request, response);
						return;
					} else {
						//処理続行
						if (!("1".equals(nickname_radio))) {
							//処理続行
						}

					}
				}

		// (4) 登録日FROMについてエラー判定を行う。
		//「登録日」は、西暦で入力してください。
		if ("1".equals(joined_date_radio)) {
			if (joined_date_from == null || "".equals(joined_date_from)) {
				// 処理継続
			} else if (this.isDateValue(joined_date_from) == false) {
				// エラー
				String s = this.getDescription(con, "ES00007_002");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "2");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request,
						response);
				return;
			} else {
				// 処理継続
			}
		}
		
		// (5) 登録日TOについてエラー判定を行う。
		if ("1".equals(joined_date_radio)) {
			if (joined_date_to == null || "".equals(joined_date_to)) {
				// 処理継続
			} else if (this.isDateValue(joined_date_to) == false) {
				// エラー
				String s = this.getDescription(con, "ES00007_003");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "2");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request,
						response);
			} else {
				// 処理継続
			}
		}
		
		// (6) 登録日FROM、登録日TOについてエラー判定を行う。
		if ("1".equals(joined_date_radio)) {
			if (joined_date_from == null || "".equals(joined_date_from) &&
			(joined_date_to == null || "".equals(joined_date_to))) {
				//エラー
				String s = this.getDescription(con, "ES00007_004");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "2");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request,
						response);
				return;
			} else if (joined_date_from == null
					|| "".equals(joined_date_from) && this.isDateValue(joined_date_to) == true) {
				//処理続行
			} else if (this.isDateValue(joined_date_from)) {
				//処理続行
			}
		}

		// (7) 登録日FROM、登録日TOについてエラー判定を行う。
		if ("1".equals(joined_date_radio)) {
			int checkResult = joined_date_to.compareTo(joined_date_from);
			if (checkResult < 0) {
				// エラー
				String s = this.getDescription(con, "ES00007_005");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "2");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request,
						response);
				return;
			}
		}
		
		// (8) 誕生日FROMについてエラー判定を行う。
				if ("1".equals(birthday_radio)) {
					if (birthday_from == null || "".equals(birthday_from)) {
						// 処理継続
					} else if (this.isDateValue(birthday_from) == false) {
						// エラー
						String s = this.getDescription(con, "ES00007_006");
						request.setAttribute("error", s);
						request.setAttribute("rating_from_error", "3");
						getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request,
								response);
						return;
					} else {
						// 処理継続
					}
				}
				
		// (9) 誕生日TOについてエラー判定を行う。
				if ("1".equals(birthday_radio)) {
					if (birthday_to == null || "".equals(birthday_to)) {
						// 処理継続
					} else if (this.isDateValue(birthday_to) == false) {
						// エラー
						String s = this.getDescription(con, "ES00007_007");
						request.setAttribute("error", s);
						request.setAttribute("rating_from_error", "3");
						getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request,
								response);
					} else {
						// 処理継続
					}
				}
		
		// (10) 誕生日FROM、誕生日TOについてエラー判定を行う。
				if ("1".equals(birthday_radio)) {
					if (birthday_from == null || "".equals(birthday_from) &&
					(birthday_to== null || "".equals(birthday_to))) {
						//エラー
						String s = this.getDescription(con,"ES00007_008");
						request.setAttribute("error", s);
						request.setAttribute("rating_from_error", "3");
						getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request,
								response);
						return;
					} else if (birthday_from == null
							|| "".equals(birthday_from) && this.isDateValue(birthday_to) == true) {
						//処理続行
					} else if (this.isDateValue(birthday_from)) {
						//処理続行
					}
				}

		// (11) 誕生日FROM、誕生日TOについてエラー判定を行う。
				if ("1".equals(birthday_radio)) {
					int checkResult = birthday_to.compareTo(birthday_from);
					if (checkResult < 0) {
						// エラー
						String s = this.getDescription(con, "ES00007_009");
						request.setAttribute("error", s);
						request.setAttribute("birthday_from", "3");
						getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request,
								response);
						return;
					}
				}
		
		// (12) リスナー数FROM エラー判定を行う。
		if ("1".equals(listener_count_radio)) {
			if (listener_count_from == null || "".equals(listener_count_from)) {
				//処理継続
			} else if (this.isNumber(listener_count_from) == false) {
				//エラー
				String s = this.getDescription(con, "ES00007_010");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "4");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp")
						.forward(request, response);
				return;
			} else {
				//処理継続
				vf = Integer.parseInt(listener_count_from);
			}
			if (!("1".equals(listener_count_radio))) {
				//処理続行
			}
		}
		// (13) リスナー数TO エラー判定を行う。
		if ("1".equals(listener_count_radio)) {
			if (listener_count_to == null || "".equals(listener_count_to)) {
				//処理継続
			} else if (this.isNumber(listener_count_to) == false) {
				//エラー
				String s = this.getDescription(con, "ES00007_011");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "4");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp")
						.forward(request, response);
				return;
			} else {
				//処理継続
				vt = Integer.parseInt(listener_count_to);
			}
			if (!("1".equals(listener_count_radio))) {
				//処理続行
			}
		}
		
		// (14) リスナー数FROM　リスナー数TO エラー判定を行う。
		if ("1".equals(listener_count_radio))
			if (listener_count_from == null || "".equals(listener_count_from) &&
			(listener_count_to == null|| "".equals(listener_count_to))) {
				//エラー
				String s = this.getDescription(con, "ES00007_012");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "4");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp")
						.forward(request, response);
				return;
			} else if (listener_count_from == null
					|| "".equals(listener_count_from) && this.isNumber(listener_count_to) == true) {
				//処理続行
			} else if (this.isNumber(listener_count_from)) {
				//処理続行
			}
		if (!("1".equals(listener_count_radio))) {
			//処理続行
		}

		// (15) リスナー数FROM、リスナー数TOについて、以下のとおりエラー判定を行う。
		if ("1".equals(listener_count_radio)) {
			if (listener_count_from != null) {
				vf = Integer.parseInt(listener_count_from);
			}
			if (listener_count_to != null) {
				vt = Integer.parseInt(listener_count_to);
			}
			if ((vf != null || vt != null) && (vf > vt)){
				//エラー
				String s = this.getDescription(con, "ES00007_013");
				request.setAttribute("error", s);
				request.setAttribute("rating_from_error", "4");
				getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp")
						.forward(request, response);
				return;
			}
		} else {
			//処理続行

		}
		if (!("1".equals(listener_count_radio))) {
			//処理続行
		}

		// (16) 言語について、以下のとおりエラー判定を行う。
		if ("002".equals(language_type_jp)) {
			//処理続行
		}else if("001".equals(language_type_en)) {
			//処理続行
	    }else if(!("002".equals(language_type_jp)) && !("001".equals(language_type_en))){
	    	//エラー
			String s = this.getDescription(con, "ES00007_014");
			request.setAttribute("error", s);
			request.setAttribute("rating_from_error", "5");
			getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp")
					.forward(request, response);
			return;
		}
		
		// (17) SQLの組み立てと、Where句への値の設定を行う。
		List<ComposerBean> results = null;
		try {
			results = executeQuery(request, response, con,
					nickname_radio,
					nickname_type_radio,
					nickname,
					joined_date_radio,
					joined_date_from,
					joined_date_to,
					gender_radio,
					gender,
					birthday_radio,
					birthday_from,
					birthday_to,
					listener_count_radio,
					listener_count_from,
					listener_count_to,
					language_type_jp,
					language_type_en,
					sort_order);
		} catch (Exception e) {
			String error = getDescription(con, "ES00007_015");
			request.setAttribute("error", error);
			getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request, response);
			return;
		}
		
		if (results == null) {
			results = new ArrayList<ComposerBean>();
		}
		int listSize = results.size();

		// (18) ゼロ件チェック
		if (listSize == 0) {
			String error = getDescription(con, "ES00007_016");
			request.setAttribute("error", error);
			getServletConfig().getServletContext().getRequestDispatcher("/ja/S00007.jsp").forward(request, response);
			return;
		}
		
		// (19) 前処理で得られたListを用いて、Requestオブジェクトに値を設定していく。
		List<ComposerBean> newList =  new ArrayList<ComposerBean>();
		
		int counter = 0;
		int hit = 0;
		
		for (ComposerBean l : results) {
			counter = counter + 1;
			// 先頭の10件のみ処理を行う。
			if (counter > 10) {
				counter = counter -1;
				continue;
			}
			newList.add(l);
		}
		for (ComposerBean cr : results) {
			hit = hit + 1;
			// 件数の総数
			if (hit > hit) {
				hit = hit -1;
				break;
			}
		}
		
		String hits = NumberFormat.getNumberInstance().format(hit);
		request.setAttribute("hits", hits);
		request.setAttribute("list", newList);
		
		
		//S00008にフォワーディングする
		getServletConfig().getServletContext().getRequestDispatcher("/ja/S00008.jsp").forward(request, response);
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

	//(2)入力値のBean（ValueInputBean）を生成して、値を詰める。
	
	private List<ComposerBean> executeQuery(HttpServletRequest request, HttpServletResponse response, Connection con,
			String nickname_radio,
			String nickname_type_radio,
			String nickname,
			String joined_date_radio,
			String joined_date_from,
			String joined_date_to,
			String gender_radio,
			String gender,
			String birthday_radio,
			String birthday_from,
			String birthday_to,
			String listener_count_radio,
			String listener_count_from,
			String listener_count_to,
			String language_type_jp,
			String language_type_en,
			String sort_order) throws Exception {

		@SuppressWarnings("unused")
		boolean joinFlg = false; // true:結合した、false：結合していない

		// (1) SQLの断片を準備する
		String sql1 = " SELECT id, unique_code, nickname, joined_date, gender, birthday, listener_count, language_type ";
		String sql2 = " FROM composer ";
		String sql3 = " WHERE ";
		String sql4 = " nickname like ?";
		String sql5 = " nickname = ? ";
		String sql6 = " AND ";
		String sql7 = " joined_date >= ? ";
		String sql8 = " AND  ";
		String sql9 = " joined_date <= ? ";
		String sql10 = " AND ";
		String sql11 = " gender = ? ";
		String sql12 = " AND ";
		String sql13 = " birthday >= ? ";
		String sql14 = " AND ";
		String sql15 = " birthday <= ? ";
		String sql16 = " AND ";
		String sql17 = " listener_count >= ? ";
		String sql18 = " AND ";
		String sql19 = " listener_count <= ? ";
		String sql20 = " AND ";
		String sql21 = " language_type = ? ";
		String sql22 = " ORDER BY joined_date desc ";
		String sql23 = " ORDER BY joined_date asc ";
		String sql24 = " ORDER BY listener_count desc ";
		String sql25 = " ORDER BY listener_count asc ";

		// (2) SQLを連結するための文字列を宣言する。
		String query = sql1 + sql2;
		
		// (3) プレイスホルダに設定する値を格納するためのListを用意する。
		List<PlaceHolderInput> list = new ArrayList<PlaceHolderInput>();
		
		// (4) ニックネームのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(nickname_radio)) {
			if (nickname == null || "".equals(nickname)) {
				//処理続行
			} else if (list.size() == 0) {
					query = query + sql3;
					if ("3".equals(nickname_type_radio)) {
					query = query + sql4;
					nickname = '%'+ nickname + '%';
					PlaceHolderInput phi = new PlaceHolderInput();
					phi.setType("3");
					phi.setStringValue(nickname);
					list.add(phi);
					} else if ("4".equals(nickname_type_radio)) {
					query = query + sql5;// + "'" + nickname + "' ";
					PlaceHolderInput phi = new PlaceHolderInput();
					phi.setType("3");
					phi.setStringValue(nickname);
					list.add(phi);
					}
					
			}else {
				throw new Exception();
			} 
		}


		// (5) 登録日FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(joined_date_radio)) {
			if (joined_date_from == null || "".equals(joined_date_from)) {
				// 処理続行
			}
			if (this.isDateValue(joined_date_from)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql6;
				}
				query = query + sql7;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(joined_date_from);
				list.add(phi);
			}
		} else {
			//処理続行
		}

	
		// (6) 登録日TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(joined_date_radio)) {
			if (joined_date_to == null || "".equals(joined_date_to)) {
				//処理続行
			}
			if (this.isDateValue(joined_date_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql8;
				}
				query = query + sql9;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(joined_date_to);
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}

		// (7) 性別のSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(gender_radio)) {
			if (gender == null || "".equals(gender)) {
				//処理続行
			}
			if (this.isNumber(gender)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql10;
				}
				query = query + sql11;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(gender);
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		
		// (8) 誕生日FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(birthday_radio)) {
			if (birthday_from == null || "".equals(birthday_from)) {
				// 処理続行
			}
			if (this.isDateValue(birthday_from)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql12;
				}
				query = query + sql13;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(birthday_from);
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}

			
		// (9) 誕生日TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(birthday_radio)) {
			if (birthday_to == null || "".equals(birthday_to)) {
				//処理続行
			}
			if (this.isDateValue(birthday_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql14;
				}
				query = query + sql15;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(birthday_to);
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		
		// (10) リスナー数FROMのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(listener_count_radio)) {
			if (listener_count_from == null || "".equals(listener_count_from)) {
				//処理続行
			} else if (this.isNumber(listener_count_from)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql16;
				}
				query = query + sql17;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(listener_count_from);
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		
		// (11) リスナー数TOのSQLへの連結及びプレイスホルダへの設定
		if ("1".equals(listener_count_radio)) {
			if (listener_count_to == null || "".equals(listener_count_to)) {
				//処理続行
			} else if (this.isNumber(listener_count_to)) {
				if (list.size() == 0) {
					query = query + sql3;
				} else {
					query = query + sql18;
				}
				query = query + sql19;

				PlaceHolderInput phi = new PlaceHolderInput();
				phi.setType("3");
				phi.setStringValue(listener_count_to);
				list.add(phi);
			} else {
				throw new Exception();
			}
		} else {
			//処理続行
		}
		
		// (12) 言語のSQLへの連結及びプレイスホルダへの設定
		if ("001".equals(language_type_en)) {
			if (list.size() == 0) {
				query = query + sql3;
			} else {
				query = query + sql20;
			}
			query = query + sql21;

			PlaceHolderInput phi = new PlaceHolderInput();
			phi.setType("3");
			phi.setStringValue(language_type_en);
			list.add(phi);
		}else if ( "002".equals(language_type_jp)) {
			if (list.size() == 0) {
				query = query + sql3;
			} else {
				query = query + sql20;
			}
			query = query + sql21;

			PlaceHolderInput phi = new PlaceHolderInput();
			phi.setType("3");
			phi.setStringValue(language_type_jp);
			list.add(phi);
		}

		// (13) 並び順の値に従って、ORDER BY句を連結する。
		if ("1".equals(sort_order)) {
			query = query + sql22;
		} else if ("2".equals(sort_order)) {
			query = query + sql23;
		} else if ("3".equals(sort_order)) {
			query = query + sql24;
		} else if ("4".equals(sort_order)) {
			query = query + sql25;
		} else {
			throw new Exception();
		}
		
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
		List<ComposerBean> composerList = new ArrayList<ComposerBean>();

		Date d = new Date();
		
		while (rs.next()) {
			ComposerBean bean = new ComposerBean();
			//作曲家ID
			String Composer_id = rs.getString("id");
			bean.setComposer_id(Composer_id);
			//ニックネーム
			String Nickname = rs.getString("nickname");
			bean.setNickname(Nickname);
			//登録日
			String Joined_date = rs.getString("joined_date");
			if(Joined_date != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				try{
					d = dateFormat.parse(Joined_date);
				}catch(ParseException e) {
					e.printStackTrace();
				}
				dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
				Joined_date = dateFormat.format(d);
			}else {
				Joined_date = "null";
			}
			bean.setJoined_date(Joined_date);	
			
			//性別
			String Gender = rs.getString("gender");
			if("1".equals(Gender)) {
				Gender = "男";
			}else if("2".equals(Gender)) {
				Gender = "女";
			}else if(Gender == null) {
				Gender = null;
			}
			bean.setGender( Gender);
			
			//誕生日
			String Birthday = rs.getString("birthday");
			if(Birthday != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				try{
					d = dateFormat.parse(Birthday);
				}catch(ParseException e) {
					e.printStackTrace();
				}
				dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
				Birthday = dateFormat.format(d);
			}else if(Birthday == null){
				Birthday = null;
			}
			bean.setBirthday( Birthday);
			
			//リスナー数
			String Listener_count = NumberFormat.getNumberInstance().format(rs.getInt("listener_count"));
			bean.setListener_count( Listener_count);
			
			//言語区分
			String Language_type = rs.getString("language_type");
			
			if("001".equals(Language_type)) {
				String Language = "英語";
				bean.setLanguage_type(Language);	
			}else if("002".equals(Language_type)) {
				String Language = "日本語";
				bean.setLanguage_type(Language);	
			}
					

			composerList.add(bean);
		}
		
		// (17) ResultSetのインスタンス、PreparedStatementのインスタンスをクローズする。
		pstmt.close();

		// (18) 前処理で生成したListを呼び出し元に返却する。
		return composerList;
		
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
}