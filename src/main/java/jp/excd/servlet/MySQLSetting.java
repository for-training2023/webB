package jp.excd.servlet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class MySQLSetting extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 実行環境がAWSかどうかを表すフラグ
	public static boolean isAWS = false;

	// AWS環境のIPドレスの範囲
	// IPアドレスがこの範囲の場合、AWSに配置されていると判断する。
	public static String AWS_IP_BLOCK = "10.1.11.";

	// AWSのMySQLのホスト名
	public static String AWS_RDS_HOST = "mysql-for-training.cywu4q57ijlf.ap-northeast-1.rds.amazonaws.com";

	// AWSのユーザネーム
	public static String AWS_RDS_USER_NAME = "root";

	// AWSのパスワード
	public static String AWS_RDS_PASSWORD = "pass-for-training";

	//-----------------------------------------------------------------
	// コンストラクタ
	//-----------------------------------------------------------------
	public MySQLSetting() {
		// 独自の処理なし
		super();
	}
	//-----------------------------------------------------------------
	// web.xmlの
	// load-on-startup で呼び出される初期化処理
	//-----------------------------------------------------------------
	public void init(ServletConfig config) throws ServletException {

		// 動作ログ
		System.out.println("MySQLSettingInit.init()");


		// IPアドレス管理クラス
		InetAddress addr = null;

		try {
			// 動作サーバのIPアドレスを取得
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// エラー発生。
			e.printStackTrace();
		}
		// IPアドレス
		String ip = "";
		String ipBLock = "";
		if (addr != null) {
			// IPアドレスを文字列で取得
			ip = addr.getHostAddress();
		}
		if (ip.length() >= 8) {
			// 先頭から8文字を取得
			ipBLock = ip.substring(0, 8);
		}
		// チェック
		if (AWS_IP_BLOCK.equals(ipBLock)) {
			MySQLSetting.isAWS = true;
		}
	}
	//-----------------------------------------------------------------
	// DBコネクション取得
	//-----------------------------------------------------------------
	public static Connection getConnection(String dbName,
	                                        String userName,
	                                        String password,
	                                        String timeZone) throws SQLException {

		// ホストの設定
		String hostName = "192.168.1.68";

		// ユーザ名
		String connectUserName = userName;

		// パスワード
		String connectPassword = password;

		if (isAWS) {
			// AWSで実行されている場合は、ホスト名、ユーザ名、パスワードを、RDSに適した内容に切り替える。
			hostName = AWS_RDS_HOST;
			connectUserName = AWS_RDS_USER_NAME;
			connectPassword = AWS_RDS_PASSWORD;
		}

		// コネクション用のSQL
		final String URL = "jdbc:mysql://"
                + hostName
                + ":3306/"
                + dbName
                + "?serverTimezone="
                + timeZone
                + "&allowPublicKeyRetrieval=true"
                + "&useSSL=false";

		// コネクション接続
		Connection conn = DriverManager.getConnection(URL, connectUserName, connectPassword);

		return conn;
	}
}
