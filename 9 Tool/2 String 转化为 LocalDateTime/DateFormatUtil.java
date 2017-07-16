package cn.edu.dlut.career.util;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 用于完成string和localdatetime的格式转化
 *
 * Created by HealerJean on 2017/4/10.
 */
@Service
public class DateFormatUtil {
    /**
     *
     * @param dateString   string格式的日期
     * @param datetype     日期格式 比如 yyyy-MM-dd
     * @return
     */
    public LocalDateTime getLocalDateTime(String dateString,String datetype) {

        SimpleDateFormat formatter = new SimpleDateFormat(datetype.trim());
        try {
            Date date =  formatter.parse(dateString.trim());
            Instant instant = Instant.ofEpochMilli(date.getTime());
            LocalDateTime res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return  res;

        }catch (ParseException e){
            e.printStackTrace();
        }
            return  null;
    }


    /**
    * @Description 目前传来的日期的格式是 yyyy-MM-dd HH:mm:ss ，此时不能转化LocalDateTime，所以需要转化
    * @Author HealerJean
    * @CreateDate 2017/5/25 15:13
    */

    private LocalDateTime getLocalDateTime(String strDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(strDate);
            Instant instant = Instant.ofEpochMilli(date.getTime());
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return localDateTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }


    public static void main(String[] args) {
        DateFormatUtil dateFormatUtil = new DateFormatUtil();
        System.out.println(dateFormatUtil.getLocalDateTime("2017-04-04","yyyy-MM-dd"));

    }
}
