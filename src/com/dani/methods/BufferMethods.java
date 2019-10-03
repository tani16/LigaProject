package com.dani.methods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class BufferMethods {

	private static final String URL_POST = "https://api.bufferapp.com/1/updates/create.json";
	private static OkHttpClient client = new OkHttpClient();
	
	private BufferMethods() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void postOnBuffer(String post) throws IOException {
		Properties p = new Properties();
		p.load(new FileInputStream("secret.properties"));
		
		RequestBody formBody = new FormEncodingBuilder()
				.add("text", post)
				.add("profile_ids[]", p.getProperty("twitter_profile"))
				.build();
		
		Request request = new Request.Builder()
				.header("Authorization", "Bearer " + p.getProperty("buffer_access"))
				.url(URL_POST)
				.post(formBody)
				.build();
		
		Response response = client.newCall(request).execute();
		
	    System.out.println(response.code());
	}
}
