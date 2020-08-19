package com.simmon.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/27 18:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    private Integer code;

    private String message;

    private T data;

    public ResponseResult(Integer code, String message) {
        this(code, message, null);
    }
}
