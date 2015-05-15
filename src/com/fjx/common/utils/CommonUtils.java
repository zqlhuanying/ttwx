package com.fjx.common.utils;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.fjx.common.framework.system.context.MySystemContext;
import com.fjx.common.framework.system.exception.MyRuntimeException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author peng
 */
public final class CommonUtils {


    public static final String DATA_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_FORMAT_DD = "yyyy-MM-dd";

    /**
     * 生成1-9位随机数
     *
     * @param count 位数
     * @return 返回count位随机数
     */
    public static String getRandomNum(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 主键生成器，基于时间戳+机器地址的
     *
     * @return 主键
     */
    public static String getPrimaryKey() {
        EthernetAddress nic = EthernetAddress.fromInterface();
        TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator(nic);
        UUID uuid = uuidGenerator.generate();
        return uuid.toString().replaceAll("-", "").toLowerCase();
    }

    /**
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return date2String(date, DATA_FORMAT_ALL);
    }

    /**
     * @param date
     * @param dataFormat 默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2String(Date date, String dataFormat) {
        if (null == date) {
            return null;
        }
        String dateFmt = DATA_FORMAT_ALL;
        if (StringUtils.isNotBlank(dataFormat)) {
            dateFmt = dataFormat;
        }
        DateFormat fmt = new SimpleDateFormat(dateFmt);
        return fmt.format(date);
    }


    /**
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String date) {
        return string2Date(date,DATA_FORMAT_ALL);
    }


    /**
     * @param date
     * @param dataFormat 默认为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String date, String dataFormat) {
        String dateFmt = DATA_FORMAT_ALL;
        if (StringUtils.isNotBlank(dataFormat)) {
            dateFmt = dataFormat;
        }
        DateFormat fmt = new SimpleDateFormat(dateFmt);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            throw new MyRuntimeException(e);
        }
    }

    /**
     * 获得生成html文件模板路径
     *
     * @param request
     * @return
     */
    public static String getFtlHtmlPath(HttpServletRequest request) {
        String baseUrl = "/WEB-INF/ftl/html";
        try {
            return WebUtils.getRealPath(request.getSession().getServletContext(), "/") + baseUrl;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得上传目录路径
     *
     * @param request
     * @return
     */
    public static String getUploadPath(HttpServletRequest request) {
        String baseUrl = "wechat";
        try {
            return WebUtils.getRealPath(request.getSession().getServletContext(), "/") + baseUrl;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得项目classPath
     *
     * @return
     */
    public static String getClassPath(){
        String classPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        if(SystemUtils.IS_OS_WINDOWS){
            classPath = classPath.substring(1,classPath.length());
        }
        return classPath;
    }

    /**
     * 获得项目classPath
     *
     * @return
     */
    public static String getClassPath(String path){
        String classPath = Thread.currentThread().getContextClassLoader().getResource(path).getPath();
        if(SystemUtils.IS_OS_WINDOWS){
            classPath = classPath.substring(1,classPath.length());
        }
        return classPath;
    }

}
