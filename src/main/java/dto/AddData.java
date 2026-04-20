package dto;

import java.io.Serializable;

/**
 * 住所録の1件分のデータを保持するDTOクラスです。
 * データベースのレコード情報をオブジェクトとして扱うために使用します。
 * Serializableを実装しているため、セッション保存やネットワーク転送が可能です。
 * * @author ab220
 * @version 1.0
 */
public class AddData implements Serializable {
	/** シリアルバージョンUID (Serializable継承時に推奨) */
	private static final long serialVersionUID = 1L;

	/** ID */
	private int id;
	/** 名前 */
	private String name;
	/** 電話番号 */
	private String phone;
	/** 住所1（都道府県名など） */
	private String address1;
	/** 住所2（市区町村・番地など） */
	private String address2;
	/** 住所3（建物名・マンション名など） */
	private String address3;

	/**
	 * 指定されたパラメータで住所データオブジェクトを構築します。
	 * * @param id 識別ID
	 * @param name 氏名
	 * @param phone 電話番号
	 * @param address1 住所1（都道府県）
	 * @param address2 住所2（市区町村・番地）
	 * @param address3 住所3（建物名）
	 */
	public AddData(int id, String name, String phone, String address1, String address2, String address3) {
		super();

		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
	}

	/**
	 * IDを取得します。
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 名前を取得します。
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 電話番号を取得します。
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 住所1（都道府県）を取得します。
	 * @return address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * 住所2（市区町村・番地）を取得します。
	 * @return address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * 住所3（建物名）を取得します。
	 * @return address3
	 */
	public String getAddress3() {
		return address3;
	}
}
