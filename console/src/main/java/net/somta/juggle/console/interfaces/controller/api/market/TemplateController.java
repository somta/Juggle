package net.somta.juggle.console.interfaces.controller.api.market;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.market.ITemplateService;
import net.somta.juggle.console.interfaces.dto.market.TemplateMarketClassifyDTO;
import net.somta.juggle.console.interfaces.dto.market.TemplateMarketDTO;
import net.somta.juggle.console.interfaces.dto.market.TemplateMarketInfoDTO;
import net.somta.juggle.console.interfaces.param.market.TemplateMarketParam;
import net.somta.juggle.console.interfaces.param.market.TemplateMarketQueryParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_API_PREFIX;
import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

@Tag(name = "模板接口")
@RestController
@RequestMapping(JUGGLE_API_PREFIX + "/template")
public class TemplateController {

    private final ITemplateService templateService;

    public TemplateController(ITemplateService templateService) {
        this.templateService = templateService;
    }

    @Operation(summary = "查询市场模板分类列表")
    @PostMapping("/market/classify")
    public ResponseDataResult<List<TemplateMarketClassifyDTO>> getTemplateMarketClassifyList(){
        List<TemplateMarketClassifyDTO> list = templateService.getTemplateMarketClassifyList();
        return ResponseDataResult.setResponseResult(list);
    }

    @Operation(summary = "查询市场模板列表")
    @PostMapping("/market")
    public ResponsePaginationDataResult<TemplateMarketDTO> getTemplateMarketList(@RequestBody TemplateMarketQueryParam templateMarketQueryParam){
        return templateService.getTemplateMarketList(templateMarketQueryParam);
    }

    @Operation(summary = "查询市场模板详情")
    @GetMapping("/market/info/{templateId}")
    public ResponseDataResult<TemplateMarketInfoDTO> getTemplateMarketInfo(@PathVariable Long templateId){
        TemplateMarketInfoDTO templateMarketInfoDto = templateService.getTemplateMarketInfo(templateId);
        return ResponseDataResult.setResponseResult(templateMarketInfoDto);
    }

    @Operation(summary = "使用模板")
    @PostMapping("/market/use")
    public ResponseDataResult<Boolean> useTemplateMarket(@RequestBody TemplateMarketParam templateMarketParam){
        templateService.useTemplateMarket(templateMarketParam);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "查询市场模板推荐模板列表")
    @GetMapping("/market/recommend/{templateId}")
    public ResponseDataResult<List<TemplateMarketDTO>> getRecommendTemplateList(@PathVariable Long templateId){
        List<TemplateMarketDTO> list = templateService.getRecommendTemplateList(templateId);
        return ResponseDataResult.setResponseResult(list);
    }

}
