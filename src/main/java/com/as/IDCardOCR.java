package com.as;

//import com.tencentcloudapi.common.Credential;
//import com.tencentcloudapi.common.profile.ClientProfile;
//import com.tencentcloudapi.common.profile.HttpProfile;
//import com.tencentcloudapi.common.exception.TencentCloudSDKException;
//
//import com.tencentcloudapi.ocr.v20181119.OcrClient;
//import com.tencentcloudapi.ocr.v20181119.models.*;;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class IDCardOCR {


    public static void main(String[] args) {
        try {
            Credential cred = new Credential("AKIDsb2A2g2DE3NUp2MnLegFagXG8VjnQGND", "HJ5meMtCIJ6ktUr1CRTFlCVeXfbJjvxi");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            OcrClient client = new OcrClient(cred, "ap-guangzhou", clientProfile);
            IDCardOCRRequest req = new IDCardOCRRequest();
            // 身份证正面图片
//            req.setImageBase64(fileToBase64("E://1.jpg"));
//            System.out.println(fileToBase64("E://4.jpg"));
            req.setImageBase64(fileToBase64("E://7.png"));
            IDCardOCRResponse resp = client.IDCardOCR(req);
            String json = IDCardOCRResponse.toJsonString(resp);
            json = json.replace("IdNum", "idcardNum")
                    .replace("Birth","birthday")
                    .replace("Name","realName")
                    .replace("Sex","gender")
                    .replace("Nation","race")
                    .replace("Authority","issuedBy")
                    .replace("ValidDate","validData");
//            IDCardOCRRequest reqBack = new IDCardOCRRequest();
//            // 身份证反面图片
//            reqBack.setImageBase64(fileToBase64("E://同住人.png"));
//            IDCardOCRResponse respBack = client.IDCardOCR(reqBack);
            System.out.println(json);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 文件转base64
     *
     * @param path
     * @return
     */

    public static String fileToBase64(String path) {
        String base64 = null;
        InputStream in = null;
        try {
            File file = new File(path);
            in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }
}
