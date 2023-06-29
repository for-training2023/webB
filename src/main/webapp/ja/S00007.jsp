<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.excd.servlet.S00008"%>

<!DOCTYPE html>
<html>
<%
//(0)hiddonで保持した値を取得する。
String errorMessage = (String) request.getAttribute("error");
String nickname_is_error = (String) request.getAttribute("nickname_is_error");
String nickname_radio = (String) request.getAttribute("nickname_radio");
String nickname_radio1 = "";
String nickname_radio2 = "";
String nickname_radio3 = "";
String nickname_type_radio1 = "";
String nickname_type_radio2 = "";
String nickname = (String) request.getAttribute("nickname");
String joined_date_is_error = (String) request.getAttribute("joined_date_is_error");
String joined_date_radio = (String) request.getAttribute("joined_date_radio");
String joined_date_radio1 = "";
String joined_date_radio2 = "";
String joined_date_radio3 = "";
String joined_date_from = (String) request.getAttribute("joined_date_from");
String joined_date_to = (String) request.getAttribute("joined_date_to");
String gender_is_error = (String) request.getAttribute("gender_is_error");
String gender_radio = (String) request.getAttribute("gender_radio");
String gender_radio1 = "";
String gender_radio2 = "";
String gender_radio3 = "";
String gender1 = "";
String gender2 = "";
String birthday_is_error = (String) request.getAttribute("birthday_is_error");
String birthday_radio = (String) request.getAttribute("birthday_radio");
String birthday_radio1 = "";
String birthday_radio2 = "";
String birthday_radio3 = "";
String birthday_from = (String) request.getAttribute("birthday_from");
String birthday_to = (String) request.getAttribute("birthday_to");
String listener_count_is_error = (String) request.getAttribute("listener_count_is_error");
String listener_count_radio = (String) request.getAttribute("listener_count_radio");
String listener_count_radio1 = "";
String listener_count_radio2 = "";
String listener_count_radio3 = "";
String listener_count_from = (String) request.getAttribute("listener_count_from");
String listener_count_to = (String) request.getAttribute("listener_count_to");
String language_type_is_error = (String) request.getAttribute("error");
String language_type_jp = (String) request.getAttribute("language_type_jp");
String language_type_en = (String) request.getAttribute("language_type_en");
String sort_order1 = "";
String sort_order2 = "";
String sort_order3 = "";
String sort_order4 = "";

// (1) 「エラー情報(error)」が設定されている場合は、画面に「エラー情報(error)」を表示する。
if (errorMessage == null) {
	errorMessage = "";
}

// (2) 「ニックネーム_エラー状態(nickname_is_error)」= "1"の場合

if ("1".equals((String) request.getAttribute("rating_from_error"))) {
	nickname_is_error = ", error";
}

// (2) バック時に「指定」が選択されていれば、赤色に表示する「（ nickname_radio1）」 =  "1"の場合

if ("1".equals(request.getAttribute("nickname_radio"))) {
	nickname_radio3 = " , required";
}

// (3) 以下の項目を元にニックネームの入力状態を再現する。
if ("1".equals((String) request.getAttribute("nickname_radio"))) {
	nickname_radio1 = "checked=\"checked\"";
} else {
	nickname_radio2 = "checked=\"checked\"";
}
if ("3".equals(request.getAttribute("nickname_type_radio"))) {
	nickname_type_radio1 = "checked=\"checked\"";
} else {
	nickname_type_radio2 = "checked=\"checked\"";
}
if (nickname == null) {
	nickname = "";
}

// (4) 「登録日_エラー状態(joined_date_is_error)」= "1"の場合
if ("2".equals(request.getAttribute("rating_from_error"))) {
	joined_date_is_error = ", error";
}

// (2) バック時に「指定」が選択されていれば、赤色に表示する「（ joined_date_radio）」 =  "1"の場合

if ("1".equals(request.getAttribute("joined_date_radio"))) {
	joined_date_radio3 = " , required";
}

