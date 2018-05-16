package com.zyq.service;

import cn.hutool.http.HttpRequest;
import com.baidu.aip.auth.DevAuth;
import com.baidu.aip.http.AipHttpClient;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.http.AipResponse;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.util.AipClientConfiguration;
import com.baidu.aip.util.Util;
import com.zyq.dto.AppInfo;
import com.zyq.dto.BaiduConfig;
import com.zyq.util.FileKit;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 2018-5-16.
 */
@Slf4j
@Service
public class VoiceService {

    @Autowired
    private AppInfo appInfo;

    @Autowired
    private BaiduConfig baiduConfig;

    private String url ="https://aip.baidubce.com/rpc/2.0/nlp/v1/lexer?charset=UTF-8&access_token=";

    public List<String> getList(MultipartFile file) {
        String fileName = FileKit.saveUploadFile(file, appInfo.getFilePath());
        JSONObject jsonObject = getVoice(fileName);
        return str2list(jsonObject.toString(2));
    }

    private JSONObject getVoice(String fileName) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(baiduConfig.getAppId(), baiduConfig.getApiKey(), baiduConfig.getSecretKey());
        // 调用接口
        JSONObject res = client.asr(fileName, fileName.substring(fileName.lastIndexOf(".")), 16000, null);
        return res;
    }
    public List<String> str2list(String str){
        String access_token = getAuth(baiduConfig.getApiKey(), baiduConfig.getSecretKey());
        HttpRequest request = HttpRequest.post(url+access_token);
        request.header("Content-Type","application/json");
        request.body(new JSONObject().put("text",str).toString());
        String res = FileKit.getUTF8StringFromGBKString(request.execute().body());
        cn.hutool.json.JSONObject result = new cn.hutool.json.JSONObject(res);
        List<String> list = new ArrayList<>();
        for(Object temp:result.getJSONArray("items")){
            list.add(new cn.hutool.json.JSONObject(temp).getStr("item"));
        }
        return list;
    }
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            String result = HttpRequest.get(getAccessTokenUrl).execute().body();
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }
}
