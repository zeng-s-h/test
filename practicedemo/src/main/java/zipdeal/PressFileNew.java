package zipdeal;

import org.apache.commons.lang.ArrayUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author 小白i
 * @date 2020/7/8
 */
public class PressFileNew {

    /**
     * 获取输出的zip流
     *
     * @param inputFile
     * @param outputFile
     * @throws Exception
     */
    public static void zipCompress(String inputFile, String outputFile) throws Exception {
        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        //获取输入的文件
        File input = new File(inputFile);
        compress(out, bos, input, null);
    }

    /**
     * @param out   zip输出流
     * @param bos   缓冲流
     * @param input 输入的文件
     * @param name  压缩文件名，可以写为null保持默认
     */
    public static void compress(ZipOutputStream out, BufferedOutputStream bos, File input, String name) throws IOException {
        if (name == null) {
            name = input.getName();
        }
        //如果路径为目录（文件夹）
        if (input.isDirectory()) {
            //取出文件夹中的文件（或子文件夹）
            File[] flist = input.listFiles();
            if (ArrayUtils.isEmpty(flist)) {
                flist = new File[0];
            }

            //如果文件夹为空，则只需在目的地zip文件中写入一个目录进入
            if (flist.length == 0) {
                out.putNextEntry(new ZipEntry(name + "/"));
            } else {
                //如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
                for (int i = 0; i < flist.length; i++) {
                    compress(out, bos, flist[i], name + "/" + flist[i].getName());
                }
            }
        } else {
            //如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            out.putNextEntry(new ZipEntry(name));
            FileInputStream fos = new FileInputStream(input);
            BufferedInputStream bis = new BufferedInputStream(fos);
            int len;
            //将源文件写入到zip文件中
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bis.close();
            fos.close();
        }
    }

    public static void main(String[] args) {
        try {
            zipCompress("D:\\download", "D:\\TestbyYTT.zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
