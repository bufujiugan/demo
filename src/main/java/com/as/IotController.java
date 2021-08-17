package com.as;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;

@RestController
public class IotController {

    @RequestMapping("/upload")
    public String httpUpload(@RequestParam("file") MultipartFile file){
        //http请求地址
        HttpPost httpPost = new HttpPost("http://swgy-zsgy.andnext.cn:8640/zsgy/bmpdoor/app/renter/faceCheck");
        CloseableHttpClient httpClient = null;
        HttpResponse response = null;
        try {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            httpClient = httpClientBuilder.build();
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(200000).setSocketTimeout(200000).build();
            httpPost.setConfig(config);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
            InputStreamBody inputBody = new InputStreamBody(file.getInputStream(), file.getOriginalFilename());
            String accessKey = "abs5wfwe5zs";
            String aesKey = "oneparkzsgytest1";
            String xTenantHeader = "64eca34885ab41fd92e332e6ef82d329";
            long time = System.currentTimeMillis();
            String code = SignUtil.getRandomCode();
            String sign = SignUtil.sign(time, accessKey, code, aesKey);
            multipartEntityBuilder.addPart("photoFile", inputBody);

            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setHeader("timeStamp", String.valueOf(time));
            httpPost.setHeader("x-tenant-header", xTenantHeader);
            httpPost.setHeader("code", code);
            httpPost.setHeader("sign", sign);
            httpPost.setHeader("accessKey", accessKey);
            httpPost.setEntity(httpEntity);
            response = httpClient.execute(httpPost);
            //响应码为 200 ， 表示上传成功
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
            //响应返回信息，json格式字符串
        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return "1";
    }
}
