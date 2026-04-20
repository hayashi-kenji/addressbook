package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AddData;
import model.Address;

/**
 * 住所録データベース（addressesテーブル）へのアクセスを担当するDAOクラスです。
 * データの取得、登録、更新、削除（CRUD操作）を提供します。
 * * @author ab220
 * @version 1.0
 */
public class AddressBookDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/addressbook";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
	}

	/**
	 * 指定されたIDに一致する住所情報を取得します。
	 * * @param id 取得したい住所データのID
	 * @return 一致する住所データ（{@link AddData}）。見つからない場合は null
	 */
	public AddData findById(int id) {
		AddData address = null;
		String sql = "SELECT * FROM addresses WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				address = new AddData(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("phone"),
						rs.getString("address1"),
						rs.getString("address2"),
						rs.getString("address3"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}

	/**
	 * 全ての住所情報を取得します。
	 * 都道府県名は prefectures テーブルと結合して名称を取得します。
	 * * @return 住所データのリスト。データが存在しない場合は空のリストを返します。
	 */
	public List<AddData> findAll() {

		List<AddData> list = new ArrayList<>();

		// データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT 分を用意
			String sql = "SELECT "
					+ "a.id,"
					+ "a.name,"
					+ "a.phone,"
					+ "p.prefecture AS address1,"
					+ "a.address2,"
					+ "a.address3 "
					+ "	FROM addresses a JOIN prefectures p ON a.address1 = p.address1";

			// SQL 送信のための PrepareStatement オブジェクトの取得
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 検索系 SQL (SELECT) の実行
			ResultSet rs = pstmt.executeQuery();

			//検索結果の ResultSet をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address1 = rs.getString("address1");
				String address2 = rs.getString("address2");
				String address3 = rs.getString("address3");
				AddData address = new AddData(id, name, phone, address1, address2, address3);
				list.add(address);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 新しい住所情報をデータベースに登録します。
	 * * @param address 登録する住所情報（{@link Address}）
	 * @return 登録成功時は true、失敗時は false
	 */
	public boolean insert(Address address) {
		// データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT 分を用意
			String sql = "INSERT INTO addresses(name, phone, address1, address2, address3) "
					+ "VALUES(?, ?, ?, ?, ?)";

			// SQL 送信のための PrepareStatement オブジェクトの取得
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// SQL のプレースホルダ ? に値をセットする
			pstmt.setString(1, address.getName());
			pstmt.setString(2, address.getPhone());
			pstmt.setString(3, address.getAddress1());
			pstmt.setString(4, address.getAddress2());
			pstmt.setString(5, address.getAddress3());

			// 更新系 SQL (INSERT) の実行
			//System.out.println(sql);
			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 指定されたIDの住所情報を削除します。
	 * * @param id 削除対象のID
	 * @return 削除成功時は true、失敗時は false
	 */
	public boolean delete(int id) {
		String sql = "DELETE FROM addresses WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); // IDをセット
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 既存の住所情報を更新します。
	 * * @param address 更新内容を含む住所データ（{@link AddData}）
	 * @return 更新成功時は true、失敗時は false
	 */
	public boolean update(AddData address) {
		String sql = "UPDATE addresses SET name=?, phone=?, address1=?, address2=?, address3=? WHERE id=?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// プレースホルダに値をセット
			pstmt.setString(1, address.getName());
			pstmt.setString(2, address.getPhone());
			pstmt.setString(3, address.getAddress1());
			pstmt.setString(4, address.getAddress2());
			pstmt.setString(5, address.getAddress3());
			pstmt.setInt(6, address.getId()); // WHERE句のID

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
