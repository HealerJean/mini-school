package com.hlj.proj.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UrlUtils
 * @Author TD
 * @Date 2019/2/13 16:47
 * @Description
 */
@Slf4j
public class UrlUtils {

    /**
     * 获取调用的域名
     * @param urlTarget
     * @return
     */
    public static String getDomainAndPort(String urlTarget)
    {
        //跳转到对应的回调地址
        String domain = "";
        try {
            URL url = new URL(urlTarget);
            String host = url.getHost();
            int port = url.getPort();
            String s = url.toString();
            domain = s.substring(0,s.indexOf(host)+host.length());
            if(port != -1) {
                domain = domain + ":" + port;
            }
        } catch (MalformedURLException e) {
            log.info("获取域名失败");
        }
        return domain ;
    }

    public static String genUrl(String hostPort, List<String> uriSegment , Map<String,String> params) {
        HttpUrl httpUrl = HttpUrl.parse(hostPort);
        HttpUrl.Builder builder = httpUrl.newBuilder();
        if(uriSegment != null && !uriSegment.isEmpty()){
            for (String segment: uriSegment) {
                builder.addPathSegment(segment);
            }
        }
        if(params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addEncodedQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        httpUrl = builder.build();
        return httpUrl.toString();
    }

    public static StringBuffer getRealRequestURL(HttpServletRequest request){

        StringBuffer requestURL = request.getRequestURL();
        return requestURL;
    }
}
