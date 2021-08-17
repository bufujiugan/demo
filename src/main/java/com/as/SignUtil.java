package com.as;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author linyx001
 * @date 2021/8/5 16:32
 */
public class SignUtil {

    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    private static final String CONTENT_FORMAT = "%d&%s&%s";

    public static void main(String[] args) {
        String accessKey = "abs5wfwe5zs";
        String aesKey = "oneparkzsgytest1";
        String xTenantHeader = "64eca34885ab41fd92e332e6ef82d329";
        long time = System.currentTimeMillis();
        String code = getRandomCode();
        String sign = sign(time, accessKey, code, aesKey);
        Map<String, String> headers = new HashMap<>();
        headers.put("timeStamp", String.valueOf(time));
        headers.put("x-tenant-header", xTenantHeader);
        headers.put("code", code);
        headers.put("sign", sign);
        headers.put("accessKey", accessKey);
        HttpRequest httpRequest = HttpUtil.createPost("http://swgy-zsgy.andnext.cn:8640/zsgy/bmpdoor/app/renter/faceCheck");
        httpRequest.addHeaders(headers);
        byte[] bytes = getBytesByFile("E:/微信图片_20210816154108.jpg");
        httpRequest.body("photoFile = " + bytes + "");
        System.out.println(httpRequest.execute().body());
        //失败 ：104100000 成功：104000000
    }




    //将文件转换成Byte数组
    public static byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*public static void main(String[] args) {
        String url = "http://swgy-zsgy.andnext.cn:8640/bmpdoor/app/renter/faceCheck";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        Header header = new BasicHeader("Content-type","multipart/form-data"); //此处不需要再额外添加头部信息，会自动添加好的
//处理文件 后面的setMode是用来解决文件名称乱码的问题:以浏览器兼容模式运行，防止文件名乱码。
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        File file = new File("E:/微信图片_20210816154108.jpg");

        try{
            builder.setCharset(Charset.forName("UTF-8")).addBinaryBody("photoFile", file, ContentType.MULTIPART_FORM_DATA, file.getName());

            HttpEntity httpEntity = builder.build();
            httpPost.setEntity(httpEntity);
            HttpResponse httpResponse = client.execute(httpPost);
            HttpEntity responseEntity = httpResponse.getEntity();
            if (responseEntity != null) { // 将响应内容转换为字符串
                String s = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * 计算校验字符串
     * @param time
     * @param xTenantHeader
     * @param code
     * @param aesKey
     * @return
     */
    public static String sign(long time, String xTenantHeader, String code, String aesKey) {
        String content = String.format(Locale.US, CONTENT_FORMAT, time, xTenantHeader, code);
        try {
            return md5Encode(bytes2Hex(aesEncryptToBytes(content, aesKey)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取随机字符串
     *
     * @return
     */
    public static String getRandomCode() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * md5散列方法
     *
     * @param content
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String md5Encode(String content) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("md5");
        byte[] result = digest.digest(content.getBytes());
        return bytes2Hex(result);
    }

    /**
     * byte数组转为16进制
     *
     * @param bytes
     * @return
     */
    private static String bytes2Hex(byte[] bytes) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
