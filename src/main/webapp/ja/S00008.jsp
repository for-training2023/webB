<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.excd.servlet.ComposerBean"%>
<!DOCTYPE html>
<html>
<%
	List<ComposerBean> composers = (List<ComposerBean>) request.getAttribute("list");
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



<style>
ul {
  list-style: none;  /* リストマーク（・）を消す*/
}

input.back {
  position:absolute;
  display: block;
  height: 40px;
  width: 60px;
  top: 0px;
  left: 0px;
  box-sizing: border-box;
  font-size: 12px;
  line-height: 40px;
  color: #333333;
  text-align:center;
  background-color: #cccccc;
  text-decoration: none;
  z-index: 10;
}

p.font_test3{
	font-size:  20px;
}

div.right_button input {
	display: block;
	box-sizing: border-box;
	font-size: 18px;
	text-align: center;
	width: 120;
	height: 36px;
	line-height: 36px;
	border: thin solid #333333;
	background-color: #cccccc;
	color: #000080;
	text-decoration: none;
	margin: 0 0 0 auto;
}

.horizon{
	border-color:  #1e90ff;
}

a.nickname_link{    /* 作曲家リンクの文字色指定 */
	color: #1e90ff;
	font-size:  25px;
}

.font_color {
    color: #1e90ff;     /* ラベルの文字色指定 */
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
		
			
		<form id="formBack"  method="post" action="/webB/ja/S00008/back">
		<input name="nickname_radio" type="hidden" value="<%= request.getAttribute("nickname_radio") %>">
		<input name="nickname_type_radio" type="hidden" value="<%= request.getAttribute("nickname_type_radio") %>">
		<input name="nickname" type="hidden" value="<%= request.getAttribute("nickname") %>">
		<input name="joined_date_radio" type="hidden" value="<%= request.getAttribute("joined_date_radio") %>">
		<input name="joined_date_from" type="hidden" value="<%= request.getAttribute("joined_date_from") %>">
		<input name="joined_date_to" type="hidden" value="<%= request.getAttribute("joined_date_to") %>">
		<input name="gender_radio" type="hidden" value="<%= request.getAttribute("gender_radio") %>">
		<input name="gender" type="hidden" value="<%= request.getAttribute("gender") %>">
		<input name="birthday_radio" type="hidden" value="<%= request.getAttribute("birthday_radio") %>">
		<input name="birthday_from" type="hidden" value="<%= request.getAttribute("birthday_from") %>">
		<input name="birthday_to" type="hidden" value="<%= request.getAttribute("birthday_to") %>">
		<input name="listener_count_radio" type="hidden" value="<%= request.getAttribute("listener_count_radio") %>">
		<input name="listener_count_from" type="hidden" value="<%= request.getAttribute("listener_count_from") %>">
		<input name="listener_count_to" type="hidden" value="<%= request.getAttribute("listener_count_to") %>">
		<input name="language_type_jp" type="hidden" value="<%= request.getAttribute("language_type_jp") %>">
		<input name="language_type_en" type="hidden" value="<%= request.getAttribute("language_type_en") %>">
		<input name="sort_order" type="hidden" value="<%= request.getAttribute("sort_order") %>">
		<input type="submit" class="back"  name="back" value="&lt;戻る">
		</form>
			
		<p class="page_title">作曲家検索</p>
		<a href="#" id="menu_open"> <img alt="メニュー" src="/webB/images/menu.png" class="menu-icon">
		</a>
		</div>
		
		<!-- メニューの起点 -->
		<div id="menu_marker"></div>

		<form id="formChange1" method="post" action="/webB/ja/S00008/change">
		<input name="nickname_radio" type="hidden" value="<%= request.getAttribute("nickname_radio") %>">
		<input name="nickname_type_radio" type="hidden" value="<%= request.getAttribute("nickname_type_radio") %>">
		<input name="nickname" type="hidden" value="<%= request.getAttribute("nickname") %>">
		<input name="joined_date_radio" type="hidden" value="<%= request.getAttribute("joined_date_radio") %>">
		<input name="joined_date_from" type="hidden" value="<%= request.getAttribute("joined_date_from") %>">
		<input name="joined_date_to" type="hidden" value="<%= request.getAttribute("joined_date_to") %>">
		<input name="gender_radio" type="hidden" value="<%= request.getAttribute("gender_radio") %>">
		<input name="gender" type="hidden" value="<%= request.getAttribute("gender") %>">
		<input name="birthday_radio" type="hidden" value="<%= request.getAttribute("birthday_radio") %>">
		<input name="birthday_from" type="hidden" value="<%= request.getAttribute("birthday_from") %>">
		<input name="birthday_to" type="hidden" value="<%= request.getAttribute("birthday_to") %>">
		<input name="listener_count_radio" type="hidden" value="<%= request.getAttribute("listener_count_radio") %>">
		<input name="listener_count_from" type="hidden" value="<%= request.getAttribute("listener_count_from") %>">
		<input name="listener_count_to" type="hidden" value="<%= request.getAttribute("listener_count_to") %>">
		<input name="language_type_jp" type="hidden" value="<%= request.getAttribute("language_type_jp") %>">
		<input name="language_type_en" type="hidden" value="<%= request.getAttribute("language_type_en") %>">
		<input name="sort_order" type="hidden" value="<%= request.getAttribute("sort_order") %>">	
		
		<div class="message_with_right_butto">
			<div class="right_button">
			<br>
				<p class = "font_test3">
				<%=request.getAttribute("hits")%>件が該当します。
				</p>
					<div class="right_button">
						<input type="submit" name="changeLink" value="　条件変更　">
						</input>
					</form>
				</div>
			</div>	
		</div>
		
		<!-- コンポーザーテーブル -->
		
		<form>
		<div class="detail">
			<ul>
				<%
					for (ComposerBean record : composers) {
				%>
				<li>
				
				<br>
				<hr class="horizon" />
				
						<a href="/webB/ja/S00004/<%=record.getUnique_code()%>"  class="nickname_link"><%=record.getNickname()%></a>
						<a href="/webB/ja/S00004/<%= record.getComposer_id()%>"> </a>
						
						<div class="detail">
							<table>
								<tr>
								  <p><span class="font_color">登録日：</span><%=record.getJoined_date()%></p>
								</tr>
								<tr>
									<%if(record.getGender() != null){%>
									<p><span class="font_color">性別：</span><%=record.getGender()%></p>
									<%} %>
								</tr>
								<tr>
									<%if(record.getBirthday() != null){%>
									<p><span class="font_color">誕生日：</span><%=record.getBirthday()%></p>
									<%} %>
								</tr>
								<tr>
									<p><span class="font_color">リスナー数：</span><%=record.getListener_count()%></p>
								</tr>
								<tr>
									<p><span class="font_color">言語：</span><%=record.getLanguage_type()%></p>
								</tr>	
							
							</table>
					</div>
				</li>
				<%
		}
				%>
			</ul>
		</form>
			
		<form id="formChange2" method="post" action="/webB/ja/S00008/change">
		<br>
		<div class="main_button">
		<input name="nickname_radio" type="hidden" value="<%= request.getAttribute("nickname_radio") %>">
		<input name="nickname_type_radio" type="hidden" value="<%= request.getAttribute("nickname_type_radio") %>">
		<input name="nickname" type="hidden" value="<%= request.getAttribute("nickname") %>">
		<input name="joined_date_radio" type="hidden" value="<%= request.getAttribute("joined_date_radio") %>">
		<input name="joined_date_from" type="hidden" value="<%= request.getAttribute("joined_date_from") %>">
		<input name="joined_date_to" type="hidden" value="<%= request.getAttribute("joined_date_to") %>">
		<input name="gender_radio" type="hidden" value="<%= request.getAttribute("gender_radio") %>">
		<input name="gender" type="hidden" value="<%= request.getAttribute("gender") %>">
		<input name="birthday_radio" type="hidden" value="<%= request.getAttribute("birthday_radio") %>">
		<input name="birthday_from" type="hidden" value="<%= request.getAttribute("birthday_from") %>">
		<input name="birthday_to" type="hidden" value="<%= request.getAttribute("birthday_to") %>">
		<input name="listener_count_radio" type="hidden" value="<%= request.getAttribute("listener_count_radio") %>">
		<input name="listener_count_from" type="hidden" value="<%= request.getAttribute("listener_count_from") %>">
		<input name="listener_count_to" type="hidden" value="<%= request.getAttribute("listener_count_to") %>">
		<input name="language_type_jp" type="hidden" value="<%= request.getAttribute("language_type_jp") %>">
		<input name="language_type_en" type="hidden" value="<%= request.getAttribute("language_type_en") %>">
		<input name="sort_order" type="hidden" value="<%= request.getAttribute("sort_order") %>">	
		<input type="submit" name="change2" value="条件変更">
		</div>
		</form>
			
		

		<!-- ページトップへjavaScript -->
		<div id="pagetop" hidden>
			<img alt="ページトップ" src = "/webB/images/pagetop.png">
		</div>

		<!-- フッター -->
		<footer>
			Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a> All
			Rights Reserved.
		</footer>

</body>
</html>