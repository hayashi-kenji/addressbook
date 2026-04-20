package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AddressBookDAO;
import model.Address;

/**
 * 新規住所登録機能を制御するサーブレットです。
 * 登録画面（register.jsp）の表示と、入力されたデータのデータベースへの登録処理を行います。
 * * @author ab220
 * @version 1.0
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * 登録画面を表示します。
	 * * @param request クライアントからのリクエスト情報
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット実行時に例外が発生した場合
	 * @throws IOException 入出力処理で例外が発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 新規登録入力画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * フォームから送信された住所情報を取得し、データベースに登録します。
	 * 登録成功時は完了画面へ、失敗時はエラーメッセージを保持して登録画面へリダイレクトします。
	 * * @param request 登録するデータを含むリクエスト情報
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット実行時に例外が発生した場合
	 * @throws IOException 入出力処理で例外が発生した場合
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// リクエストパラメータの文字化け防止
		request.setCharacterEncoding("UTF-8");

		// 1. フォームからの入力値を取得
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");

		// 2. 登録用モデルを作成し、DAOでデータベースへ登録実行
		Address address = new Address(name, phone, address1, address2, address3);
		AddressBookDAO dao = new AddressBookDAO();
		boolean retCode = dao.insert(address);

		// セッションの取得（完了画面での名前表示やエラーメッセージ保持に使用）
		HttpSession session = request.getSession();

		if (retCode) {
			// 登録成功：名前をセッションに保存して完了画面へ
			session.setAttribute("name", name);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerOK.jsp");
			dispatcher.forward(request, response);
		} else {
			// 登録失敗：エラーメッセージをセッションに保存し、登録画面へリダイレクト
			session.setAttribute("errMsg", name + "さんの登録ができませんでした。");
			response.sendRedirect("RegisterServlet");
		}
	}
}