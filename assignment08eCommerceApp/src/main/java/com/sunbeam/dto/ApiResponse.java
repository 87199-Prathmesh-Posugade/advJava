package com.sunbeam.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
	private LocalDateTime l;
	private String message;

	public ApiResponse(String msg) {
		message = msg;
		l = LocalDateTime.now();
	}
}
