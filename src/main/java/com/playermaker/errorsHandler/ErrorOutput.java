package com.playermaker.errorsHandler;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ErrorOutput {

	private String status;
	private String description;
}
