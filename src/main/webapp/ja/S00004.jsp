<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="keywords"
	content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ">
<meta name="description"
	content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">
<title>作曲家紹介</title>
<link rel="stylesheet" href="/webB/css/main.css">
<script src="/webB/js/jquery-3.3.0.min.js"></script>
<script src="/webB/js/util.js"></script>
<!-- 画像の圧縮表示設定 -->
<style>
div.song_list ul li div.cell div.song1 img {
	position: relative;
	left: 0px;
	top: -11px;
	width: 275px;
	height: 182px;
}

div.song_list ul li div.cell div.song2 img {
	position: relative;
	left: 0px;
	top: -134.5px;
	width: 275px;
	height: 429px;
}

div.song_list ul li div.cell div.song3 img {
	position: relative;
	left: 0px;
	top: -30.5px;
	width: 275px;
	height: 220px;
}
</style>
</head>
<body>
	<%
	String id = (String) request.getAttribute("id");
	String nickname = (String) request.getAttribute("nickname");
	String message = (String) request.getAttribute("message");
	String joined_date = (String) request.getAttribute("joined_date");
	String unique_code = (String) request.getAttribute("unique_code");
	String gender = (String) request.getAttribute("gender");
	String birthday = (String) request.getAttribute("birthday");
	String listener_count = (String) request.getAttribute("listener_count");
	String fb_link = (String) request.getAttribute("fb_link");
	String tw_link = (String) request.getAttribute("tw_link");
	String other_link_url = (String) request.getAttribute("other_link_url");
	String other_link_description = (String) request.getAttribute("other_link_description");
	String songsum = (String) request.getAttribute("songsum");//作品数
	String listenSum = (String) request.getAttribute("listenSum");
	String rating_total = (String) request.getAttribute("rating_total");
	String rating_average = (String) request.getAttribute("rating_average");
	String total_listen_count = (String) request.getAttribute("total_listen_count");
	String s_averageAll = (String) request.getAttribute("s_averageAll");

	String nothing = "データがありません。";
	%>

	<%
	List<Map<String, String>> SongList = (List<Map<String, String>>) request.getAttribute("SongList");
	%>

	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker"></div>

	<div class="wrapper">

		<!-- タイトルバー -->
		<div class="title_bar">
			<p class="page_title">作曲家紹介</p>
			<a href="#" id="menu_open"> <img alt="メニュー"
				src="/webB/images/menu.png" class="menu-icon">
			</a>
		</div>

		<!-- メニューの起点 -->
		<div id="menu_marker"></div>

		<!-- 基本情報 -->
		<div class="double_rows_table">
			<table>
				<tr>
					<td class="label">ID</td>
					<td class="value">
						<%
						out.println(unique_code);
						%>
					</td>
				</tr>
				<tr>
					<td class="label">ニックネーム</td>
					<td class="value">
						<%
						out.println(nickname);
						%>
					</td>
				</tr>
			</table>
		</div>

		<!-- メッセージ -->
		<%
		if (message != null) {
		%>
		<div class="single_row_table">
			<table>
				<tr>
					<td class="label">メッセージ</td>
				</tr>
				<tr>
					<td class="value">
						<%
						out.println(message);
						%>
					</td>
				</tr>
			</table>
		</div>
		<%
		} else {
		;
		}
		%>




		<!-- プロフィール -->
		<div class="single_row_table">
			<table>
				<tr>
					<td class="label">プロフィール</td>
				</tr>
				<tr>
					<!-- 性別の表示方法  性別が未定義または誕生日が未定義の場合の処理-->
					<td class="value"><span class="label_top"> <%
 if (gender != null) {
 	out.println("性別 :" + gender);
 }
 %> <%
 if (gender != null && birthday != null) {
 	out.println("　" + "誕生日 :" + birthday);
 } else if (gender == null && birthday != null) {
 	out.println("誕生日 :" + birthday);
 } else if (gender == null && birthday == null) {
 	;
 }
 %> <%
 if (gender != null || birthday != null) {
 %> <br> <%
 }
 %><span
							class="label_top">FB：</span> <span class="value"><a
								href="<%=fb_link%>"> <%
 out.println(fb_link);
 %>
							</a></span> <br> <span class="label_top">Twitter：</span> <span
							class="value"><a href="<%=tw_link%>"> <%
 out.println(tw_link);
 %>
							</a></span></td>
				</tr>
			</table>
		</div>

		<!-- 情報 -->
		<div class="single_row_table">
			<table>
				<tr>
					<td class="label">情報</td>
				</tr>
				<tr>
					<td class="value"><span class="label_top">登録：</span> <span
						class="value"> <%
 out.println(joined_date);
 %>
					</span> <br> <span class="label_top">作品数：</span> <span class="value">
							<%
							if (songsum != null) {
								out.println(songsum);
							} else {
								out.println(nothing);
							}
							%>
					</span> <br> <span class="label_top">総感動指数：</span> <span
						class="value"> <%
 if (rating_total != null) {
 	int rating_total2 = Integer.parseInt(rating_total);
 	out.println(String.format("%,d", rating_total2));
 } else {
 	out.println(nothing);
 }
 %>
					</span> <br> <span class="label_top">平均感動指数：</span> <span
						class="value"> <%
 if (s_averageAll.equals("0.0")) {
 	out.println(nothing);
 } else {
	 out.println(s_averageAll);
 }
 %>
					</span> <br> <span class="label_top">総再生回数：</span> <span
						class="value"> <%
 if (listenSum != null) {
 	int listenSum2 = Integer.parseInt(listenSum);
 	out.println(String.format("%,d", listenSum2));
 } else {
 	out.println(nothing);
 }
 %>
					</span> <br></td>
				</tr>
			</table>
		</div>

		<!-- 関連リンク -->
		<div class="single_row_table">
			<table>
				<tr>
					<td class="label">関連リンク</td>
				</tr>
				<tr>
					<td class="value"><a href="<%=other_link_url%>"><%=other_link_description%></a></td>
				</tr>
			</table>
		</div>

		<!-- 公開曲一覧のヘッダー -->
		<div class="sub_header">
			<p>公開曲一覧</p>
		</div>

		<!-- ソングテーブル -->
		<div class="song_list">
			<ul>

				<!--公開曲一覧で表示する情報はここから-->
				<%
				if (songsum != null) { //作品を持たない人は一覧を表示しない
				%>
				<%
				for (int i = 0; i < SongList.size(); i++) {
					Map<String, String> map = SongList.get(i);
					map.get("song_id");
					map.get("image_file_height");
					map.get("image_file_width");
				%>
				<li>
					<div class="cell">
						<div class="song_title"><%=map.get("title")%></div>
						<div class="image_base">
							<a href="/web/ja/S00003/<%=map.get("song_id")%>">
								<div class="image song1">
									<img alt="希望の扉"
										src="/webB/images/<%=map.get("image_file_name")%>"> <img
										alt="play" class="play" src="/webB/images/play.png">
								</div>
							</a>
						</div>
						<div class="detail">
							<span class="label_top">総感動指数：</span> <span class="value">
								<%=map.get("rating_total")%>
							</span> <span class="label">平均感動指数：</span> <span class="value"> <%=map.get("rating_average")%>
							</span> <span class="label">再生回数：</span> <span class="value"> <%=map.get("total_listen_count")%>
							</span> <span class="label">公開：</span> <span class="value"> <%=map.get("release_datetime")%>
							</span>
						</div>
					</div>
				</li>
				<%
				}
				%>
				<!--曲の情報ここまで-->
				<%
				} else {
				;
				}
				%>


			</ul>
		</div>


		<!-- ページトップへjavaScript -->
		<div id="pagetop" hidden>
			<img alt="ページトップ" src="../images/pagetop.png">
		</div>

		<!-- フッター -->
		<footer>
			Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a> All
			Rights Reserved.
		</footer>

	</div>
</body>
</html>