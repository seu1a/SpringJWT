package kr.seula.springjwt.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {

    private boolean success;
    private int code;
    private String message;
    private T data;

}
