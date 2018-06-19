package execrise01;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouwentong on 2018/6/19.
 */
public class IOClient {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 8899);
        new Thread(() -> {
            OutputStream outputStream = null;
            try {
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    outputStream.write((new Date() + "Hello World").getBytes());
                    outputStream.flush();
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
