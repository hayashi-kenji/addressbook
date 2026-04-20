package model;

import java.io.Serializable;

/**
 * 住所情報を表すモデルクラスです。
 * 主に新規登録時などの、IDを含まない住所エンティティとして使用されます。
 * Serializableを実装しており、オブジェクトの直列化が可能です。
 * * @author ab220
 * @version 1.0
 */
public class Address implements Serializable {
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 名前 */
	private String name;
	/** 電話番号 */
	private String phone;
	/** 住所1（都道府県コードまたは名称） */
	private String address1;
	/** 住所2（市区町村・番地） */
	private String address2;
	/** 住所3（建物名・マンション名） */
	private String address3;

	/**
	 * コンストラクタ。全てのフィールドを初期化します。
	 * * @param name 名前
	 * @param phone 電話番号
	 * @param address1 住所1（都道府県）
	 * @param address2 住所2（市区町村・番地）
	 * @param address3 住所3（建物名）
	 */
	public Address(String name, String phone, String address1, String address2, String address3) {
		super();
		this.name = name;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
	}

	/**
	 * 名前を取得します。
	 * @return name 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 電話番号を取得します。
	 * @return phone 電話番号
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 住所1（都道府県）を取得します。
	 * @return address1 住所1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * 住所2（市区町村・番地）を取得します。
	 * @return address2 住所2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * 住所3（建物名）を取得します。
	 * @return address3 住所3
	 */
	public String getAddress3() {
		return address3;
	}
}