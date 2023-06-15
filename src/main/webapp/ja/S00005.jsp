<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<%
	// (1) 「エラー情報(error)」が設定されている場合は、画面に「エラー情報(error)」を表示する。
	String errorMessage = (String)request.getAttribute("error");
    if (errorMessage == null) errorMessage = "";

	// (2) 「公開日_エラー状態(release_date_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
	String release_date_is_error = "";
	if ("1".equals(request.getAttribute("release_date_is_error"))) {
		release_date_is_error = ", error";
	}

	// (3) 以下の項目を元に公開日の入力状態を再現する。
	String release_date_Radio1 = "";
    if ("1".equals(request.getAttribute("release_date_is_radio"))) {
    	release_date_Radio1 = "checked=\"checked\"";
    }
	String release_date_Radio2 = "";
	if ("0".equals(request.getAttribute("release_date_is_radio"))) {
		release_date_Radio2 = "checked=\"checked\"";
	}
	String release_date_is_from = (String)request.getAttribute("release_date_is_from");
	String release_date_is_to = (String)request.getAttribute("release_date_is_to");

	// (4) 「感動指数_エラー状態(rating_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
	String rating_is_error = "";
	if ("1".equals(request.getAttribute("rating_is_error"))) {
		rating_is_error = ", error";
	}

	// (5) 以下の項目を元に感動指数の入力状態を再現する。
	String rating_radio1 = "";
	if ("1".equals(request.getAttribute("rating_radio"))) {
		rating_radio1 = "checked=\"checked\"";
	}
	String rating_radio2 = "";
	if ("0".equals(request.getAttribute("rating_radio"))) {
		rating_radio2 = "checked=\"checked\"";
	}
	String rating_from = (String)request.getAttribute("rating_from");
	if (rating_from == null) rating_from = "";
	String rating_to = (String)request.getAttribute("rating_to");
	if (rating_to == null) rating_to = "";

	// (6) 「平均感動指数_エラー状態(rating_average_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
	String rating_average_is_error = "";
	if ("1".equals(request.getAttribute("rating_average_is_error"))) {
		rating_is_error = ", error";
	}
	
	// (7) 以下の項目を元に平均感動指数の入力状態を再現する。
	String rating_average_radio1 = "";
	if ("1".equals(request.getAttribute("rating_average_radio"))) {
		rating_radio1 = "checked=\"checked\"";
	}
	String rating_average_radio2 = "";
	if ("0".equals(request.getAttribute("rating_average_radio"))) {
		rating_radio2 = "checked=\"checked\"";
	}
	String rating_average_from = (String)request.getAttribute("rating_average_from");
	if (rating_average_from == null) rating_average_from="1.0";
	String rating_average_to = (String)request.getAttribute("rating_average_to");
	if (rating_average_to == null) rating_average_to ="1.0";

	// (8) 「再生回数_エラー状態(views_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
	String views_is_error = "";
	if ("1".equals(request.getAttribute("views_is_error"))) {
		views_is_error = ", error";
	}
	
	// (9) 以下の項目を元に再生回数の入力状態を再現する。
	String views_radio1 = "";
	if ("1".equals(request.getAttribute("views_radio"))) {
		views_radio1 = "";
	}
	String views_radio2 = "";
	if ("0".equals(request.getAttribute("views_radio"))) {
		views_radio2 = "";
	}
	String views_from = (String)request.getAttribute("views_from");
	if (views_from == null) views_from = "";
	String views_to = (String)request.getAttribute("views_to");
	if (views_to == null) views_to = "";

	// (10) 「曲名_エラー状態(title_is_error)」= "1"の場合、divタグのクラス属性に errorを加える。
	String title_is_error = "";
	if ("1".equals(request.getAttribute("title_is_error"))) {
		title_is_error = ", error";
	}

	// (11) 以下の項目を元に曲名の入力状態を再現する。
	String title_radio1 = "";
	if ("1".equals(request.getAttribute("title_radio"))) {
		title_radio1 = "checked=\"checked\"";
	}
	String title_radio2 = "";
	if ("0".equals(request.getAttribute("title_radio"))) {
		title_radio2 = "checked=\"checked\"";
	}
	String title_type_radio1 = "";
	if ("3".equals(request.getAttribute("title_type_radio"))) {
		title_type_radio1 = "checked=\"checked\"";
	}
	String title_type_radio2 = "";
	if ("4".equals(request.getAttribute("title_type_radio"))) {
		title_type_radio2 = "checked=\"checked\"";
	}
	String title = (String)request.getAttribute("title");
	if (title == null) title = "";

	// (12) 以下の項目を元に並び順の入力状態を再現する。
	String sort_order = (String)request.getAttribute("sort_order");
	if (sort_order == null) sort_order="01";
