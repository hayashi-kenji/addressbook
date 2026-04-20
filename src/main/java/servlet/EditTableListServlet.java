package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AddressBookDAO;
import dto.AddData;

/**
 * 編集・削除用の一覧表示を制御するサーブレットです。
 * データの参照に先立ち、管理者パスワードによる認証を行います。
 * 認証成功時のみ、データベースから全件データを取得し、編集用一覧画面を表示します。
 * * @author ab220
 * @version 1.0
 */
@WebServlet("/EditTableListServlet")
public class EditTableListServlet extends HttpServlet {
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * 管理者認証画面（パスワード入力画面）を表示します。
	 * * @param request クライアントからのリクエスト情報
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット実行時に例外が発生した場合
	 * @throws IOException 入出力処理で例外が発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 管理者パスワード入力画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminAuth.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 入力されたパスワードを検証し、認証に成功した場合は住所録一覧を取得して表示します。
	 * 認証に失敗した場合は、再度認証画面を表示します。
	 * * @param request パスワード情報を含むリクエスト情報
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット実行時に例外が発生した場合
	 * @throws IOException 入出力処理で例外が発生した場合
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. フォームから送信されたパスワードを取得
		String adminPass = request.getParameter("adminpass");

		// 2. 設定ファイル（properties）から期待されるパスワードを読み込む
		String expectedPass = loadPasswordFromProperties();

		// 3. パスワードの検証
		if (adminPass != null && adminPass.equals(expectedPass)) {
			// 成功時の処理
			AddressBookDAO dao = new AddressBookDAO();
			List<AddData> list = dao.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editTableList.jsp");
			dispatcher.forward(request, response);
		} else {
			// 失敗時の処理
			// エラーメッセージをリクエストスコープにセット
			request.setAttribute("errMsg", "パスワードが正しくありません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminAuth.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * config.propertiesからパスワードを読み込む補助メソッド
	 */
	private String loadPasswordFromProperties() {
		Properties props = new Properties();
		// getResourceAsStream を使うとクラスパス上のファイルを読み込める
		try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			if (is != null) {
				props.load(is);
				return props.getProperty("admin.password");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; // 読み込めなかった場合
	}
}