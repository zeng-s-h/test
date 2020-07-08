package zipdeal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author 小白i
 * @date 2020/7/8
 */
public class PressFile {

    public static void pressZip(String input,String output,String name) throws Exception{
        FileOutputStream outputStream = new FileOutputStream(output);
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        //获取多个路径
        String[] paths = input.split("\\|");
        File[] files = new File[paths.length];
        byte[] buffer = new byte[1024];
        for (int i = 0; i < paths.length; i++) {
            files[i] = new File(paths[i]);
        }
        for (int i = 0; i < files.length; i++) {
            FileInputStream fis = new FileInputStream(files[i]);
            if (files.length == 1 && name != null) {
                zipOutputStream.putNextEntry(new ZipEntry(name));
            } else {
                zipOutputStream.putNextEntry(new ZipEntry(files[i].getName()));
            }
            int len;
            // 读入需要下载的文件的内容，打包到zip文件
            while ((len = fis.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, len);
            }
            zipOutputStream.closeEntry();
            fis.close();
        }
        zipOutputStream.close();
    }

    public static void main(String[ ] args){
        try {
            pressZip("D:\\download\\梦然-少年.mp3","D:\\Test.zip","testytt.mp3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
