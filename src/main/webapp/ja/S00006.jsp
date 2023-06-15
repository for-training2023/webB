<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.excd.bean.SongRecord"%>
<%@ page import="jp.excd.bean.SongBean"%>
<!DOCTYPE html>
<html>
<%
	List<SongBean> songs = (List<SongBean>) request.getAttribute("list");
	System.out.println("00006");
%>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>音楽室</title>
<link rel="stylesheet" type="text/css" href="/webB/css/main.css">
<script type="text/javascript" src="/webB/js/jquery-3.3.0.min.js"></script>
<script type="text/javascript" src="/webB/js/input.js"></script>
<script type="text/javascript" src="/webB/js/util.js"></script>

<style>

div.song_list ul li div.cell div.gazou img {
	position: relative;
	left: 0px;
	top: -11px;
	width: 275px;
	height: 182px;
}

ul {
  list-style-type: none;
}

.exceed{
	text-align: center;
}

</style>

</head>
<body>
 	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker"></div>

	<div class="wrapper">
 	
	 	<!-- タイトルバー -->
		<div class="title_bar">
			<p class="page_title">作品検索</p>
			<a href="javascript:back.submit()" class="back">&lt;&nbsp;戻る</a>
			<a href="#" id="menu_open" class="menu_maker"> <img alt="メニュー" src="/webB/images/menu.png" class="menu-icon">
			</a>
		</div>
	
		<!-- メニューの起点 -->
		<div id="menu_marker"></div>
 	

		<!-- 検索結果表示 -->
		<div class="message_with_right_button">
			<p><%=request.getAttribute("hits")%>件が該当します。</p>
			<div class="right_button">
				<a href="javascript:change.submit()" class="btn-change" id="changeLink">条件変更</a>
			</div>
		</div>

 		

 		<div class="song_list">
 			<ul>
 				<%
 					for (SongBean record : songs) { //recordの部分は自分で設定できる
					
 				%>
 				<li>
 					<div class="cell">
 						<div class="detail">
 							<div class="song_title">
 								<h1><%=record.getTitle()%></h1> <!-- ドットより左はfor文のrecordに対応している -->
 							</div>
 							<div class="image_base">
 								
 									<a href="/webB/ja/S00003/<%= record.getSong_id()%>">  
 									<div class="image">
 									<img src="/webB/images/<%=record.getImage_file_name()%>" class="play" alt="play" src="/webB/ja/play.png">
 								</div>
 								</a>
 							</div>
 						
 							<div>
 								<p>
									<span class="label">総感動指数:</span><span class="value"><%=record.getRating_total_formated()%></span>
		 							<span class="label">平均感動指数:</span><span class="value"><%=record.getRating_average_formated()%></span><br>
		 							<span class="label">再生回数:</span><span class="value"><%=record.getTotal_listen_count_formated()%></span>
		 							<span class="label">公開:</span><span class="value"><%=record.getRelease_datetime_formated()%></span>
 								</p>
 							</div>
 						</div>
 					</div>
 				</li>
				<%
					}
				%>
 			</ul>
 			<div class="main_button">
			<a href="/webB/ja/S00006/change">条件変更</a>
		</div>
 		</div>
 	</div>

 	<form name="back" id="formBack" method="POST" action="/webB/ja/S00006/back">
 		<input name="release_date_is_radio" type="hidden" value="<%= request.getAttribute("release_date_is_radio") %>">
		<input name="release_date_is_from" type="hidden" value="<%= request.getAttribute("release_date_is_from") %>">
 		<input name="release_date_is_to" type="hidden" value="<%= request.getAttribute("release_date_is_to") %>">
 		<input name="rating_radio" type="hidden" value="<%= request.getAttribute("rating_radio") %>">
 		<input name="rating_from" type="hidden" value="<%= request.getAttribute("rating_from") %>">
 		<input name="rating_to" type="hidden" value="<%= request.getAttribute("rating_to") %>">
 		<input name="rating_average_radio" type="hidden" value="<%= request.getAttribute("rating_average_radio") %>">
 		<input name="rating_average_from" type="hidden" value="<%= request.getAttribute("rating_average_from") %>">
 		<input name="rating_average_to" type="hidden" value="<%= request.getAttribute("rating_average_to") %>">
 		<input name="views_radio" type="hidden" value="<%= request.getAttribute("views_radio") %>">
 		<input name="views_from" type="hidden" value="<%= request.getAttribute("views_from") %>">
 		<input name="views_to" type="hidden" value="<%= request.getAttribute("views_to") %>">
 		<input name="title_radio" type="hidden" value="<%= request.getAttribute("title_radio") %>">
 		<input name="title_type_radio" type="hidden" value="<%= request.getAttribute("title_type_radio") %>">
 		<input name="title" type="hidden" value="<%= request.getAttribute("title") %>">
 		<input name="sort_order" type="hidden" value="<%= request.getAttribute("sort_order") %>">
 	</form>
 	<form name="change" id="formChange" method="POST" action="/webB/ja/S00006/change">
 		<input name="release_date_is_radio" type="hidden" value="<%= request.getAttribute("release_date_is_radio") %>">
 		<input name="release_date_is_from" type="hidden" value="<%= request.getAttribute("release_date_is_from") %>">
 		<input name="release_date_is_to" type="hidden" value="<%= request.getAttribute("release_date_is_to") %>">
 		<input name="rating_radio" type="hidden" value="<%= request.getAttribute("rating_radio") %>">
 		<input name="rating_from" type="hidden" value="<%= request.getAttribute("rating_from") %>">
 		<input name="rating_to" type="hidden" value="<%= request.getAttribute("rating_to") %>">
 		<input name="rating_average_radio" type="hidden" value="<%= request.getAttribute("rating_average_radio") %>">
		<input name="rating_average_from" type="hidden" value="<%= request.getAttribute("rating_average_from") %>">
 		<input name="rating_average_to" type="hidden" value="<%= request.getAttribute("rating_average_to") %>">
 		<input name="views_radio" type="hidden" value="<%= request.getAttribute("views_radio") %>">
 		<input name="views_from" type="hidden" value="<%= request.getAttribute("views_from") %>">
 		<input name="views_to" type="hidden" value="<%= request.getAttribute("views_to") %>">
 		<input name="title_radio" type="hidden" value="<%= request.getAttribute("title_radio") %>">
 		<input name="title_type_radio" type="hidden" value="<%= request.getAttribute("title_type_radio") %>">
 		<input name="title" type="hidden" value="<%= request.getAttribute("title") %>">
 		<input name="sort_order" type="hidden" value="<%= request.getAttribute("sort_order") %>">
 	</form>


 	<br>
 	<hr>
 	<footer>
 		<div class="exceed">
 			Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a> All Rights Reserved.
 		</div>

 		<div id="pagetop" hidden>
 			<img src="/webB/images/pagetop.png" alt="ページトップ">
 		</div>

	</footer>

</body>
</html>
