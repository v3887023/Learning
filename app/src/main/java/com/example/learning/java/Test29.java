package com.example.learning.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.example.learning.java.Utils.println;

public class Test29 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10086);
            while (true) {
                final Socket socket = serverSocket.accept();
                println("new connection: " + socket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FileInputStream fis = null;

                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            final String message = reader.readLine();
                            final int i = Integer.parseInt(message.trim());

                            File file = new File("F:\\pic\\" + i + ".jpg");
                            int length = (int) file.length();
                            println("file length: " + length);
                            fis = new FileInputStream(file);
                            println("available request");
                            int len;
                            byte[] buf = new byte[1024];
                            OutputStream os = socket.getOutputStream();
                            os.write((file.getAbsolutePath() + "\n").getBytes());
                            os.write(intToBytes(length));
                            while ((len = fis.read(buf)) != -1) {
                                os.write(buf, 0, len);
                            }
                            println("file send");
                            socket.close();
                            println("close connection");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            println("bad request");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (fis != null) {
                                try {
                                    fis.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] intToBytes(int n) {
        byte[] bytes = new byte[4];

        bytes[3] = (byte) (n & 0xFF);

        n >>= 8;
        bytes[2] = (byte) (n & 0xFF);

        n >>= 8;
        bytes[1] = (byte) (n & 0xFF);

        n >>= 8;
        bytes[0] = (byte) (n & 0xFF);

        return bytes;
    }
}
