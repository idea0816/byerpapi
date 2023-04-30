package idv.cxy.webapi.controller;

import idv.cxy.webapi.service.XXZLService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2023/4/11
 * @Description 订单鞋型合并
 *
 * 取得ARTICLEs
 */

@Tag(name = "鞋型資料")
@RestController
@CrossOrigin
public class XXZLController {
    @Resource
    private XXZLService xxzlService;

    // 取得ARTICLEs
    @Operation(summary = "取得ARTICLE", description = "取得ARTICLE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json"

                            )
                    })
    })
    @GetMapping("/zlzl/getARTICLEs")
    public List<String> getARTICLEs() {
        return xxzlService.getARTICLEs();
    }
}
