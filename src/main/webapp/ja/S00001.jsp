<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%System.out.println("JSP"); %>
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
<title>音楽室</title>
<link rel="stylesheet" href="../css/main.css">
<script src="../js/jquery-3.3.0.min.js" type="text/javascript"></script>
<script src="../js/util.js" type="text/javascript"></script>
<!-- 画像の圧縮表示設定 -->
<style type="text/css">
 .sortselected{
   pointer-events:none;
 }
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
<script type="text/javascript">

	/**
	 * 画面ロード時にURLパラメータを自動正しい形に修正する。
	 *	数値以外の値が入った場合：1
	 *	数値が5より大きい場合：1
	 *	数値がnullの場合：1
	 *	数値がundefinedの場合：1
	 *	数値が全角の1～4の場合：全角数値を半角数値に修正したもの
	 *
	 *  ※不具合：[, ], \, |, {, }, ` が入力された場合には400エラーが作動してしまいます。 
	 */
window.onload = function loadFinished(){
	
	var mes = "";	 
	//URLを取得
	let url = new URL(location.href);
	const enc = encodeURI(url);
	
	// URLSearchParamsオブジェクトを取得
	let params = url.searchParams;
	var category = params.get('category');

	if(!category){
		category = null;
	}

	// 値がnullの場合は「1」を設定する。
	if(category == null){
		category = '１';
	}

	// 全角を半角に修正する。
	var categoryNum =  replaceCategory(category);
	
	// 値が数値でない場合は「1」を設定する。
	if(isNaN(categoryNum)){
		category = 1;
		categoryNum = 1;
	}
	
	// 値がundefinedの場合は「1」を設定する。
	if(category === void 0){
		category = 1;
		categoryNum = 1;
	}
	
	// 値が5より大きい場合は「1」を設定する。
	if(category > 5){
		category = 1;
		categoryNum = 1;
	}
	
	
	// URLSearchParamsオブジェクトを取得
	var from = params.get('from');

	if(!from){
		from = null;
	}

	// 値がnullの場合は「6」を設定する。
	if(from == null){
		from = "６";
	}

	var fromNam = replaceFrom(from);

	// 値が数値でない場合は「6」を設定する。
	if(isNaN(fromNam)){
		fromNam = 6;
	}
	
	// 値がundefinedの場合は「6」を設定する。
	if( from === void 0){
		fromNam = 6;
	}

	// 変更するURLを生成する。
	var cool = "http://localhost:8080/webB/ja/S00001?category=" + categoryNum + "&from=" + fromNam;

	// URLを表示する。
	history.pushState('','', cool);

}
	 /**
	 * URLからURLパラメータ「category」の値を全角から半角に修正する。
	 *	
	 * return URLパラメータ「category」の値
	 */
	function replaceCategory(str){
		return str.replace(/[１-４]/g, function(s){
			return String.fromCharCode(s.charCodeAt(0) - 0xFEE0);
		});
	}
	 /**
	 * URLからURLパラメータ「from」の値を全角から半角に修正する。
	 *	
	 * return URLパラメータ「from」の値
	 */
	function replaceFrom(str){
		return str.replace(/[０-９]/g, function(s){
			return String.fromCharCode(s.charCodeAt(0) - 0xFEE0);
		});
	}
	/**
	 * URLからURLパラメータ「from」を取り出し値を返却する。
	 *	URLパラメータ「from」が見つからない場合はnullを返却する。
	 *
	 * return URLパラメータ「from」の値
	 */
	function serchFrom() {
		if (1 < document.location.search.length) {
			//URLを取得
			let url = new URL(location.href);

			// URLSearchParamsオブジェクトを取得
			let params = url.searchParams;
			var result = params.get('from');
			return result;
		}
		return null;
	}

	/**
	 * URLからURLパラメータ「category」を取り出し値を返却する。
	 *	URLパラメータ「category」が見つからない場合はnullを返却する。
	 *
	 * return URLパラメータ「category」の値
	 */
	function serchCategory() {
		if (1 < document.location.search.length) {
			//URLを取得
			let url = new URL(location.href);

			// URLSearchParamsオブジェクトを取得
			let params = url.searchParams;
			var result = params.get('category');
		if(isNaN(result)){
			result = 1;
		}
			return result;
		}
		return null;
	}

	/**
	 * URLパラメータ「from」に値を代入する。
	 *	値がnullの場合は初期値「6」を代入し返却する。
	 *
	 * return bool	serchFrom()の結果
	 */
	function from() {
		let bool = serchFrom();
		if (bool === null) {
			var from = 6;
			return 6;
		}
		return bool;

	}

	/**
	 * URLパラメータ「category」に値を代入する。
	 *	値がnullの場合は初期値「1」を代入し返却する。
	 *
	 * return bool	serchCategory()の結果
	 */
	function category() {
		let bool = serchCategory();
		if (bool === null) {
			var category = 1;
			return 1;
		}
		return bool;

	}

	/**
	 *	カテゴリ選択を押下したときに呼び出されます。
	 *		URLパラメータ「from」と「category」それぞれに値を代入しGETで遷移する。
	 *
	 * @param name	押下したソートそれぞれに設定されている値
	 */
	function sort1(name) {

		// from()を呼び出し代入する。
		from = from();

		// category()を呼び出し代入する。
		category = category();

		// 押下したソートの項目をcategoryに代入する。
		category = name;

		// fromの値が0以下、nullの時はfromの値に初期値「6」を代入する。
		if (from <= 0 || from == null || from == "") {
			from = 6;
		}

		// サーブレットに値を送信する。
		var request = {
			category : name,
			from : from
		};
		window.location.herf= "/webB/ja/S00001?category=" + category + "&from=" + from 
		// 押下したaタグのhrefに遷移先のURLを代入し、画面遷移する。
		var target = document.getElementById(name)
		target.href = "/webB/ja/S00001?category=" + category + "&from=" + from

	}

	/**
	 * 追加読み込みを押下したときに呼び出されます。
	 *	fromの値に+5し、fromをURLパラメータに代入しGET遷移する。
	 *
	 * @param add	5が格納される。
	 */
	function add(add) {

		// from()を呼び出し代入する。
		from = from();

		// category()を呼び出し代入する。
		category = category();

		// fromの値が0以下、nullの時はfromの値に1を代入する。
		if (from <= 0 || from == null || from == "") {
			from = 1;
		}

		// fromとaddを加算するために型を変換する。
		var num1 = Number(from);
		var num2 = Number(add);

		// fromとaddを加算する。
		var result = num1 + num2;

		// サーブレットに値を送信する。
		var request = {
			param1 : from,
			param2 : add
		};

		// 押下したaタグのhrefに遷移先のURLを代入し、画面遷移する。
		var target = document.getElementById('add');
		target.href = "/webB/ja/S00001?category=" + category + "&from=" + result
	}

	/**
	 * 表示件数が最大まで達したときに表示される、
	 *	「1～6に戻る」を押下したときに呼び出されます。
	 *	fromの値に6を代入し、fromをURLパラメータに代入しGET遷移する。
	 *
	 * @param add	6が格納される。
	 */
	function back(back) {

		// from()を呼び出し代入する。
		from = from();

		// category()を呼び出し代入する。
		category = category();

		// fromの値が0以下、nullの時はfromの値に1を代入する。
		if (from <= 0 || from == null || from == "") {
			from = 1;
		}

		// 数値に変換する。
		var num1 = Number(back);

		// resultに代入する。
		result = num1;

		// サーブレットに値を送信する。
		var request = {
			param1 : from,
			param2 : back
		};

		// 押下したaタグのhrefに遷移先のURLを代入し、画面遷移する。
		var target = document.getElementById('back')
		target.href = "/webB/ja/S00001?category=" + category + "&from=" + result
		}
