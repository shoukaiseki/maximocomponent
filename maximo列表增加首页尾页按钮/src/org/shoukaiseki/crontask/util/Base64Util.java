package org.shoukaiseki.crontask.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

/**
 * org.shoukaiseki.crontask.util.Base64Util <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-21 11:18:47<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class Base64Util {

    public static String encodeBase64(byte[] in)  {
        byte[] bytes = Base64.encodeBase64(in);
        return new String(bytes,Charset.forName("UTF-8"));
    }

    public static String encodeBase64URLSafeString(byte[] in)  {
        String str = encodeBase64(in);
        str=str.replace('+','-');
        str=str.replace('/','_');
        str=str.replaceAll("=","");
        return str;
    }

}
