package idv.cxy.webapi.service;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 時間控制
 */

public interface timeService {
    // 現在年月日
    String now();

    // 現在年月
    String nowYM();

    // 現在年月 -1
    String lastNowYM();

    // 轉成Web顯示的日期
    String forWebDate(String webDate);

    // Date轉String
    String date2String(String date);
}