</script>
</head>
<body>
	<!-- メニューのキャンセルレイヤの起点 -->
	<div id="layer_marker"></div>

	<div class="wrapper">

		<!-- トップバナー -->
		<div class="top_banner">
			<a href="https://itunes.apple.com/jp/app/id1440134774?mt=8"> 
				<img alt="メロコ～iPhone用作曲アプリアイコン" src="../images/melokoIcon.png" class="icon">
				<p>作曲アプリ「メロコ」。歌モノに特化したアプリです。このサイトの曲はすべてこのアプリで作成されています。</p>
				 <img alt="メロコ～専用アプリダウンロード画面へのリンク" src="../images/right_blue_arrow.png" class="to_download_page_arrow">
			</a>
		</div>

		<!-- タイトルバー -->
		<div class="title_bar">
			<p class="main_title">音楽室</p>
			<p class="sub_title">～作曲家たちのコミュニティー～</p>
			<a href="#" id="menu_open"> <img alt="メニュー" src="../images/menu.png" class="menu-icon"></a>
		</div>

		<!-- メニューの起点 -->
		<div id="menu_marker"></div>

		<!-- トップタブ -->
		<div class="top_tab">
			<%
			// カテゴリ選択で選択した箇所に色をつけ、他の個所の色を消す。
			String tab1 = "selected";
			String tab2 = " ";
			String tab3 = " ";
			String tab4 = " ";
			String tab5 = " ";
			String category = (String) request.getAttribute("Category");
			if (category != null) {
				if (category.equals("1")) {
					tab1 = "selected";
					tab2 = " ";
					tab3 = " ";
					tab4 = " ";
					tab5 = " ";
				}
				if (category.equals("2")) {
					tab1 = " ";
					tab2 = "selected";
					tab3 = " ";
					tab4 = " ";
					tab5 = " ";
				}
				if (category.equals("3")) {
					tab1 = " ";
					tab2 = " ";
					tab3 = "selected";
					tab4 = " ";
					tab5 = " ";
				}
				if (category.equals("4")) {
					tab1 = " ";
					tab2 = " ";
					tab3 = " ";
					tab4 = "selected";
					tab5 = " ";
				}
				if (category.equals("5")) {
					tab1 = " ";
					tab2 = " ";
					tab3 = " ";
					tab4 = " ";
					tab5 = "selected";
				}
			}else{
				category = "1";
				tab1 = "selected";
				tab2 = " ";
				tab3 = " ";
				tab4 = " ";
				tab5 = " ";
			}
			%>
			<ul>
				<li class="tab1 <%=tab1%>">
					<a class="sort<%=tab1%>" href=""  id="1" onclick="sort1(1)">新着</a>
				</li>
				<li class="tab2 <%=tab2%>" id="tab2">
					<a class="sort<%=tab2%>" href="" data-value="2" id="2" onclick="sort1(2)">人気</a>
				</li>
				<li class="tab3 <%=tab3%>">
					<a class="sort<%=tab3%>" href="" data-value="3" id="3" onclick="sort1(3)">高評価</a>
				</li>
				<li class="tab4 <%=tab4%>">
					<a class="sort<%=tab4%>" href="" data-value="4" id="4" onclick="sort1(4)">名作</a>
				</li>
				
			<!-- あとでけす -->
			<%if(category.equals("5")){ %>
				<li class="tab5 <%=tab5%>">
				<a class="sort<%=tab5%>" href="" data-value="5" id="5" onclick="sort1(5)">全件表示</a></li> 
			<%} %>
			</ul>
		</div>

		<!-- トップ告知 -->
		<%
		// データを格納した値を受け取る。
		List<Map<String, String>> listMap = (List<Map<String, String>>) request.getAttribute("ListMap");
		Map<String, String> map = new HashMap<String, String>();
		String listsize = (String) request.getAttribute("ListSize");

		// 最初に呼び出したとき等、リストがない場合にS00001のサーブレットに遷移しリストを持ってくる。
		if (listsize == null) {
			// S00001に遷移する。
			getServletConfig().getServletContext().getRequestDispatcher("/ja/S00001").forward(request, response);

			// 値を改めて取得する。
			listMap = (List<Map<String, String>>) request.getAttribute("ListMap");
		}

		// 表示するレコード値の最小値と最大値を取得する。
		String min = (String) request.getAttribute("OutPutMin");
		String max = (String) request.getAttribute("OutPutMax");

		// レコード値の初期値を設定する。
		int outPutMin = 0;
		int outPutMax = 5;

		// 最小値がある場合は初期値に代入する。
		if (min == null) {
		} else {
			outPutMin = Integer.parseInt(min);
		}

		// 最大値がある場合は初期値に代入する。
		if (max == null) {
		} else {
			outPutMax = Integer.parseInt(max);
		}

		// レコードの最大値がリストの数より大きい場合はリストの数字をレコードの最大値に代入する。
		if (listMap.size() < outPutMax) {
			outPutMax = listMap.size();
		}

		// 告知を取得する。
		String notice = (String) request.getAttribute("Notice");

		// 告知がない場合にnullを設定する。
		if ("".equals(notice)) {
			notice = null;
		}

		// 告知がnullの場合に、告知の枠を表示しないようにする。
		if (notice != null) {
		%>
		<div class="top_notice">
			<p class="top_notice_title">告知</p>
			<p class="top_notice_body">
				<%=notice%>
			</p>
		</div>
		<%
		}
		%>
		<!-- ソングテーブル -->
		<div class="song_list">
			<ul>
			<%= listMap.size()%>件表示できます。<br>
				<%
				/* リストの数だけループして表示する。
				*	ループの初期値に最小値を代入する。
				*	ループの終了条件は、最小値を1ずつ加算し最大値と等しくなったら終了する。
				*/
				for (int i = outPutMin; i < outPutMax; i++) {
					
					map = listMap.get(i);
				%><%=i+1 %>件目
				<li>
					<div class="cell">
						<div class="song_title"><%=map.get("Title")%></div>
						<div class="composer">
							<span class="label_top">作曲：</span> 
							<span class="composer_link">
								<!-- S00004.jspに遷移させる所。js「song(uniqucode)」に飛ばしてS00004.javaに値を送る -->
								<%
								// UniqueCodeがnullのときは404.jspに遷移する。
								if (map.get("UniqueCode") != null) {
								%> <a class="comp" id="comp"
								href='/webB/ja/S00004/<%=map.get("UniqueCode")%>'><%=map.get("NickName")%></a>
								<%
								} else {
								%> <a class="comp" id="comp" href="/webB/ja/404.jsp"><%=map.get("NickName")%></a>
								<%
								}
								%>
							</span>
						</div>
						<div class="image_base">
							<!-- S00003.jspに遷移させる所。js「song(id)」に飛ばしてS00003.javaに値を送る -->
							<a class="song" id="song" href='/webB/ja/S00003/<%=map.get("Id")%>' id='<%map.get("Id");%>' )>
								<div class="image">
									<img alt='<%=map.get("ImageFileName")%>' src='../images/<%=map.get("ImageFileName")%>'> 
									<img alt="play" class="play" src="/webB/images/play.png">
								</div>
							</a>※IDは、<%=map.get("Id")%>です。
						</div>
						<div class="detail">
							<span class="label_top">総感動指数：</span> <span class="value"><%=map.get("RatingTotal")%></span>
							<span class="label">平均感動指数：</span> <span class="value"><%=map.get("RatingAverage")%></span>
							<span class="label">再生回数：</span> <span class="value"><%=map.get("TotalListenCount")%></span>
							<span class="label">公開：</span> <span class="value"><%=map.get("ReleaseDatetime")%></span>
						</div>
					</div>
				</li>
				<%
				}
				%>
			</ul>
		</div>

		<!-- メインボタン -->
		<%
		// リストの最大件数が表示されている場合に、追加読み込みボタンを表示させない。
		if (listMap.size() > outPutMax) {
		%>
		<div class="main_button">
			<a class="add" href="" data-value="5" id="add" onclick="add(5)">さらに読み込む</a>
		</div>
		<%
		} else {
		// 表示できる件数がない場合に、1～6の画面に戻るメッセージを表示する。
		%>
		これ以上、表示できるデータがありませんm(_ _)m<br>
		 <a class="add" href="" data-value="5" id="back" onclick="back(6)">→1件目から、5件目に戻ります。</a>

		<%
		}
		%>
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