// (5) 以下の項目を元に登録日の入力状態を再現する。
if ("1".equals(request.getAttribute("joined_date_radio"))) {
	joined_date_radio1 = "checked=\"checked\"";
} else {
	joined_date_radio2 = "checked=\"checked\"";
}
if (joined_date_from == null) {
	joined_date_from = "";
}
if (joined_date_to == null) {
	joined_date_to = "";
}

// (2) バック時に「指定」が選択されていれば、赤色に表示する「（ gender_radio）」 =  "1"の場合

if ("1".equals(request.getAttribute("gender_radio"))) {
	gender_radio3 = " , required";
}

// (6) 以下の項目を元に性別の入力状態を再現する。
if ("1".equals(request.getAttribute("gender_radio"))) {
	gender_radio1 = "checked=\"checked\"";
} else {
	gender_radio2 = "checked=\"checked\"";
}
if ("1".equals((String) request.getAttribute("gender"))) {
	gender1 = "selected=\"selected\"";
} else if ("2".equals((String) request.getAttribute("gender"))) {
	gender2 = "selected=\"selected\"";
}

// (7) 「誕生日_エラー状態(birthday_radio)」= "1"の場合
if ("3".equals(request.getAttribute("rating_from_error"))) {
	birthday_is_error = ", error";
}

// (2) バック時に「指定」が選択されていれば、赤色に表示する「（ birthday_radio）」 =  "1"の場合

if ("1".equals(request.getAttribute("birthday_radio"))) {
	birthday_radio3 = " , required";
}

// (8) 以下の項目を元に誕生日の入力状態を再現する。
if ("1".equals(request.getAttribute("birthday_radio"))) {
	birthday_radio1 = "checked=\"checked\"";
} else {
	birthday_radio2 = "checked=\"checked\"";
}
if (birthday_from == null) {
	birthday_from = "";
}
if (birthday_to == null) {
	birthday_to = "";
}

// (9) 「リスナー数_エラー状態(listener_count_is_error)」= "1"の場合
if ("4".equals(request.getAttribute("rating_from_error"))) {
	listener_count_is_error = ", error";
}

// (2) バック時に「指定」が選択されていれば、赤色に表示する「（ listener_count_radio）」 =  "1"の場合

if ("1".equals(request.getAttribute("listener_count_radio"))) {
	listener_count_radio3 = " , required";
}

// (10) 以下の項目を元にリスナー数の入力状態を再現する。

if ("1".equals(request.getAttribute("listener_count_radio"))) {
	listener_count_radio1 = "checked=\"checked\"";
} else {
	listener_count_radio2 = "checked=\"checked\"";
}
if (listener_count_from == null) {
	listener_count_from = "";
}
if (listener_count_to == null) {
	listener_count_to = "";
}

// (11) 「言語_エラー状態(language_type_is_error)」= "1"の場合
if ("5".equals(request.getAttribute("rating_from_error"))) {
	language_type_is_error = ", error";
}

// (12) 以下の項目を元に言語の入力状態を再現する。
if ("002".equals(request.getAttribute("language_type_jp"))) {
	language_type_jp = "checked=\"checked\"";
} else if ("001".equals(request.getAttribute("language_type_en"))) {
	language_type_en = "checked=\"checked\"";
}

// (13) 以下の項目を元に並び順の入力状態を再現する。
if ("1".equals(request.getAttribute("sort_order"))) {
	sort_order1 = "selected=\"selected\"";
} else if ("2".equals(request.getAttribute("sort_order"))) {
	sort_order2 = "selected=\"selected\"";
} else if ("3".equals(request.getAttribute("sort_order"))) {
	sort_order3 = "selected=\"selected\"";
} else if ("4".equals(request.getAttribute("sort_order"))) {
	sort_order4 = "selected=\"selected\"";
}
%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link rel="stylesheet" type="text/css" href="/webB/css/main.css">
<script type="text/javascript" src="/webB/js/jquery-3.3.0.min.js"></script>
<script type="text/javascript" src="/webB/js/util.js"></script>
<script type="text/javascript" src="/webB/js/input.js"></script>
<title>音楽室</title>

