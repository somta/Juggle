package net.somta.juggle.console.interfaces.controller.template;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.template.ITemplateService;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketClassifyDTO;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketDTO;
import net.somta.juggle.console.interfaces.dto.template.TemplateMarketInfoDTO;
import net.somta.juggle.console.interfaces.param.template.TemplateMarketParam;
import net.somta.juggle.console.interfaces.param.template.TemplateMarketQueryParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

@Tag(name = "模板接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/template")
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
    @GetMapping("/market/info/{suiteId}")
    public ResponseDataResult<TemplateMarketInfoDTO> getTemplateMarketInfo(@PathVariable Long suiteId){
        TemplateMarketInfoDTO templateMarketInfoDto = templateService.getTemplateMarketInfo(suiteId);
        return ResponseDataResult.setResponseResult(templateMarketInfoDto);
    }

    @Operation(summary = "使用模板")
    @PostMapping("/market/use")
    public ResponseDataResult<Boolean> useTemplateMarket(@RequestBody TemplateMarketParam templateMarketParam){
        templateService.useTemplateMarket(templateMarketParam);
        return ResponseDataResult.setResponseResult();
    }


}
