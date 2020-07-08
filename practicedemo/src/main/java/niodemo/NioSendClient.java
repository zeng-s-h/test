package niodemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author 小白i
 * @date 2020/6/3
 */
public class NioSendClient {

    private static String targetSrc = "D:\\download\\啦啦啦.mp3";

    private static String sourceSrc = "D:\\download\\梦然-少年.mp3";

    public void sendFile() throws IOException {

        Charset charset = StandardCharsets.UTF_8;

        File srcFile = new File(sourceSrc);
        if (!srcFile.exists()) {
            return;
        }

        FileChannel inChannel = new FileInputStream(srcFile).getChannel();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        //socketChannel.connect(new InetSocketAddress("127.0.0.1",80));
        socketChannel.socket().connect(new InetSocketAddress("127.0.0.1",80));

        while (!socketChannel.finishConnect()) {
        }

        ByteBuffer targetFileName = charset.encode(targetSrc);
        socketChannel.write(targetFileName);

        ByteBuffer allocate = ByteBuffer.allocate(1024);
    }

}