<script>
	//「ニックネーム_指定ラジオ(nickname_radio)」の「指定」を選んだら、「ニックネーム_指定タイプ(nickname_type_radio)」の「あいまい」を選択状態にする。
	const nickname_type_radio1 = document
			.getElementsByName("nickname_type_radio")

	function checkAll() {
		nickname_type_radio1[0].checked = true
	}
</script>


</head>
<body>
	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker"></div>

	<!--ページ全体のラッパー-->
	<div class="wrapper">

		<!-- タイトルバー -->
		<div class="title_bar">
			<p class="page_title">作曲家検索</p>
			<a href="#" id="menu_open"> <img alt="メニュー"
				src="/webB/images/menu.png" class="menu-icon">
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
			<p><%=errorMessage%></p>
		</div>
		<%
		}
		%>

		<!-- フォーム -->
		<form method="post" name="form1" action="/webB/ja/S00007/search">

			<!--条件divをまとめるdiv(contents)-->
			<div class="contents_search">



				<!-- ニックネーム -->
				<div id="jouken_nickname" class="jouken<%=nickname_is_error%>">
					<div class="input_table <%=nickname_radio3%>">
						<table>
							<tr>
								<td class="label" rowspan=2>ニックネーム</td>
								<td class="value">
									<table class="radio_base">
										<tr>
											<td><input type="radio" onclick="checkAll()"
												id="id_nickname_radio1" value="1" name="nickname_radio"
												class="onOffRadio" <%=nickname_radio1%>> <span
												class="radio_label">指定</span></td>
											<td><input type="radio" id="id_nickname_radio2"
												name="nickname_radio" value="2" class="onOffRadio"
												<%=nickname_radio2%>> <span class="radio_label">指定なし</span>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="value">
									<table class="radio_base">
										<tr>
											<td><input type="radio" id="id_nickname_type_radio1"
												name="nickname_type_radio" value="3"
												<%=nickname_type_radio1%>><span
												class="radio_label">あいまい</span></td>
											<td><input type="radio" id="id_nickname_type_radio2"
												name="nickname_type_radio" value="4"
												<%=nickname_type_radio2%>><span
												class="radio_label">完全一致</span></td>
										</tr>
									</table> <input type="text" id="id_nickname" name="nickname"
									maxlength="255" value="<%=nickname%>">
								</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 登録日 -->
				<div id="jouken_joined_date"
					class="jouken<%=joined_date_is_error%>">
					<div class="input_table <%=joined_date_radio3%>">
						<table>
							<tr>
								<td class="label" rowspan=2>登録日</td>
								<td class="value">
									<table class="radio_base">
										<tr>
											<td><input type="radio" id="id_joined_date_radio1"
												name="joined_date_radio" value="1" class="onOffRadio"
												<%=joined_date_radio1%>><span class="radio_label">指定</span>
											</td>
											<td><input type="radio" id="id_joined_date_radio2"
												name="joined_date_radio" value="2" class="onOffRadio"
												<%=joined_date_radio2%>><span class="radio_label">指定なし</span></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="value"><input type="date"
									id="id_joined_date_from" name="joined_date_from"
									value="<%=joined_date_from%>"> <br> ～ <br> <input
									type="date" id="id_joined_date_to" name="joined_date_to"
									value="<%=joined_date_to%>"></td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 性別（ラジオボタンをつけること） -->
				<div class="input_table <%=gender_radio3%>">
					<table>
						<tr>
							<td class="label" rowspan=2>性別</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td><input type="radio" id="id_gender_radio1"
											name="gender_radio" value="1" class="onOffRadio"
											<%=gender_radio1%>><span class="radio_label">指定</span>
										</td>
										<td><input type="radio" id="id_gender_radio2"
											name="gender_radio" value="2" class="onOffRadio"
											<%=gender_radio2%>><span class="radio_label">指定なし</span></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="value"><select id="id_gender" name="gender"
								tabindex="10">
									<option value="1" <%=gender1%>>男</option>
									<option value="2" <%=gender2%>>女</option>
							</select></td>
						</tr>
					</table>
				</div>
			</div>

			<!-- 誕生日 -->
			<%
			System.out.println(birthday_is_error);
			%>
			<%
			System.out.println(birthday_radio3);
			%>
			<div id="jouken_birthday" class="jouken<%=birthday_is_error%>">
				<div class="input_table <%=birthday_radio3%>">
					<table>
						<tr>
							<td class="label" rowspan=2>誕生日</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td><input type="radio" id="id_birthday_radio1"
											name="birthday_radio" value="1" class="onOffRadio"
											<%=birthday_radio1%>><span class="radio_label">指定</span></td>
										<td><input type="radio" id="id_birthday_radio2"
											name="birthday_radio" value="2" class="onOffRadio"
											<%=birthday_radio2%>><span class="radio_label">指定なし</span></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="value"><input type="date" id="id_birthday_from"
								name="birthday_from" value="<%=birthday_from%>"> <br>
								～ <br> <input type="date" id="id_birthday_to"
								name="birthday_to" value="<%=birthday_to%>"></td>
						</tr>
					</table>
				</div>
			</div>

			<!-- リスナー数 -->
			<div id="jouken_listener_count"
				class="jouken<%=listener_count_is_error%>">
				<div class="input_table <%=listener_count_radio3%>">
					<table>
						<tr>
							<td class="label" rowspan=2>リスナー数</td>
							<td class="value">
								<table class="radio_base">
									<tr>
										<td><input type="radio" id="listener_count_radio1"
											name="listener_count_radio" value="1" class="onOffRadio"
											<%=listener_count_radio1%>><span
											class="radio_label">指定</span></td>
										<td><input type="radio" id="listener_count_radio2"
											name="listener_count_radio" value="2" class="onOffRadio"
											<%=listener_count_radio2%>><span
											class="radio_label">指定なし</span></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="value"><input type="text"
								id="listener_count_from" name="listener_count_from"
								value="<%=listener_count_from%>" maxlength="8"> <br>
								～ <br> <input type="text" name="listener_count_to"
								value="<%=listener_count_to%>" maxlength="8"></td>
						</tr>
					</table>
				</div>
			</div>

			<!-- 言語 -->
			<div id="jouken_language_type"
				class="jouken<%=language_type_is_error%>">
				<div class="input_table">
					<table>
						<tr>
							<td class="label">言語</td>
							<td class="value"><input type="checkbox"
								id="language_type_jp" name="language_type_jp" value="002"
								<%=language_type_jp%>>日本語<br> <input
								type="checkbox" id="language_type_en" name="language_type_en"
								value="001" <%=language_type_en%>>英語</td>
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
							<td class="value"><select id="id_sort_order"
								name="sort_order" tabindex="10">
									<option value="1" <%=sort_order1%>>新しい順</option>
									<option value="2" <%=sort_order2%>>古い順</option>
									<option value="3" <%=sort_order3%>>リスナー数が多い順</option>
									<option value="4" <%=sort_order4%>>リスナー数が少ない順</option>
							</select></td>
						</tr>
					</table>
				</div>
			</div>

			<!-- メインボタン -->
			<div class="main_button">
				<input type="submit" name="main_button" value="検索">
			</div>

		</form>

	</div>

	<!-- ページトップへjavaScript -->
	<div id="pagetop" hidden>
		<img alt="ページトップ" src="/webB/images/pagetop.png">
	</div>

	<!-- フッター -->
	<footer>
		Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a> All
		Rights Reserved.
	</footer>

	</div>
</body>
</html>



