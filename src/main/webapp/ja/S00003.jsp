<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.Map" %>
<%@ page import= "java.util.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
String songId = (String)request.getAttribute("songId");
String title = (String)request.getAttribute("title");
String songComposerId = (String)request.getAttribute("songComposerId");
String ratingTotal = (String)request.getAttribute("ratingTotal");
String ratingAverage = (String)request.getAttribute("ratingAverage");
String totalListeningCount = (String)request.getAttribute("totalListeningCount");
String releaseDatetime = (String)request.getAttribute("releaseDatetime");
String lastUpdateDatetime = (String)request.getAttribute("lastUpdateDatetime");
String message = (String)request.getAttribute("message");
String key = (String)request.getAttribute("key");
String scoreType = (String)request.getAttribute("scoreType");
String bpm = (String)request.getAttribute("bpm");
String imageFileName = (String)request.getAttribute("imageFileName");
int imageFileHeight = (int)request.getAttribute("imageFileHeight");
int imageFileWidth = (int)request.getAttribute("imageFileWidth");
String otherLinkUrl = (String)request.getAttribute("otherLinkUrl");
String otherLinkDescription = (String)request.getAttribute("otherLinkDescription");
String uniqueCode = (String)request.getAttribute("uniqueCode");
String nickName = (String)request.getAttribute("nickName");
String commentUniqueCode = (String)request.getAttribute("commentUniqueCode");
String commentNickName = (String)request.getAttribute("commentNickName");
String rating = (String)request.getAttribute("rating");
String style = null;

%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="keywords" content="作曲アプリ,Meloko,楽譜,iPhone,iPad,iOS,MIDI,メロコ,作詞,作曲,コミュニティー,スマホ">
  <meta name="description" content="「メロコ」はiPhone,iPadで動作する作曲アプリです。思いついたメロディーをどんどん曲として保存していきましょう。">
  <title><%=title %></title>
  
  <link rel="stylesheet" href="/webB/css/main.css">
  
  <script src="/webB/js/jquery-3.3.0.min.js"></script>
  
  <script src="/webB/js/util.js"></script>
 
<!-- 画像の圧縮表示設定 -->
<style>

div.song_link div.cell div.song1 {
	position:relative;
    width :275px;
    height :160px;
}

.reply{
	position:relative;
	top:30px;
	left:-10px;
}

.song_link div.cell div.song1 img.songimage {
    height:100%;
    width:100%;
    object-fit:cover;
}

#composerComment{
	position:relative;
	top:20px;
}

#composerTime{
	position:relative;
	top:20px;
}




