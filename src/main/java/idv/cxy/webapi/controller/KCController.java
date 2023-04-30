package idv.cxy.webapi.controller;

import idv.cxy.webapi.model.KSYD;
import idv.cxy.webapi.service.KSYDService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/9/12
 * @Description 庫存作業
 * <p>
 * 取得入出庫單表頭
 * 新增入出庫單(含表頭、表身)
 * 修改入出庫單(表身)
 * 刪除入出庫單表頭
 * 領料分配到訂單並回寫LBY_ERP
 */

@Tag(name = "庫存作業")
@RestController
@CrossOrigin
public class KCController {

    @Resource
    // 将@Autowired注解替换为@Resource注解
    private KSYDService ksydService;

    // 取得入出庫單表頭
    @Operation(summary = "入出庫表頭", description = "依日期區間、取得入出庫表頭")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = KSYD.class))
                            )
                    })
//            @ApiResponse(responseCode = "401", description = "參數錯誤或沒有權限", content = {
//                    @Content()
//            })
    })
    @GetMapping("/kc/getKSYD/{StarDate}/{EndDate}")
    public List<KSYD> getKSYD(@PathVariable String StarDate, @PathVariable String EndDate) {
        return ksydService.getKSYD(StarDate, EndDate);
    }

    // 新增入出庫單(含表頭、表身)
    @Operation(summary = "新增入出庫單(含表頭、表身)", description = "新增入出庫單(含表頭、表身)")
    @PostMapping("/kc/insKSYD")
    public String insKSYD() {
        return "success";
    }

    // 修改入出庫單(表身)
    @Operation(summary = "修改入出庫單(表身)", description = "修改入出庫單(表身)")
    @PutMapping("/kc/updKSYDS")
    public String updKSYDS() {
        return "success";
    }

    // 刪除入出庫單表頭
    @Operation(summary = "刪除入出庫表頭", description = "依單號刪除入出庫表頭;表身同時刪除(全刪)")
    @DeleteMapping("/kc/delKSYD/{KSDH}")
    public String delKSYS(@PathVariable String KSDH) {
        return "success";
    }

    // 領料分配到訂單並回寫LBY_ERP
    @Operation(summary = "領料分配到訂單並回寫LBY_ERP", description = "傳入資料格式範例：localhost:9090/kc/setAllocateDDZL/202301")
    @PostMapping("/kc/setAllocateDDZL/{selectYM}")
    public String setAllocateDDZL(@PathVariable String selectYM) {
        ksydService.setAllocateDDZL(selectYM);
        return "success";
    }


}
