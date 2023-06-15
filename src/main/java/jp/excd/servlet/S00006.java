package jp.excd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class S00006 extends HttpServlet {

	public void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		// （1）404.jspにフォワーディングする。
		System.out.println("aaaaaaaaaaaaaa");
		getServletConfig().getServletContext().getRequestDispatcher("/ja/404.jsp").forward(request, response);
	}

	public void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {

		//--------------------------------------------
		//  (1)接続URLのチェック
		//--------------------------------------------
		String URI = request.getRequestURI();

		if (("/webB/ja/S00006/back".equals(URI) || "/webB/ja/S00006/change".equals(URI)) == false) {
			getServletConfig().getServletContext().getRequestDispatcher("/ja/404.jsp").forward(request, response);
		}

		//--------------------------------------------
		//  (2) POSTパラメタで以下の値を受け取る
		//--------------------------------------------
		request.setCharacterEncoding("UTF-8");
		String release_date_Radio = request.getParameter("release_date_is_radio");
		String release_date_from = request.getParameter("release_date_is_from");
		String release_date_to = request.getParameter("release_date_is_to");
		String rating_Radio = request.getParameter("rating_radio");
		String rating_from = request.getParameter("rating_from");
		String rating_To = request.getParameter("rating_to");
		String rating_average_radio = request.getParameter("rating_average_radio");
		String rating_average_from = request.getParameter("rating_average_from");
		String rating_average_to = request.getParameter("rating_average_to");
		String views_radio = request.getParameter("views_radio");
		String views_from = request.getParameter("views_from");
		String views_to = request.getParameter("views_to");
		String title_radio = request.getParameter("title_radio");
		String title_type_radio = request.getParameter("title_type_radio");
		String title = request.getParameter("title");
		String sort_order = request.getParameter("sort_order");

		//--------------------------------------------
		//  (3) 入力項目(POSTパラメタ)を使って、Requestオブジェクトのアトリビュートの初期化をする。
		//--------------------------------------------
		request.setAttribute("release_date_is_radio", release_date_Radio);
		request.setAttribute("release_date_is_from", release_date_from);
		request.setAttribute("release_date_is_to", release_date_to);
		request.setAttribute("rating_radio", rating_Radio);
		request.setAttribute("rating_from", rating_from);
		request.setAttribute("rating_to", rating_To);
		request.setAttribute("rating_average_radio", rating_average_radio);
		request.setAttribute("rating_average_from", rating_average_from);
		request.setAttribute("rating_average_to", rating_average_to);
		request.setAttribute("views_radio", views_radio);
		request.setAttribute("views_from", views_from);
		request.setAttribute("views_to", views_to);
		request.setAttribute("title_radio", title_radio);
		request.setAttribute("title_type_radio", title_type_radio);
		request.setAttribute("title", title);
		request.setAttribute("sort_order", sort_order);
		
		// (4) S00005.jspにフォワーディングする。
		getServletConfig().getServletContext().getRequestDispatcher("/ja/S00005.jsp").forward(request, response);
		
	}

}