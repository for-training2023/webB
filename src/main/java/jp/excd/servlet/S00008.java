package jp.excd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class S00008 extends HttpServlet{
	

	public void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		// （1）404.jspにフォワーディングする。
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
	}

	public void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {

		//--------------------------------------------
		//  (1)接続URLのチェック
		//--------------------------------------------
		String URI = request.getRequestURI();

		if (("/webB/ja/S00008/back".equals(URI) || "/webB/ja/S00008/change".equals(URI)) == false) {
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/404.jsp").forward(request, response);
		}

		//--------------------------------------------
		//  (2) POSTパラメタで以下の値を受け取る
		//--------------------------------------------
		request.setCharacterEncoding("UTF-8");
		String nickname_radio = request.getParameter("nickname_radio");
		String nickname_type_radio = request.getParameter("nickname_type_radio");
		String nickname = request.getParameter("nickname");
		String joined_date_radio = request.getParameter("joined_date_radio");
		String joined_date_from = request.getParameter("joined_date_from");
		String joined_date_to = request.getParameter("joined_date_to");
		String gender_radio = request.getParameter("gender_radio");
		String gender = request.getParameter("gender");
		String birthday_radio = request.getParameter("birthday_radio");
		String birthday_from = request.getParameter("birthday_from");
		String birthday_to = request.getParameter("birthday_to");
		String listener_count_radio = request.getParameter("listener_count_radio");
		String listener_count_from= request.getParameter("listener_count_from");
		String listener_count_to = request.getParameter("listener_count_to");
		String language_type_jp = request.getParameter("language_type_jp");
		String language_type_en = request.getParameter("language_type_en");
		String sort_order = request.getParameter("sort_order");


		//--------------------------------------------
		//  (3) 入力項目(POSTパラメタ)を使って、Requestオブジェクトのアトリビュートの初期化をする。
		//--------------------------------------------
		request.setAttribute("nickname_radio", nickname_radio);
		request.setAttribute("nickname_type_radio", nickname_type_radio);
		request.setAttribute("nickname", nickname);
		request.setAttribute("joined_date_radio", joined_date_radio);
		request.setAttribute("joined_date_from", joined_date_from);
		request.setAttribute("joined_date_to", joined_date_to);
		request.setAttribute("gender_radio", gender_radio);
		request.setAttribute("gender", gender);
		request.setAttribute("birthday_radio", birthday_radio);
		request.setAttribute("birthday_from", birthday_from);
		request.setAttribute("birthday_to", birthday_to);
		request.setAttribute("listener_count_radio", listener_count_radio);
		request.setAttribute("listener_count_from", listener_count_from);
		request.setAttribute("listener_count_to", listener_count_to);
		request.setAttribute("language_type_jp", language_type_jp);
		request.setAttribute("language_type_en", language_type_en);
		request.setAttribute("sort_order", sort_order);
		
		// (4) S00007.jspにフォワーディングする。
		String nextPath = "/ja/S00007.jsp";
		getServletConfig().getServletContext().getRequestDispatcher(nextPath).forward(request, response);
	}


}
