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
/* 	left: 0px;
	top: -11px; */
	width: 275px;
	height: 160px;
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

.song_list div.cell div.song1 img.songimage {
    height:100%;
    width:100%;
    object-fit:cover;
}

.overflow-wrap.normal {
  overflow-wrap: normal;
}

.overflow-wrap.break-word {
  overflow-wrap: break-word;
}

</style>
</head>
<body>
<%
String id = (String) request.getAttribute("id");//作曲家ID
String nickname = (String) request.getAttribute("nickname");//ニックネーム
String message = (String) request.getAttribute("message");//メッセージ
String joined_date = (String) request.getAttribute("joined_date");//登録日
String unique_code = (String) request.getAttribute("unique_code");//ユニークコード
String gender = (String) request.getAttribute("gender");//性別
String birthday = (String) request.getAttribute("birthday");//生年月日
String listener_count = (String) request.getAttribute("listener_count");//再生回数
String fb_link = (String) request.getAttribute("fb_link");//Facebookリンク
String tw_link = (String) request.getAttribute("tw_link");//Twiterリンク
String other_link_url = (String) request.getAttribute("other_link_url");//関連リンクURL
String other_link_description = (String) request.getAttribute("other_link_description");//関連リンク文字列
String songsum = (String) request.getAttribute("songsum");//作品数
String listensum = (String) request.getAttribute("listensum");//総再生回数
String rating_total = (String) request.getAttribute("rating_total");//総感動指
String total_listen_count = (String) request.getAttribute("total_listen_count");//総再生回数
String s_averageAll = (String) request.getAttribute("s_averageAll");//総平均
String image_file_name = (String) request.getAttribute("image_file_name");//画像名前
String ratingAll = (String) request.getAttribute("ratingAll");//総感動指数

String nothing = "データがありません。"; %>

