package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

@SpringBootApplication
@RestController
/**
 * Application that present an API for reading posts from jsonplaceholder.typicode.com/posts
 */
public class PostReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostReaderApplication.class, args);
    }

    @GetMapping("/posts")
    /**
     * Gets all the posts from jsonplaceholder.typicode.com/posts
     */
    public String getPosts() throws Exception {
        String data = getContent("https://jsonplaceholder.typicode.com/posts");
        return data;
    }

    @GetMapping("/comments")
    /**
     * Get the comments associated with the supplied post ID.
     */
    public String getComments(@RequestParam String postId) throws Exception {
        return  getContent("https://jsonplaceholder.typicode.com/posts/" + postId + "/comments");
    }

    private String getContent(String contentUrl)  {
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(contentUrl);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:" + contentUrl, e);
        }
        return sb.toString();
    }

}
