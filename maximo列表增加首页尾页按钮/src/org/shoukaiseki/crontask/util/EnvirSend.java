package org.shoukaiseki.crontask.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.shoukaiseki.crontask.AbstractSimpleCronTask;
import org.shoukaiseki.crontask.SyncModel;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * org.shoukaiseki.crontask.util.EnvirSend <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-21 11:33:47<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class EnvirSend {

    public static Logger log = AbstractSimpleCronTask.log;

    public static SyncModel sendPost(String url,SyncModel smIN)  {
        log.debug("url="+url);
        SyncModel sm=new SyncModel();
        String json = JSONObject.toJSONString(smIN);
        log.debug("json="+json);
        String decodeBase64= null;
        decodeBase64 = Base64Util.encodeBase64URLSafeString(json.getBytes());
        decodeBase64="syncmodeldata="+decodeBase64;
        log.debug("decodeBase64="+decodeBase64);
        byte[] bytes= new byte[0];
        try {
            bytes = sendPost(url,decodeBase64);
            String str=new String(bytes, Charset.forName("UTF-8"));
            log.debug("str="+str);
            sm=JSON.parseObject(str, SyncModel.class);
            log.debug("smOU="+JSONObject.toJSONString(sm));
        } catch (Exception e) {
            log.error("EnvirSend.sendPost:",e);
            sm.setStatus("sendPost error");
        }
        return sm;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws IOException
     */
    public static  byte[] sendPost(String url, String param) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);//允许输入流，即允许下载
        conn.setDoInput(true);//允许输出流，即允许上传
        conn.setUseCaches(false); //不使用缓冲
        conn.setRequestMethod("POST"); //使用get请求
        conn.setConnectTimeout(30000);
        // 发送请求参数

        // 获取URLConnection对象对应的输出流
        OutputStream outputStream = conn.getOutputStream();
        out = new PrintWriter(outputStream);
        out.print(param);
        // flush输出流的缓冲
        out.flush();

        // 定义BufferedReader输入流来读取URL的响应
        InputStream inputStream = conn.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int c;
        byte[] respBuffer =null;
        respBuffer = new byte[2048];
        while (true) {
            c = bis.read(respBuffer);
            if (c == -1)
                break;

            baos.write(respBuffer, 0, c);
        }


        conn.disconnect();
        inputStream.close();

        //使用finally块来关闭输出流、输入流
        if(out!=null){
            out.close();
        }
        byte[] data = baos.toByteArray();
        baos.flush();

        return data;

    }

    public static void main(String[] args) {
        String url="http://localhost:8080/envirservice/envir/error";
        SyncModel sm=new SyncModel();
        sm.setFilename("Error9_2017.txt");
        sm.setLinenum(0);
        sm = EnvirSend.sendPost(url, sm);

    }

}