<% List<Map<String, String>> songlist = (List<Map<String, String>>) request.getAttribute("songlist"); %>

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
						<% out.println(unique_code); //ユニークコード %>
					</td>
				</tr>
				<tr>
					<td class="label">ニックネーム</td>
					<td class="value">
						<% out.println(nickname); //ニックネーム %>
					</td>
				</tr>
			</table>
		</div>

		<!-- メッセージ -->
		<% if (message != null) { //メッセージがある場合　%>
		<div class="single_row_table">
			<table>
				<tr>
					<td class="label">メッセージ</td>
				</tr>
				<tr>
					<td class="value">
						<% out.println(message);// メッセージ %>
					</td>
				</tr>
			</table>
		</div>
		<% } else { //メッセージが無い場合は表示エリアを見せない
			;
			}  %>




		<!-- プロフィール -->
		<div class="single_row_table">
			<table>
				<tr>
					<td class="label">プロフィール</td>
				</tr>
				<tr>
				
					<!-- 性別の表示方法  性別が未定義または生年月日が未定義の場合の処理-->
					<td class="value"><span class="label_top">
					 <%
					 //性別が設定されている場合 性別を表示
					 if (gender != null) {
						 out.println("性別 :" + gender);
						 }
						 
					 //性別と生年月日の両方が設定されている場合 誕生日を表示
						if (gender != null && birthday != null) {
						 out.println("　" + "誕生日 :" + birthday);
						 //性別が未設定で、生年月日が設定されている場合 誕生日のみ表示
						} else if (gender == null && birthday != null) {
							 out.println("誕生日 :" + birthday);
							 //性別と生年月日の両方が未設定の場合 どちらも表示しない
						} else if (gender == null && birthday == null) {
							;
						}
								 
						//性別と生年月日のどちらか一方でも設定されている場合、表示後に改行する
						if (gender != null || birthday != null) { %> <br> <% } %>
						
						<!-- Facebook,Twitterリンク -->						
					 <span class="label_top">FB：</span> 
					 <span class="value"><a href="<%=fb_link%>"> <% out.println(fb_link);%></a></span>
					 <br>
					 <span class="label_top">Twitter：</span>
					 <span class="value"><a href="<%=tw_link%>"> <% out.println(tw_link); %></a></span>
					 </td>
					 
				</tr>
			</table>
		</div>

		<!-- 情報 -->
		<div class="single_row_table">
		 <div class="overflow-wrap">
      <div class="break-word">
			<table>
				<tr>
					<td class="label">情報</td>
				</tr>
				<tr>
					<td class="value">
					<span class="label_top">登録：</span>
					<span class="value">
					<% out.println(joined_date); // 登録日 %></span>
					<br>
					
					<span class="label_top">作品数：</span>
					<span class="value">
							<%
							//作品を一つでも持っている場合 作品数を表示する
							if (songsum != null) {
								out.println(songsum);
								//作品を一つも持っていない場合
								} else {
									out.println(nothing);
									} %>
					</span>
					<br>
					
					<span class="label_top" style="word-break: break-word">総感動指数：</span>
					
					<span class="value"> 
					<%
					//総感動指数が取得出来ている場合、int型に換えてフォーマットを適用した状態で表示する
					if (rating_total != null) {
						int ratingAll2 = Integer.parseInt(ratingAll);
						out.println(String.format("%,d", ratingAll2));
						//総感動指数を取得出来てない場合
						} else {
							out.println(nothing);
							} %>
					</span>
					<br>
						<span class="label_top">平均感動指数：</span>
						<span class="value">
							<%
							//平均感動指数が0のとき
							if (s_averageAll.equals("0.0")) {
								out.println(nothing);
								//平均感動指数が0でない場合　表示する
								} else {
									out.println(s_averageAll);
									} %>
						</span>
					<br>
					
					
					<span class="label_top">総再生回数：</span>
					
					<span class="value" style="word-break: break-word">
					<%
					//再生回数が一曲分でも取得出来ている場合 int型に換えてフォーマットを適用して表示する
					if (listensum != null) {
						Long listensum2 = Long.parseLong(listensum);
						out.println(String.format("%,d", listensum2));
						//再生回数を一曲も取得出来ていない場合
						} else {
							out.println(nothing);
							} %>
					</span>
					<br>
					</td>
					
				</tr>
			</table>
			</div>
			</div>
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
		
		<%
		//作品を一曲でも持っている場合
		if (songsum != null) { 
					%>
		<!-- 公開曲一覧のヘッダー -->
		<div class="sub_header">
			<p>公開曲一覧</p>
		</div>

		<!-- ソングテーブル -->
		<div class="song_list">
			<ul>

				<!--公開曲一覧で表示する情報はここから-->
				<%
				
					//曲が存在する分だけ繰り返し表示する
					for (int i = 0; i < songlist.size(); i++) {
					Map<String, String> map = songlist.get(i);
					map.get("song_id");//曲ID
					map.get("image_file_height");//画像高さ
					String image_name = map.get("image_file_name");//画像名前
					map.get("image_file_width");//画像幅 %>
				<li>
					<div class="cell">
						<div class="song_title"><%=map.get("title") %></div><!-- 曲名 -->
						<div class="image_base">
							<a href="/webB/ja/S00003/<%=map.get("song_id")%>"><!-- 曲ID -->
								<div class="image song1">
								
									<% 
									
									//画像が設定されている場合
									if (image_name != null) { %>
									
									<!-- リンクを参照して画像を表示する -->
									<img  class= "songimage" alt="<%=map.get("title")%>" src="/webB/images/<%=image_name%>">
									<img alt="play" class="play" src="/webB/images/play.png">
								</div>
							</a>
						</div>
						<% } else { //画像が未設定の場合 Noimageを表示させる %>
						<img alt="<%=image_name%>" src="/webB/images/noimage.png">
						<img alt="play" class="play" src="/webB/images/play.png">
					</div> </a>
		</div>
		<% } %>
		<div class="detail">
					<span class="label_top">総感動指数：</span>
					<span class="value"> <%=map.get("rating_total")%></span><!-- 総感動指数を表示 -->
					<span class="label">平均感動指数：</span>
					<span class="value"><%=map.get("r_average")%></span><!-- 平均感動指数を表示 -->
					<span class="label">再生回数：</span>
					<span class="value"> <%=map.get("total_listen_count")%></span><!-- 再生回数を表示 -->
					<span class="label">公開：</span>
					<span class="value"> <%=map.get("release_datetime")%></span><!-- 公開日を表示 -->
		</div>
	</div>
				</li>
	<% } %>
	<!--曲の情報ここまで-->
			</ul>
	</div>
	
	<%} else {
	;
	}%>


	<!-- ページトップへjavaScript -->
	<div id="pagetop" hidden>
		<img alt="ページトップ" src="/webB/images/pagetop.png">
	</div>

	<!-- フッター -->
	<footer>
		Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a>
		All Rights Reserved.
	</footer>

	</div>
</body>
</html>