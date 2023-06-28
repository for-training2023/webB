<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.excd.servlet.SongRecord"%>
<!DOCTYPE html>
<html>
<%
	List<SongRecord> songs = (List<SongRecord>) request.getAttribute("list");
%>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>音楽室</title>
<link rel="stylesheet" href="/web/css/main_1.css" />
<link rel="stylesheet" type="text/css" href="/web/css/menu.css" />
<link rel="stylesheet" type="text/css" href="/web/css/S00006.css" />
<script src="/web/js/jquery-3.3.0.min.js"></script>
<script type="text/javascript" src="/web/js/pageTop.js"></script>
<script type="text/javascript" src="/web/js/menu.js"></script>
<script type="text/javascript" src="/web/js/S00006.js"></script>

</head>
<body>
	<div id="overlay"></div>
	<div class="wrapper">

		<div class="main">
			<div class="left">
				<a id="backLink" href="#">
					<p>&lt;戻る</p>
				</a>
			</div>

			<div class="title">
				<h1>作品検索</h1>
			</div>
			<div class="right">
				<div class="tyousei">
					<img src="/web/images/menu_編集後.png" class="menu_top" alt="メニュー">
				</div>
			</div>
		</div>

		<div class="bottom_main">
			<div class="gaitou">
				<p><%=request.getAttribute("hits")%>件が該当します。
				</p>
			</div>
			<div class="resultTitle">
				<a href="#" class="btn-change" id="changeLink">条件変更</a>
			</div>
		</div>

		<div class="contents">
			<ul>
				<%
					for (SongRecord record : songs) {
				%>
				<li>
					<div class="detail">
						<h1><%=record.getTitle()%></h1>
						<div class="gazou">
							<a href="/web/ja/S00003/<%= record.getSong_id()%>"> <img src="/web/images/<%=record.getImage_file_name()%>" class="trimc" alt="">
							</a>
						</div>
						<p>
							<span class="sisuu">総感動指数:</span><span><%=record.getRating_total()%></span>
							<span class="sisuu">平均感動指数:</span><span><%=record.getRating_average()%></span><br>
							<span class="sisuu">再生回数</span><span><%=record.getTotal_listen_count()%></span>
							<span class="sisuu">公開:</span><span><%=record.getRelease_datetime()%></span>
						</p>
					</div>
				</li>
				<%
					}
				%>
			</ul>
			<div class="jouken2">
				<a id="changeLink2" href="#" class="btn-change2">条件変更</a>
			</div>
		</div>
	</div>

	<form id="formBack" method="POST" action="/web/ja/S00006/back">
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
	<form id="formChange" method="POST" action="/web/ja/S00006/change">
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

	<nav id="menu-conts" class="hidden">
		<div>
			メニュー
			<button type="button" id="close-btn">×CLOSE</button>
		</div>
		<ul>
			<li><a href="https://www.excd.jp/web/ja/S00001">HOME</a></li>
			<li><a href="https://www.excd.jp/web/ja/S00005">作品検索</a></li>
			<li><a href="https://www.excd.jp/web/ja/S00007">作曲家検索</a></li>
			<li><a href="https://www.excd.jp/web/ja/S00008">専用アプリダウンロード</a></li>
			<li><a href="https://excd.jp/">運営会社</a><a
				href="https://excd.jp/"><img src="/web/images/return.png"
					id="return" alt="メニュー"></a></li>
		</ul>
	</nav>

	<br>
	<hr>
	<footer>
		<p>
			Copyright &copy; <a href="https://www.excd.jp/top" style = "font-siza:15pt">EXCEED Co.,ltd. </a>
		</p>

		<div id="pagetop" hidden>
			<img src="/web/images/pagetop.png" alt="ページトップ">
		</div>

	</footer>

</body>
</html>
