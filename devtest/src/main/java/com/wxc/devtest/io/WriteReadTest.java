package com.wxc.devtest.io;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WriteReadTest {
    private static final Logger logger = LoggerFactory.getLogger(WriteReadTest.class);
    public static void main(String[] args) {
        String filePath = "E:\\vehicle\\csv\\20210531\\test.csv";
        List<List<String>> writeContents = new CopyOnWriteArrayList<>();
        /*new Thread(()->{
            while (true) {
                if (!writeContents.isEmpty()) {
                    write(writeContents,filePath);
                    writeContents.clear();
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
        List<String> contents = new LinkedList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                long stime = System.currentTimeMillis();
                logger.info("load csv file ....,filePath:{}", filePath);
                //读取并过滤
                /*CsvListReader reader = new CsvListReader(new InputStreamReader(new FileInputStream(file), "GBK"), CsvPreference.STANDARD_PREFERENCE);
                reader.getHeader(true);// 去除头部字段声明
                List<String> line;
                int i = 0;
                while ((line = reader.read()) != null) {
                    //日志定位问题
                    i++;
                    if (i % 1000000 == 0) {
                        logger.info("load csv file crazing,filePath:{},load record size:{},cost time:{}s", filePath, i, (System.currentTimeMillis() - stime) / 1000);
                    }
                    contents.add(line);
                    *//*if (i % 100000 == 0) {
                        line.set(0, "1");
                        writeContents.add(line);
                    }*//*
                }*/
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
                String line = null;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    i++;
                    if (i % 1000000 == 0) {
                        logger.info("load csv file crazing,filePath:{},load record size:{},cost time:{}s", filePath, i, (System.currentTimeMillis() - stime) / 1000);
                    }
                    contents.add(line);
                }
                if (reader != null) {
                    reader.close();
                }
                logger.info("load csv file finished,filePath:{},load record size:{},cost time:{}s", filePath, contents.size(), (System.currentTimeMillis() - stime) / 1000);
            } else {
                logger.info("file not exist! filePath:{},fileName:{}.",filePath);
            }
        } catch (IOException e) {
            logger.error("load csv file failed.filePath:{}", filePath, e);
        }
        logger.info("contents size:{}",contents.size());
    }
    private static void write(List<List<String>> list, String fileName) {
        logger.info("write to csv start...,filePath:{}.", fileName);
        long stime = System.currentTimeMillis();
        try {

            File file = new File(fileName);

            List<List<String>> contents =new ArrayList<>();
            for (List<String> entity : list) {
                contents.add(entity);
            }
            writeToCsv(file, contents);
            logger.info("writeToCsv,path:{}, write size:{}, performence time:{}s", file,list.size(),(System.currentTimeMillis()-stime)/1000);
        } catch (IOException e) {
            logger.error("writeToCsv failed,path:{}.",e);
        }

    }
    public static void writeToCsv(File file, List<List<String>> content) throws IOException {
        CsvListWriter writer = new CsvListWriter(new FileWriterWithEncoding(file, Charset.forName("GBK"),true),
                CsvPreference.STANDARD_PREFERENCE);
        for (List<String> str : content) {
            writer.write(str);
        }
        writer.close();
    }
}
