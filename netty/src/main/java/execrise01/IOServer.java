package execrise01;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhouwentong on 2018/6/19.
 */
public class IOServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8899);
        new Thread(() -> {
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Socket finalSocket = socket;
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        InputStream inputStream = null;
                        try {
                            inputStream = finalSocket.getInputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        byte[] bytes = new byte[1024];
                        int len = 0;
                        try {
                            while ((len = inputStream.read(bytes)) != -1) {
                                System.out.println(new String(bytes, 0, len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

            }

        }).start();


    }
}
