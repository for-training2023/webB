<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="../css/main.css">
<script src="../js/jquery-3.3.0.min.js"></script>
<script src="../js/jquery-3.3.0.min.js"></script>
<script src="../js/util.js"></script>
<style>
div.title_bar {
	/* absoluteの起点 */
	position: relative;
	width: 100%;
	height: 40px;
	background-color: rgb(233, 233, 233);
	border-bottom: 1px solid #333;
}

div.wrapper {
	width: 350px;
	height: auto;
	margin: 0 auto;
	max-width: 100%;
	height: 500px;
	margin-bottom: 10px;
	border: 1px solid #333333;
}

.box {
	padding-left: 15px;
}

.box img {
	border-radius: 30px;
	display: block;
	margin-right: auto;
}

.shadow {
	box-shadow: -2px 5px 8px 3px rgba(0, 0, 0, 0.3);
}

.copy {
	text-align: center;
	margin: 0 auto;
	padding: 5px 30px 0.3px;
}

.copy p {
	text-align: left;
	font-size: 85%;
}
</style>


</head>
<body>


	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker"></div>

	<div class="wrapper">



		<!-- タイトルバー -->

		<!-- メニューの起点 -->
		<div id="menu_marker"></div>

		<div class="title_bar">
			<p class="page_title">アプリダウンロード</p>
			<a href="#" id="menu_open"> <img alt="メニュー"
				src="../images/menu.png" class="menu-icon">
			</a>
		</div>



		<p
			style="font-size: 93%; padding: 13px 13px 10px 13px; line-height: 1;">
			当コミュニティーの参加には、専用アプリ「メロコ」が必要です。<br>
			専用アプリ「メロコ」をお使いになれば、曲の視聴、コメントなど行うことができます。
		</p>
		<br>
		<div class="box">
			<img class="shadow" src="../images/melokoIcon.png" width="120px"
				height="120px" name="img1"
				onclick="location.href='https://apps.apple.com/jp/app/MelokoIcon-f-overview/id1440134774'">

		</div>
		<br>
		<img src="../images/applestore.png" name="img2" width="250px"
			height="110px"
			onclick="location.href='https://apps.apple.com/jp/app/MelokoIcon-f-overview/id1440134774'">
		<br> <br> <br> <br>
		<hr color="#66aacc" size="1">

		<div class="copy">
			<p>
				Copyright © <a href="https://www.excd.jp/">EXCEED Co., Ltd.</a> All
				Rights Reserved.
			</p>
		</div>
	</div>

	<!-- ページトップへjavaScript -->
	<div id="pagetop" hidden>
		<img alt="ページトップ" src="../images/pagetop.png">
	</div>

</body>
</html>