package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AddressBookDAO;
import dto.AddData;

/**
 * 住所情報の編集および更新処理を制御するサーブレットです。
 * GETリクエストで編集対象のデータを取得して入力画面を表示し、
 * POSTリクエストで画面から送られた内容をデータベースに反映します。
 * * @author ab220
 * @version 1.0
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * 編集画面を表示します。
	 * リクエストパラメータから受け取ったIDを基に最新のデータを取得し、編集フォームへ引き継ぎます。
	 * * @param request クライアントからのリクエスト情報（編集対象のidを含む）
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット実行時に例外が発生した場合
	 * @throws IOException 入出力処理で例外が発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. パラメータからIDを取得
		// 数値以外が送られた場合の例外処理を考慮する場合は try-catch が推奨されます
		int id = Integer.parseInt(request.getParameter("id"));
		
		// 2. DAOを使用して、現在の登録内容を取得
		AddressBookDAO dao = new AddressBookDAO();
		AddData address = dao.findById(id); 

		// 3. 取得したデータをリクエストスコープにセットし、編集画面（JSP）へ遷移
		request.setAttribute("address", address);
		request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
	}

	/**
	 * 住所情報の更新処理を実行します。
	 * 編集画面のフォームから送信されたデータを受け取り、データベースを更新します。
	 * * @param request 編集後のデータを含むリクエスト情報
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット実行時に例外が発生した場合
	 * @throws IOException 入出力処理で例外が発生した場合
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// リクエストパラメータの文字化け防止
		request.setCharacterEncoding("UTF-8");

		// 1. フォームからの入力値を取得
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");

		// 2. 更新用データをDTOにまとめ、DAOでデータベースを更新
		AddData address = new AddData(id, name, phone, address1, address2, address3);
		AddressBookDAO dao = new AddressBookDAO();
		boolean isSuccess = dao.update(address);

		// 3. 結果に応じた画面遷移
		if (isSuccess) {
			// 更新成功：二重送信防止のため、一覧画面へリダイレクト
			response.sendRedirect("TableListServlet");
		} else {
			// 更新失敗：エラーメッセージをセットして編集画面を再表示
			request.setAttribute("errMsg", "更新に失敗しました。再度やり直してください。");
			doGet(request, response);
		}
	}
}