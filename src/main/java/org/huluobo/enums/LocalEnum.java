package org.huluobo.enums;

public enum LocalEnum {
	TEMPLATE_1("1","1_1_1"), TEMPLATE_2("2","1_1_2"), TEMPLATE_3("3","1_1_3"), TEMPLATE_4("4","1_1_4"), TEMPLATE_5("5","1_1_5"), TEMPLATE_6("6","1_1_6");

	private String type;
	private String msg = "1_1_1";
	
	LocalEnum(String type) {
		this.type = type;

	}

	LocalEnum(String type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	public static String getMsg(String type) {
		for (LocalEnum localEnum : LocalEnum.values()) {
			if (localEnum.getType().equals(type)) {
				return localEnum.getMsg();
			}
		}
		return "";
	}

	public String getType() {
		return type;
	}

	public String getMsg() {
		return msg;
	}

	public static void main(String[] args) {
		System.out.println(LocalEnum.getMsg("2"));
		System.out.println(LocalEnum.TEMPLATE_1.getMsg());
	}
}
