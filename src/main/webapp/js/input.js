//--------------------------------------------------------------------------
// 入力チェック関連
//--------------------------------------------------------------------------
$(function(){
    //--------------------------------------------------------------------------
    // ラジオラベルが押下された場合の処理
    //--------------------------------------------------------------------------
    $("span.radio_label").on("click", function() {
        
        // 該当ラベルのjQueryオブジェクト化
        var _this$ = $(this);
        
        // 兄弟の取得
        var _radioOrSelect$ =  _this$.prev();
        
        // ラジオのチェック
        _radioOrSelect$.prop('checked', true);
        
        if (_radioOrSelect$.hasClass("onOffRadio")) {
          // テーブルの色替え
          Util.changeTabelStatus(_radioOrSelect$);
        } else {
        }
    });
    //--------------------------------------------------------------------------
    // チェックボックスラベルが押下された場合の処理
    //--------------------------------------------------------------------------
    $("span.check_label").on("click", function() {
        
        // 該当ラベルのjQueryオブジェクト化
        var _this$ = $(this);
        
        // 兄弟の取得
        var _radioOrSelect$ =  _this$.prev();
        
        var isChecked = _radioOrSelect$.prop("checked");
        if (isChecked) {
          _radioOrSelect$.prop('checked', false);
        } else {
          _radioOrSelect$.prop('checked', true);
        }
    });
    //--------------------------------------------------------------------------
    // ラジオが変更された場合の処理
    //--------------------------------------------------------------------------
    $("input[type=radio].onOffRadio").change(function() {
        var _this$ = $(this);
        
        // テーブルの色替え
        Util.changeTabelStatus(_this$);
        
    });
});
