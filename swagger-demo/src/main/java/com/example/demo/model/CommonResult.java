package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2020/1/5 10:34
 */
@Data
@Builder
public final class CommonResult<T> {

    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAIL = -1;
    public static final String DEFAULT_ERROR_CODE = "";
    public static final String DEFAULT_ERROR_MSG = "";

    private int status = STATUS_SUCCESS;

    private String errorCode = DEFAULT_ERROR_CODE;

    private String errorMsg = DEFAULT_ERROR_MSG;

    private T resultBody;

    @SuppressWarnings("unchecked")
    public static <T> CommonResult<T> success(T resultBody, int status, String errorCode, String errorMsg) {
        return (CommonResult<T>) CommonResult.builder().resultBody(resultBody).status(status).errorCode(errorCode).errorMsg(errorMsg).build();
    }

    public static <T> CommonResult<T> success(T resultBody) {
        return success(resultBody, STATUS_SUCCESS, DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG);
    }
    // .........
}
