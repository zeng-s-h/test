package zipdeal;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author 小白i
 * @date 2020/7/8
 */
public class UnpressFile {

    public static void main(String[] args) {
        //当前压缩文件
        File file = new File("D:\\TestbyYTT.zip");
        //创建ZipInputStream对象
        ZipInputStream zin;
        try {
            //创建压缩文件对象
            ZipFile zipFile = new ZipFile(file);
            //实例化对象，指明要解压的文件
            zin = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry;
            //如果entry不为空，并不在同一个目录下
            while (((entry = zin.getNextEntry()) != null) && !entry.isDirectory()) {
                //解压出的文件路径
                File tmp = new File(entry.getName());
                //如果文件不存在
                if (!tmp.exists()) {
                    //创建文件父类文件夹路径
                    tmp.getParentFile().mkdirs();
                    //将文件目录中的文件放入输出流
                    OutputStream os = new FileOutputStream(tmp);
                    //用输入流读取压缩文件中制定目录中的文件
                    InputStream in = zipFile.getInputStream(entry);
                    int count = 0;
                    //如有输入流可以读取到数值
                    while ((count = in.read()) != -1) {
                        //输出流写入
                        os.write(count);
                    }
                    os.close();
                    in.close();
                }
                zin.closeEntry();
                System.out.println(entry.getName() + "解压成功");
            }
            zin.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
