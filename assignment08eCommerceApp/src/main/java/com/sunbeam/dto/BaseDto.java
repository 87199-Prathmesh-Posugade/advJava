package com.sunbeam.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseDto {
	private Long id;
	private LocalDate createdOn;	
	private LocalDateTime updatedOn;
}
