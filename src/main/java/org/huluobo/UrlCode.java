package org.huluobo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlCode {

	public static void main(String[] args) {
		String url="http://localhost:8080/forestoa/sso/toTask?params=";
		String params= "number=1005&pwd=888888&url=/Filedocumentsenddetail?taskId=272";
		String  encode = encode(params);
		System.out.println("Decoded URL: " +url+  encode);
		String s=decode(encode);
		System.out.println(s);
		String [] ss=s.split("&");
		String number="";
		String pwd="";
		String a="";
		for(String tmp:ss) {
			String [] keyval=tmp.split("=");
			if("number".equals(keyval[0])) {
				number=keyval[1];
			}else if("pwd".equals(keyval[0])) {
				pwd=keyval[1];
			}else if("url".equals(keyval[0])) {
				a=keyval[1]+"="+keyval[2];
			}
		}
		System.out.println(number+"-"+pwd+"-"+a);
		
		System.out.println(decode("number%3D1013%26pwd%3D1%26url%3D%2FFilebriefingreceivedetail%3FtaskId%3D123"));
	}

	public static String encode(String url)
	{
		try {
			String encodeURL = URLEncoder.encode(url, "UTF-8");
			return encodeURL;
		} catch (UnsupportedEncodingException e) {
			return "Issue while encoding" + e.getMessage();
		}
	}

	public static String decode(String url) {
		try {
			String prevURL = "";
			String decodeURL = url;
			while (!prevURL.equals(decodeURL))

			{
				prevURL = decodeURL;
				decodeURL = URLDecoder.decode(decodeURL, "UTF-8");
			}
			return decodeURL;

		} catch (UnsupportedEncodingException e) {
			return "Issue while decoding" + e.getMessage();
		}
	}
}
