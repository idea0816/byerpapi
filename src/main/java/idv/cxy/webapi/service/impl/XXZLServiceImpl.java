package idv.cxy.webapi.service.impl;

import idv.cxy.webapi.dao.XXZLDao;
import idv.cxy.webapi.service.XXZLService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2023/4/11
 * @Description 型体資料
 *
 * 取得ARTICLEs
 */
@Component
public class XXZLServiceImpl implements XXZLService {
    @Resource
    private XXZLDao xxzlDao;

    // 取得ARTICLEs
    @Override
    public List<String> getARTICLEs() {
        return xxzlDao.getARTICLEs();
    }
}
