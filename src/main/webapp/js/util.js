//-----------------------------------------------------------------------------
// ユーティリティーオブジェクト
//-----------------------------------------------------------------------------
var Util = {
  changeTabelStatus: function(_base$) {
    
    // 自分を含むテーブル
    var _table$ = _base$.parents('table.radio_base');
    
    // 自分を含むdiv
    var _div$ = _table$.parents('div.input_table');
    
    // ラジオボタンの配列
    var _radios$ = _table$.find("input[type=radio].onOffRadio");
    
    _radios$.each(function(index) {
        var _this$ = $(this);

        // チェックされているかどうか？
        var isChecked = _this$.is(":checked");
        
        if (_this$.val() === "1") {
          //----------------------------------------------------------------------
          // ONのラジオ
          //----------------------------------------------------------------------
          if (isChecked) {
            // チェックされている。
            if (_table$.hasClass("error")) {
              // エラー状態の場合は何もしない。
            } else {
              // エラー状態でない場合は、必須色に変更する。
              _div$.addClass("required");
            }
          }
        } else {
          //----------------------------------------------------------------------
          // OFFのラジオ
          //----------------------------------------------------------------------
          if (isChecked) {
            _div$.removeClass("required");
          }
        }
    });
  },
  //------------------------------------------------------------------------------
  // キャンセルレイヤの追加
  //------------------------------------------------------------------------------
  addCancelLayer: function() {
    console.log("addCancelLayer");
    
    // bodyの取得
    var _body$ = $('body');

    // 高さの取得
    var _bodyHeigth = _body$.height();

    // Layerの生成
    var _layer$ = $('<div id="layer"></div>');

    // 高さの設定
    _layer$.height(_bodyHeigth);

    // 消しておく。
    _layer$.css("display", "none");

    // DOMに追加
    $("#layer_marker").append(_layer$);
    
    // ゆっくり表示
    _layer$.fadeIn(150);
  },
  //------------------------------------------------------------------------------
  // メニューの追加
  //------------------------------------------------------------------------------
  addMenu: function() {
    console.log("addMenu");
    
    // Layerの生成
    var _layer$ = $(''
      + '<div class="menu_base">'
      + '  <div class="menu_header">'
      + '    <p>メニュー</p>'
      + '    <a id="menu_close" href="#">×CLOSE</a>'
      + '  </div>'
      + '  <ul>'
      + '    <li>'
      + '      <a href="S00001.html">'
      + '        <p>HOME</p>'
      + '        <img alt="HOME" class="right_arrow" src="../images/right_arrow.png" />'
      + '      </a>'
      + '    </li>'
      + '    <li>'
      + '      <a href="S00005.html">'
      + '        <p>作品検索</p>'
      + '        <img alt="作品検索" class="right_arrow" src="../images/right_arrow.png" />'
      + '      </a>'
      + '    </li>'
      + '    <li>'
      + '      <a href="S00007.html">'
      + '        <p>作曲家検索</p>'
      + '        <img alt="作曲家検索" class="right_arrow" src="../images/right_arrow.png" />'
      + '      </a>'
      + '    </li>'
      + '    <li>'
      + '      <a href="S00009.html">'
      + '        <p>専用アプリダウンロード</p>'
      + '        <img alt="専用アプリダウンロード" class="right_arrow" src="../images/right_arrow.png" />'
      + '      </a>'
      + '    </li>'
      + '    <li>'
      + '      <a href="https://excd.jp/">'
      + '        <p class="to_excd">運営会社</p>'
      + '        <img alt="運営会社" class="pop_up" src="../images/return.png" />'
      + '      </a>'
      + '    </li>'
      + '  </ul>'
      + '</div>'
     );

    // 消しておく。
    _layer$.css("display", "none");

    // DOMに追加
    $("#menu_marker").append(_layer$);
    
    // ゆっくり表示
    _layer$.fadeIn(300);
  },
  //-----------------------------------------------------------------------------
  // メニュー用のイベントハンドラーの追加
  //-----------------------------------------------------------------------------
  addMenuEventHandller :function() {
      console.log("addMenuEventHandller");
      
      // クローズボタン
      $("#menu_close, #layer").on("click", function() {
          console.log("close");
          
          // メニューを非表示
          $(".menu_base").fadeOut(150, function() {
              
              // メニューをDOMから削除
              $(".menu_base").remove();
          });

          // レイヤを非表示
          $("#layer").fadeOut(300, function() {
              
              // レイヤをDOMから削除
              $("#layer").remove();
          });

      });
  }
}
//-----------------------------------------------------------------------------
// ページトップへボタン
//-----------------------------------------------------------------------------
$(function() {
  var topBtn=$('#pagetop');
  topBtn.hide();
 
  $(window).scroll(function(){
    if($(this).scrollTop()>5){
      topBtn.fadeIn();
    }else{
     
      topBtn.fadeOut();
    } 
  });
  topBtn.click(function(){
    $('body,html').animate({
    scrollTop: 0},500);
    return false;
  });
});
//-----------------------------------------------------------------------------
// メニュー関連
//-----------------------------------------------------------------------------
$(function() {
    $("a#menu_open").on("click", function() {
        console.log("menu_open");
        
        // キャンセルレイアの追加
        Util.addCancelLayer();
        
        // メニューの追加
        Util.addMenu();
        
        // イベントハンドラの追加
        Util.addMenuEventHandller();
    });
});

