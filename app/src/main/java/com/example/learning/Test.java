package com.example.learning;

import com.example.learning.java.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-08-27
 * <p>
 * Description:
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10086);
        while (true) {
            Socket socket = serverSocket.accept();
//            Utils.println("新连接：" + socket.getInetAddress());
//            File file = new File("/Users/ddai/Downloads/icon_moresite.png");
//
//            FileInputStream fis = new FileInputStream(file);
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//            int len;
//            byte[] buf = new byte[1024];
//            while ((len = fis.read(buf)) != -1) {
//                baos.write(buf, 0, len);
//            }
//
//            byte[] array = baos.toByteArray();
//
//            OutputStream os = socket.getOutputStream();
//            os.write("HTTP/1.1 200 OK\r\n".getBytes());
//            os.write("Server: Molly\r\n".getBytes());
//            os.write("Content-Type: image/jpeg\r\n".getBytes());
//            os.write(String.format(Locale.CHINA, "Content-Length: %d\r\n", array.length).getBytes());
//            os.write("Content-Disposition: attachment;filename=more_site.png\r\n".getBytes());
//            os.write("\r\n".getBytes());
//            os.write(array, 0, array.length);
//            os.flush();
//            fis.close();
//
//            Utils.println("结束");

            a(socket);
        }
    }

    private static void a(Socket socket) throws IOException {
        Utils.println("新连接：" + socket.getInetAddress());
        File file = new File("/Users/ddai/Downloads/icon_moresite.png");

        FileInputStream fis = new FileInputStream(file);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int len;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }

        byte[] array = baos.toByteArray();

        OutputStream os = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(os);
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Server: Molly");
        printWriter.println("Content-Type: image/jpeg");
        printWriter.println(String.format(Locale.CHINA, "Content-Length: %d", array.length));
        printWriter.println("Content-Disposition: attachment;filename=more_site.png");
        printWriter.println("");
        printWriter.flush();

        os.write(array, 0, array.length);

        os.flush();
        fis.close();

        Utils.println("结束");
    }
}
