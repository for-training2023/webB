<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="keywords" content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ">
	<meta name="description" content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">
	<link rel="stylesheet" href="/webB/css/main.css">
	<script src="/webB/js/jquery-3.3.0.min.js"></script>
	<script src="/webB/js/util.js"></script> 
	<!-- 画像の圧縮表示設定 -->
	<style>
	
	.message{
		width:90%;
		margin-left: auto;
		margin-right: auto;
		margin-top: 13px;
	}
	
	.box {
		padding-left: 24px;
		padding-top: 13px;
	}
	
	.box img {
		border-radius: 30px;
		display: block;
		margin-right: auto;
	}
	
	.shadow {
		box-shadow: 5px 5px 5px 1px rgba(0, 0, 0, 0.2);
	}
	
	#img1{
		width: 140px;
		height: 140px;
	}
	
	#img2{
		margin-top:20px;
		padding-left: 16px;
		width: 240px;
		height: 96px;
	}
	</style>
	
</head>
<body>

<!------------------------------------------------------------------------>

  <!-- メニューのキャンセルレイヤの起点 -->
  <div id="layer_marker">
  </div>

  <div class="wrapper">

    <!-- タイトルバー -->
    <div class="title_bar">
      <p class="page_title">アプリダウンロード</p>
      <a href="#" id="menu_open">
        <img alt="メニュー" src="/webB/images/menu.png" class="menu-icon">
      </a>
    </div>

    <!-- メニューの起点 -->
    <div id="menu_marker">
    </div>
<!------------------------------------------------------------------------>

    <div class="message">
    	<p>
		当コミュニティーの参加には、専用アプリ「メロコ」が必要です。<br>
		専用アプリ「メロコ」をお使いになれば、曲の視聴、コメントなど行うことができます。
		</p>
    </div>

<!------------------------------------------------------------------------>

	<div class="box">
		<img class="shadow" src="../images/melokoIcon.png" id="img1" onclick="location.href='https://apps.apple.com/jp/app/MelokoIcon-f-overview/id1440134774'">
	</div>
	
<!------------------------------------------------------------------------>

	<img src="../images/applestore.png" id="img2" onclick="location.href='https://apps.apple.com/jp/app/MelokoIcon-f-overview/id1440134774'">

<!------------------------------------------------------------------------>

		<!-- ページトップへjavaScript -->
    <div id="pagetop" hidden>
      <img alt="ページトップ" src="/webB/images/pagetop.png">
    </div>
    
<!------------------------------------------------------------------------>
    <!-- フッター -->
    <footer>
      Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a> All Rights Reserved.
    </footer>


</body>
</html>