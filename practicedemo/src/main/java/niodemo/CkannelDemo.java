package niodemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 小白i
 * @date 2020/6/3
 * FileChannel为阻塞模式，不能设置为非阻塞模式
 */
public class CkannelDemo {

    private static String targetSrc = "D:\\download\\啦啦啦.mp3";

    private static String sourceSrc = "D:\\download\\梦然-少年.mp3";

    public static void main(String[] args) throws IOException {
        nioCopyFile();
    }

    private static void nioCopyFile() {

        //首先拿到文件对象
        File sourceFile = new File(sourceSrc);
        File targetFile = new File(targetSrc);

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            if (!targetFile.exists()) {
                boolean newFile = targetFile.createNewFile();
            }
            //获取流文件
            fileInputStream = new FileInputStream(sourceFile);
            fileOutputStream = new FileOutputStream(targetFile);

            //获取通道
            inChannel = fileInputStream.getChannel();
            outChannel = fileOutputStream.getChannel();
            //获取buffer对象
            ByteBuffer allocate = ByteBuffer.allocate(1024);

            int length = -1;
            //缓冲区读入数据
            while ((length = inChannel.read(allocate)) != -1) {
                //读完数据反转，变为写模式
                allocate.flip();

                int outLength = 0;
                while ((outLength = outChannel.write(allocate)) != 0) {
                    System.out.println("写入的数据长度为" + outLength);
                }

                //清除buffer，变为写模式
                allocate.clear();
            }

            outChannel.force(true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            assert outChannel != null;
            IoUtil.closeQuietly(outChannel);
            IoUtil.closeQuietly(fileOutputStream);
            IoUtil.closeQuietly(inChannel);
            IoUtil.closeQuietly(fileInputStream);
        }
    }

}
