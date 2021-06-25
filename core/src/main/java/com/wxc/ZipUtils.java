package com.wxc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ZipUtils {

    public static void batchZip (File[] files,ZipOutputStream outputStream) {
        Set<String> code = new HashSet<>();
        for(File file:files) {
            if (file.isFile() && code.contains(file.getName().split("_")[0])) {
                zipFile(file, outputStream);
            }

        }
    }
    /**
     * 根据输入的文件与输出流对文件进行打包
     */
    public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream fileInputStream = new FileInputStream(inputFile);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 512);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int len;
                    byte[] buffer = new byte[512];
                    while ((len = bufferedInputStream.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, len);
                    }
                    // 关闭创建的流对象
                    bufferedInputStream.close();
                    fileInputStream.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static HttpServletResponse downloadZip(File file,HttpServletResponse response) {
        if(file.exists() == false){
            System.out.println("待压缩的文件目录："+file+"不存在.");
        }else{
            try {
                // 以流的形式下载文件。
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file.getPath()));
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                inputStream.close();
                // 清空response
                response.reset();

                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");

                //如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(file.getName().getBytes("GB2312"), "ISO8859-1"));
                outputStream.write(buffer);
                outputStream.flush();
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    File f = new File(file.getPath());
                    f.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

}
