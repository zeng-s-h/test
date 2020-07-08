package zipdeal;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author 小白i
 * @date 2020/7/8
 */
public class UnPressFileNew {

    /**
     * zip文件解压
     *
     * @param inputFile   待解压文件夹/文件
     * @param destDirPath 解压路径
     */
    public static void zipUncompress(String inputFile, String destDirPath) throws Exception {
        //获取当前压缩文件
        File srcFile = new File(inputFile);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        //开始解压
        //构建解压输入流
        ZipInputStream zIn = new ZipInputStream(new FileInputStream(srcFile));
        ZipEntry entry = null;
        File file = null;
        while ((entry = zIn.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                file = new File(destDirPath, entry.getName());
                if (!file.exists()) {
                    //创建此文件的上级目录
                    new File(file.getParent()).mkdirs();
                }
                OutputStream out = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = zIn.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                bos.close();
                out.close();
            }
        }
    }
    public static void main(String[] args) {
        try {
            zipUncompress("D:\\TestbyYTT.zip", "D:\\ytt的解压文件\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
