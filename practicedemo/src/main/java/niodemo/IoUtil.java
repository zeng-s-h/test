package niodemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channel;

/**
 * @author 小白i
 * @date 2020/6/3
 */
public class IoUtil {

    static void closeQuietly(FileOutputStream stream) {

        assert stream != null : "传入的流不能为空！";
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void closeQuietly(FileInputStream stream) {

        assert stream != null : "传入的流不能为空！";
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void closeQuietly(Channel channel) {

        assert channel != null : "传入的通道不能为空！";
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
