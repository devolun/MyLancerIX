package com.mylancerix.pojo;

public class DirectoryParts {
	private String name;
	private String oem;

	public DirectoryParts(String name, String oem) {
		super();
		this.name = name;
		this.oem = oem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

}
