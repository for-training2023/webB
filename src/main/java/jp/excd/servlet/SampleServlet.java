package jp.excd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet {
	public void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {
		//  404.jspにフォワーディングする。
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
		return;
	}



	public void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		Connection con = null;
		request.setCharacterEncoding("UTF-8");

		String dbName = "meloko";
		String userName = "meloko";
		String password = "exceed";
		String timeZone = "Asia/Tokyo";

		try {
			// (1)DB接続（コネクションの確立）
			con = MySQLSetting.getConnection(dbName, userName, password, timeZone);

			// (2)内部メソッド呼び出し
			this.mainProcess(request, response, con);

		} catch (Exception e) {
			e.printStackTrace();
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/500.jsp").forward(request, response);

		} finally {
			try {
				if (con != null) {

					// (3)接続したコネクションの切断を行う。
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/500.jsp").forward(request, response);
				return;

			}

		}


	}


	private void mainProcess(
			HttpServletRequest request,
			HttpServletResponse response,
			Connection con) throws Exception{

		String sqlDescription = "select count(*) as count from mst_description ";
		PreparedStatement pstmtDescription = con.prepareStatement(sqlDescription);

		ResultSet rsDescription = pstmtDescription.executeQuery();
		if(rsDescription.next()) { 
			Integer count = Integer.valueOf(rsDescription.getInt("count"));
			request.setAttribute("count", count.toString());
		}

		rsDescription.close();
		pstmtDescription.close();


		//フォワード先の指定
		RequestDispatcher dispatcher =  request.getRequestDispatcher("/jsp/Sample.jsp");

		//フォワードの実行
		dispatcher.forward(request, response);

	}
}