%>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link rel="stylesheet" type="text/css" href="/webB/css/main.css">
<script type="text/javascript" src="/webB/js/jquery-3.3.0.min.js"></script>
<script type="text/javascript" src="/webB/js/util.js"></script>
<script type="text/javascript" src="/webB/js/input.js"></script>


<script>
$(function(){
	$("#id_rating_average_from").val("<%= rating_average_from %>");
	$("#id_rating_average_to").val("<%= rating_average_to %>");
	$("#id_sort_order").val("<%= sort_order %>");
});
</script>
<title>音楽室</title>

<style>

	.exceed{
	text-align: center;
	}

</style>

</head>

<body>
	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker"></div>

		<!--ページ全体のラッパー-->
		<div class="wrapper">

			<!-- タイトルバー -->
			<div class="title_bar">
			<p class="page_title">作品検索</p>
			<a href="#" id="menu_open"> <img alt="メニュー" src="/webB/images/menu.png" class="menu-icon">
			</a>
			</div>

			<!-- メニューの起点 -->
			<div id="menu_marker"></div>

			<!--エラーメッセージ -->
			<%
			if ("".equals(errorMessage) == false) {
			%>
			<div class="error_message">
			<img alt="エラー" src="/webB/images/error_mark.png">
			<p><%= errorMessage %></p>
			</div>
			<% } %>

			<!-- フォーム -->
			<form method="post" name="form1" action="/webB/ja/S00005/search">

			<!--条件divをまとめるdiv(contents)-->
			<div class="contents_search">

				<!-- 条件タイトル、フォームをまとめるdiv-->
				<div id="jouken_date" class="jouken<%= release_date_is_error %>">

					<!--条件のタイトル-->
					<div class="input_table">
						<table>
							<tr>
							<td class="label" rowspan=2 >公開日</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td>
											<input type="radio" id="id_release_date_radio1" 
											name="release_date_radio" value="1" class="onOffRadio" <%= release_date_Radio1 %>><span class="radio_label">指定 </span></td>
										<td>
											<input type="radio" id="id_release_date_radio2" 
											name="release_date_radio" value="0" class="onOffRadio" <%= release_date_Radio2 %>><span class="radio_label">指定なし </span></td>
									</tr>
								</table>
							</td>
							</tr>
							<tr>
							<td class="value">
								<input type="date" id="id_release_date_from"
								name="release_date_from" value="<%= release_date_is_from %>">
								<p>～</p>
								<input type="date" id="id_release_date_to"
								name="release_date_to" value="<%= release_date_is_to %>">
							</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 感動指数 -->
				<div id="jouken_rating" class="jouken<%= rating_is_error %>">
					<div class="input_table">
						<table>
							<tr>
							<td class="label" rowspan=2 >感動指数</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td>
											<input type="radio" id="id_rating_radio1" name="rating_radio"
											value="1" class="onOffRadio" <%= rating_radio1 %>><span class="radio_label">指定 </span></td>
										<td>
											<input type="radio" id="id_rating_radio2" name="rating_radio" 
											value="0" class="onOffRadio" <%= rating_radio2 %>><span class="radio_label">指定なし </span></td>
									</tr>
								</table>
							</td>
							</tr>
							<tr>
							<td class="value">
								<input type="text" id="id_rating_from" name="rating_from"
								maxlength='8' value="<%= rating_from %>">
								<p>～</p>
								<input type="text" id="id_rating_to" name="rating_to"
								maxlength='8' value="<%= rating_to %>">
							</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 平均感動指数 -->
				<div id="jouken_ratingAverage" class="jouken<%= rating_average_is_error %>">
					<div class="input_table">
						<table>
							<tr>
							<td class="label" rowspan=2 >平均感動指数</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td>
											<input type="radio" id="id_rating_average_radio1"
											name="rating_average_radio" value="1" class="onOffRadio" <%= rating_radio1 %>><span class="radio_label">指定 </span></td>
										<td>
											<input type="radio" id="id_rating_average_radio2"
											name="rating_average_radio" value="0" class="onOffRadio" <%= rating_radio2 %>><span class="radio_label">指定なし </span></td>
									</tr>
								</table>
							</td>
							</tr>
							<tr>
							<td class="value">
								<select id="id_rating_average_from" name="rating_average_from">
									<option value="1.0">1.0</option>
									<option value="1.1">1.1</option>
									<option value="1.2">1.2</option>
									<option value="1.3">1.3</option>
									<option value="1.4">1.4</option>
									<option value="1.5">1.5</option>
									<option value="1.6">1.6</option>
									<option value="1.7">1.7</option>
									<option value="1.8">1.8</option>
									<option value="1.9">1.9</option>
									<option value="2.0">2.0</option>
									<option value="2.1">2.1</option>
									<option value="2.2">2.2</option>
									<option value="2.3">2.3</option>
									<option value="2.4">2.4</option>
									<option value="2.5">2.5</option>
									<option value="2.6">2.6</option>
									<option value="2.7">2.7</option>
									<option value="2.8">2.8</option>
									<option value="2.9">2.9</option>
									<option value="3.0">3.0</option>
									<option value="3.1">3.1</option>
									<option value="3.2">3.2</option>
									<option value="3.3">3.3</option>
									<option value="3.4">3.4</option>
									<option value="3.5">3.5</option>
									<option value="3.6">3.6</option>
									<option value="3.7">3.7</option>
									<option value="3.8">3.8</option>
									<option value="3.9">3.9</option>
									<option value="4.0">4.0</option>
									<option value="4.1">4.1</option>
									<option value="4.2">4.2</option>
									<option value="4.3">4.3</option>
									<option value="4.4">4.4</option>
									<option value="4.5">4.5</option>
									<option value="4.6">4.6</option>
									<option value="4.7">4.7</option>
									<option value="4.8">4.8</option>
									<option value="4.9">4.9</option>
									<option value="5.0">5.0</option>
								</select>
								<p>～</p>
								<select id="id_rating_average_to" name="rating_average_to">
									<option value="1.0">1.0</option>
									<option value="1.1">1.1</option>
									<option value="1.2">1.2</option>
									<option value="1.3">1.3</option>
									<option value="1.4">1.4</option>
									<option value="1.5">1.5</option>
									<option value="1.6">1.6</option>
									<option value="1.7">1.7</option>
									<option value="1.8">1.8</option>
									<option value="1.9">1.9</option>
									<option value="2.0">2.0</option>
									<option value="2.1">2.1</option>
									<option value="2.2">2.2</option>
									<option value="2.3">2.3</option>
									<option value="2.4">2.4</option>
									<option value="2.5">2.5</option>
									<option value="2.6">2.6</option>
									<option value="2.7">2.7</option>
									<option value="2.8">2.8</option>
									<option value="2.9">2.9</option>
									<option value="3.0">3.0</option>
									<option value="3.1">3.1</option>
									<option value="3.2">3.2</option>
									<option value="3.3">3.3</option>
									<option value="3.4">3.4</option>
									<option value="3.5">3.5</option>
									<option value="3.6">3.6</option>
									<option value="3.7">3.7</option>
									<option value="3.8">3.8</option>
									<option value="3.9">3.9</option>
									<option value="4.0">4.0</option>
									<option value="4.1">4.1</option>
									<option value="4.2">4.2</option>
									<option value="4.3">4.3</option>
									<option value="4.4">4.4</option>
									<option value="4.5">4.5</option>
									<option value="4.6">4.6</option>
									<option value="4.7">4.7</option>
									<option value="4.8">4.8</option>
									<option value="4.9">4.9</option>
									<option value="5.0">5.0</option>
								</select>
							</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 再生回数 -->
				<div id="jouken_views" class="jouken<%= views_is_error %>">
					<div class="input_table">
						<table>
							<tr>
							<td class="label" rowspan=2 >再生回数</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td>
											<input type="radio" id="id_views_radio1" name="views_radio"
											value="1" class="onOffRadio" <%= views_radio1 %>><span class="radio_label">指定 </span></td>
										<td>
											<input type="radio"id="id_views_radio2" name="views_radio" 
											value="0" class="onOffRadio" <%= views_radio2 %>><span class="radio_label">指定なし </span></td>
									</tr>
								</table>
							</td>
							</tr>
							<tr>
							<td class="value">
								<input type="text" id="id_views_from" name="views_from"
								maxlength='8' value="<%= views_from %>">
								<p>～</p>
								<input type="text" id="id_views_to" name="views_to"
								maxlength='8' value="<%= views_to %>">
							</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 曲名 -->
				<div id="jouken_song_title" class="jouken<%= title_is_error %>">
					<div class="input_table">
						<table>
							<tr>
							<td class="label" rowspan=2 >曲名</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td>
											<input type="radio" id="id_song_title_radio1"
											name="song_title_radio" value="1" class="onOffRadio" <%=title_radio1 %>><span class="radio_label">指定 </span></td>
										<td>
											<input type="radio" id="id_song_title_radio2"
											name="song_title_radio" value="0" class="onOffRadio" <%=title_radio2 %>><span class="radio_label">指定なし </span></td>
									</tr>
								</table>
							</td>
							</tr>
							<tr>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td>
											<input type="radio" id="id_song_title_match_radio1"
											name="song_title_match_radio" value="3" <%=title_type_radio1 %>><span class="radio_label">あいまい </span></td>
										<td>
											<input type="radio" id="id_song_title_match_radio2"
											name="song_title_match_radio" value="4" <%=title_type_radio2 %>><span class="radio_label">完全一致 </span></td>
									</tr>
								</table>
									<br><input type="text" id="id_song_title_text"
										name="song_title_text" maxlength='255' value="<%=title%>">
							</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 並び順 -->	
				<div id="jouken_sort_order" class="jouken">
					<div class="input_table">
						<table>
							<tr>
							<td class="label">並び順</td>
							<td class="value">
								<select id="id_sort_order" name="sort_order">
									<option value="01">新しい順</option>
									<option value="02">古い順</option>
									<option value="03">感動指数が多い順</option>
									<option value="04">感動指数が少ない順</option>
									<option value="05">平均感動指数が高い順</option>
									<option value="06">平均感動指数が低い順</option>
									<option value="07">再生回数が多い順</option>
									<option value="08">再生回数が少ない順</option>
								</select>
							</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- メインボタン -->   
				<div class="main_button">
					<input type="submit" name="main_button" value="検索">
				</div>
				

			</div>
			</form>
		</div>

		<!-- ページトップへjavaScript -->
		<div id="pagetop" hidden>
			<img alt="ページトップ" src="/webB/images/pagetop.png">
		</div>

		<!-- フッター -->
		<footer>
			<div class="exceed">
				Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a> All Rights Reserved.
			</div>
		</footer>

</body>
</HTML>

