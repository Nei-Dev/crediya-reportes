package com.crediya.model.auth;

import lombok.Getter;

@Getter
public enum AuthClaimsEnum {
	
	USER_ID("userId"),
	IDENTIFICATION("identification"),
	ROLE("role");
	
	private final String value;
	
	AuthClaimsEnum(String value) {
		this.value = value;
	}
	
}
