package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AddressBookDAO;

/**
 * 住所録データの削除処理を制御するサーブレットです。
 * 一覧画面などから送られたIDを基に、データベースから該当するレコードを削除します。
 * 削除完了後は、二重処理防止のため一覧画面へリダイレクトします。
 * * @author ab220
 * @version 1.0
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * GETリクエストを受け取り、指定されたIDのデータを削除します。
	 * 通常、一覧画面の「削除」リンクからの遷移を想定しています。
	 * * @param request クライアントからのリクエスト情報（削除対象のidを含む）
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット実行時に例外が発生した場合
	 * @throws IOException 入出力処理で例外が発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. リクエストパラメータから削除対象のIDを取得
		String idStr = request.getParameter("id");

		if (idStr != null && !idStr.isEmpty()) {
			try {
				int id = Integer.parseInt(idStr);

				// 2. DAOを介してデータベースから削除を実行
				AddressBookDAO dao = new AddressBookDAO();
				boolean isSuccess = dao.delete(id);

				// ログ出力（必要に応じてロガーに置き換え）
				if (isSuccess) {
					System.out.println("成功：ID " + id + " のデータを削除しました。");
				} else {
					System.out.println("失敗：ID " + id + " の削除に失敗しました（データが存在しない可能性があります）。");
				}
				
			} catch (NumberFormatException e) {
				// IDが数値でない不正なリクエストの場合の処理
				System.err.println("エラー：不正なID形式を受信しました。");
				e.printStackTrace();
			}
		}

		// 3. 処理完了後、一覧画面へリダイレクト
		// forwardではなくsendRedirectを使用することで、ブラウザの更新ボタンによる再削除（二重送信）を防止します。
		response.sendRedirect("TableListServlet");
	}
}