package com.as;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.UUID;

@Controller
public class FileController {


    /**
     * 文件流上传到金科
     * @param multipartFile
     * @return
     */
    @PostMapping("/save")
    public static String filePost(MultipartFile multipartFile) {
        if(null != multipartFile) {
            try {
//                log.info("上传文件大小：" + multipartFile.getSize() / 1024 + "kb;文件名称:" + multipartFile.getOriginalFilename());

                String BOUNDARY = UUID.randomUUID().toString();
                MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                        .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                        .setBoundary(BOUNDARY)
                        .setCharset(Charset.defaultCharset());
//                multipartEntityBuilder.addPart("photoFile", new InputStreamBody(multipartFile.getInputStream(), multipartFile.getOriginalFilename()));
                multipartEntityBuilder.addPart("photoFile", new FileBody(bytesToFile(multipartFile.getBytes(), System.currentTimeMillis() + ".jpg")));
                String url = "http://swgy-zsgy.andnext.cn:8640/bmpdoor/app/renter/faceCheck";
                HttpPost request = new HttpPost(url);
                request.setEntity(multipartEntityBuilder.build());
                request.addHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
                CloseableHttpClient httpClient =  HttpClients.createDefault();
                HttpResponse response = httpClient.execute(request);
                InputStream is = response.getEntity().getContent();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = in.readLine()) != null) {
                    buffer.append(line);
                }
//                log.info("发送消息收到的返回：{}", unicodeToUtf8(buffer.toString()));
//                return unicodeToUtf8(buffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * base转file
     * @param buffer
     * @param filePath
     * @return
     */
    public static File bytesToFile(byte[] buffer, final String filePath){
        File file = new File(filePath);
        OutputStream output = null;
        BufferedOutputStream bufferedOutput = null;
        try {
            output = new FileOutputStream(file);
            bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(null!=bufferedOutput){
                try {
                    bufferedOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != output){
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
