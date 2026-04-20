package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 住所録システムのメインメニュー画面を制御するサーブレットです。
 * ユーザーからのリクエストを受け取り、メニュー画面（addressBook.jsp）への遷移を管理します。
 * * @author ab220
 * @version 1.0
 */
@WebServlet("/AddressBookServlet")
public class AddressBookServlet extends HttpServlet {
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * GETリクエストを処理し、メニュー画面を表示します。
	 * * @param request クライアントがサーブレットへ送ったリクエスト内容を含む {@link HttpServletRequest} オブジェクト
	 * @param response サーブレットがクライアントに返すレスポンス内容を含む {@link HttpServletResponse} オブジェクト
	 * @throws ServletException サーブレットが GET リクエストを処理している間に例外が発生した場合
	 * @throws IOException サーブレットがリクエストを処理している間に入出力例外が発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// addressBook.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addressBook.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * POSTリクエストを処理します。（現在は未実装です）
	 * * @param request クライアントがサーブレットへ送ったリクエスト内容を含む {@link HttpServletRequest} オブジェクト
	 * @param response サーブレットがクライアントに返すレスポンス内容を含む {@link HttpServletResponse} オブジェクト
	 * @throws ServletException サーブレットが POST リクエストを処理している間に例外が発生した場合
	 * @throws IOException サーブレットがリクエストを処理している間に入出力例外が発生した場合
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 必要に応じて実装
	}

}