</style>
</head>
<body>
  <!-- メニューのキャンセルレイヤの起点 -->
  <div id="layer_marker">
  </div>

  <div class="wrapper">

    <!-- タイトルバー -->
    <div class="title_bar">
      <p class="page_title"><%=title %></p>
      <a href="#" id="menu_open">
        <img alt="メニュー" src="/webB/images/menu.png" class="menu-icon">
      </a>
    </div>

    <!-- メニューの起点 -->
    <div id="menu_marker">
    </div>

    <!-- 曲名 -->
    <div class="double_rows_table">
      <table>
        <tr>
          <td class="label">曲名</td>
          <td class="value"><%=title %></td>
        </tr>
      </table>
    </div>

    <!-- 作者へのリンク -->
    <div class="label_and_link">
      <span class="label">作者：</span><span class="link"><a href="/webB/ja/S00004/<%=uniqueCode %>"><%=nickName %></a></span>
    </div>
    
    <!-- メッセージ -->
    <div class="single_row_table">
      <table>
        <tr>
          <td class="label">メッセージ</td>
        </tr>
        <tr>
          <td class="value"><%=message %></td>
        </tr>
      </table>
    </div>

    <!-- 曲画像リンク -->
    <div class="song_link">
      <div class="cell">
        <div class="image_base">
          <a href="meloko://?song_id=<%=songId %>">
            <div class="image song1">
              <img class= "songimage" alt="<%=title %>" src="/webB/images/<%=imageFileName %>">
              <img alt= "play" class="play" src="/webB/images/play.png" >
            </div>
          </a>
        </div>
      </div>
    </div>

    <!-- 情報 -->
    <div class="single_row_table">
      <table>
        <tr>
          <td class="label">情報</td>
        </tr>
        <tr>
          <td class="value">
            <span class="label_top">総感動指数：</span>
            <span class="value"><%=ratingTotal %></span>
            <span class="label">平均感動指数：</span>
            <span class="value"><%=ratingAverage %></span>
            <span class="label">再生回数：</span>
            <span class="value"><%=totalListeningCount %></span>
            <span class="label">公開：</span>
            <span class="value"><%=releaseDatetime %></span>
            <span class="label">最終更新日：</span>
            <span class="value"><%=lastUpdateDatetime %></span>
            <span class="label">KEY：</span>
            <span class="value"><%=key %></span>
            <span class="label">楽譜表記：</span>
            <span class="value"><%=scoreType %></span>
            <span class="label">BPM：</span>
            <span class="value"><%=bpm %></span>
          </td>
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
          <td class="value">
            <a href="<%=otherLinkUrl %>"><%=otherLinkDescription %></a>
          </td>
        </tr>
      </table>
    </div>
    
    
    <!-- コメントヘッダー -->
    
	<% List<Map<String,String>> commentList = (List<Map<String,String>>)request.getAttribute("commentList");%>

	<% if(commentList.size()!=0){%>

    	<div class="sub_header">
      		<p>この曲についたコメント</p>
    	</div>
    	
	<%} %>



    <!-- コメントテーブル -->
    <% int i = 0; %>  <!-- for文で使うiを0で宣言 -->
    <% int length = 0; %>
	<% if( 100 < commentList.size()){ %>  <!-- コメントが100件以上あれば、for文で使うiを-100した数から始める 例）500件あれば400件目から100件表示 -->
	<% i = commentList.size() - 100; %>  <!-- コメント数から100を引いた数字をiに代入 -->
	<% length = i + 100; %>
	<% }else{ %>
	<% length = commentList.size(); %>
	<%} %>
	
	<% for(; i < length ; i++){ %>  <!-- 初期化式は省略/iが100足された数値に達するまで/一件ずつ -->
		<% Map<String,String> map = commentList.get(i); %>

		<% if(map.get("type").equals("1")){ %>  <!-- コメントタイプが「１」だった場合（リプライだった場合） -->
		<%	style ="style='padding-left:40px'"; %>
		<%}else{%>
		<%  style ="null"; %>
		<%} %>
	
		<div class="comments" <%=style %>>
		<% if(map.get("type").equals("1")){ %>
		<div class="reply">> </div>
		<%}%>
	      <ul>
	        <li>
	        <div class="normal">

	        <% if(!(nickName.equals(map.get("commentNickName")))){   //コメント投稿者が作曲家名と異なる場合%>
	        	        <div class="rating star<%=map.get("rating") %>"></div>
	    				<div class="composer_link"><a href="/webB/ja/S00004/<%=map.get("commentUniqueCode") %>"><%=map.get("commentNickName") %></a></div>
	    				<p class="comment"><%=map.get("comment") %></p>
	    				<p class="time"><%=map.get("writeDatetime") %></p>
	        <%}else{%>

	        	    	<div class="composer_link" style="padding-left:0;"><a href="/webB/ja/S00004/<%=map.get("commentUniqueCode") %>"><%=map.get("commentNickName") %></a></div>
	        	    	<p class="comment" id="composerComment"><%=map.get("comment") %></p>
	        	        <p class="time" id="composerTime"><%=map.get("writeDatetime") %></p>
			<%} %>
			

          	</div>
        	</li>
	  	</ul>
	 </div>
	<%} %>

 


    <!-- ページトップへjavaScript -->
    <div id="pagetop" hidden>
      <img alt="ページトップ" src="/webB/images/pagetop.png">
    </div>

    <!-- フッター -->
    <footer>
      Copyright <a href="https://www.excd.jp/">© EXCEED Co., Ltd.</a> All Rights Reserved.
    </footer>

  </div>
</body>
</html>