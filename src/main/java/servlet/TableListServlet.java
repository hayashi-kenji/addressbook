package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AddressBookDAO;
import dto.AddData;

/**
 * 住所録の一覧表示機能を制御するサーブレットです。
 * データベースから全ての住所情報を取得し、一覧表示画面（tableList.jsp）に引き渡します。
 * * @author ab220
 * @version 1.0
 */
@WebServlet("/TableListServlet")
public class TableListServlet extends HttpServlet {
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * GETリクエストを処理し、住所録の一覧を表示します。
	 * データベースから取得したリストをリクエストスコープに格納し、JSPへフォワードします。
	 * * @param request クライアントからのリクエスト情報
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット実行時に例外が発生した場合
	 * @throws IOException 入出力処理で例外が発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 1. DAOをインスタンス化
		AddressBookDAO dao = new AddressBookDAO();
		
		// 2. データベースから全件取得（内部で都道府県テーブルと結合済み）
		List<AddData> list = dao.findAll(); 

		// 3. 取得したリストをリクエストスコープにセット
		// JSP側で request.getAttribute("list") として取り出せるようにします
		request.setAttribute("list", list);
		
		// 4. 一覧表示画面（tableList.jsp）へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tableList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * POSTリクエストを処理します。（現在は使用していません）
	 * * @param request クライアントからのリクエスト情報
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット実行時に例外が発生した場合
	 * @throws IOException 入出力処理で例外が発生した場合
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 必要に応じて実装
	}

}