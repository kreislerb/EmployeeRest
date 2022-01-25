package com.dis.controller.response;

import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

	private String status;
	private T data;
	private String message;
	private List<String> errors;
}
