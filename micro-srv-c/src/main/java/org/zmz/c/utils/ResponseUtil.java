package org.zmz.c.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUtil {
    public static final String SUCCESS = "0";

    public static final String FAIL = "-1";

    public static final String WATI = "2";

    public static final String TABLE_NOT_FOUND = "-2";

    public static final String RESULTMSG_SUCCESS = "SUCCESS";

    public static final String RESULTMSG_FAIL = "ERROR";

    public ResponseUtil() {
        resultCode = SUCCESS;
        resultMsg = "";
    }

    public Object resultObject;

    private String resultCode;

    private String resultMsg;

    private String errorCode;

    public boolean isSuccess() {
        return "0".equals(this.resultCode);
    }
}