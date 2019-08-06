package com.example.learning.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static com.example.learning.java.Utils.println;

public class Test14 {
    public static void main(String[] args) throws IOException {
        String host = "www.baidu.com";
        String path = "/";
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(host, 80);
        socket.connect(address);

        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
        osw.write("GET " + path + " HTTP/1.1\r\n");
        osw.write("Host:" + host + "\r\n");
        //http协议必须在报文头后面再加一个换行，通知服务器发送完成，不然服务器会一直等待
        osw.write("\r\n");
        osw.flush();
        socket.shutdownOutput();

        final InputStream is = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String text;
        while ((text = reader.readLine()) != null) {
            builder.append(text).append('\n');
        }

        println(builder.toString());
    }
}
