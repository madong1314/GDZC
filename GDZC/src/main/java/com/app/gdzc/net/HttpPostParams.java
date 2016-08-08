package com.app.gdzc.net;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public class HttpPostParams {

    private static HttpPostParams httpPostParams;
    private HashMap<String, String> params;

    public Map<String,ByteArrayInputStream> mapImages; //存图片的
    public Map<String,File> mapFiles; //待上传的文件
    public static HttpPostParams getInstance() {
        if (httpPostParams == null) {
            httpPostParams = new HttpPostParams();
        }
        return httpPostParams;
    }
    //通用类型请求参数
    public Map<String, String> BaseParams() {
        if (params == null) {
            params = new HashMap<String, String>();
        }
        params.clear();
        return params;
    }
}
