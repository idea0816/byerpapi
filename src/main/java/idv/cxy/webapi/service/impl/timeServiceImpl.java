package idv.cxy.webapi.service.impl;

import idv.cxy.webapi.service.timeService;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description JDK8 時間控制
 */

@Component
public class timeServiceImpl implements timeService {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate nowDate = LocalDate.now();
    Date date = new Date();

    // 現在年月日
    @Override
    public String now() {
        return nowDate.format(dateFormat);
    }

    // 現在年月
    @Override
    public String nowYM() {
        return nowDate.format(dateFormat).substring(0, 6);
    }

    // 現在年月 -1
    @Override
    public String lastNowYM() {
        return (nowDate.minusMonths(1)).format(dateFormat).substring(0, 6);
    }

    // 轉成Web顯示的日期
    @Override
    public String forWebDate(String webDate) {
        try {
            // 先按照原格式轉化成時間
            date = new SimpleDateFormat("yyyyMMddHHmmss").parse(webDate + "000000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 再將時間轉化成所需字串
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    // Date轉String
    @Override
    public String date2String(String date2String) {
        try {
            // 先按照原格式轉化成時間
            date = new SimpleDateFormat("yyyy-MM-dd").parse(date2String);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 再將時間轉化成所需字串
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }
}
