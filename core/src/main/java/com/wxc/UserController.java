package com.wxc;

import com.netflix.client.http.HttpResponse;
import com.wxc.entity.User;
import com.wxc.mapper.UserMapper;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/")
public class UserController {
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/insert")
    public ModelAndView index() {
        User user = new User();
        user.setAge(18);
        user.setName("Adam");
        user.setPwd("123456");
        userMapper.insert(user);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("count", userMapper.findAll().size());
        return modelAndView;
    }
    @RequestMapping("/getBoolean")
    public String getBoolean() {

        boolean b0 = userMapper.getBoolean0();
        boolean b1 = userMapper.getBoolean1();
        int i0 = userMapper.getInt0();
        int i1 = userMapper.getInt1();
        System.out.println(b0);
        System.out.println(b1);
        System.out.println(i0);
        System.out.println(i1);
        return "b0="+b0+",b1="+b1+",i0="+i0+",i1="+i1;
    }

    //应用场景 打包
    @RequestMapping(value = "downloadZip")
    public HttpServletResponse downloadZip(HttpServletRequest request, HttpServletResponse response)throws Exception {

        return getZip(request.getParameter("path"),response) ;
    }

    public HttpServletResponse getZip(String filesPath,HttpServletResponse response) {
        File file = new File("E:\\vehicleCirculate\\20210520");
        File[] files = file.listFiles();
        //打包凭证.zip与EXCEL一起打包
        try {
            String zipFilenameA =  "/20210520.zip";
            File fileA = new File(zipFilenameA);
            if (!fileA.exists()){
                fileA.createNewFile();
            }
            response.reset();
            //response.getWriter()
            //创建文件输出流
            FileOutputStream fousa = new FileOutputStream(fileA);
            ZipOutputStream zipOutA = new ZipOutputStream(fousa);
            ZipUtils.batchZip(files, zipOutA);
            zipOutA.close();
            fousa.close();
            return ZipUtils.downloadZip(fileA,response);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
