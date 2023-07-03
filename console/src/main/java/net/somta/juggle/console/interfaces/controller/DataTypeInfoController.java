package net.somta.juggle.console.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;
import net.somta.juggle.console.application.service.IDataTypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

@Tag(name = "数据类型接口（已完成）")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/dataType")
public class DataTypeInfoController {

    @Autowired
    private IDataTypeInfoService dataTypeInfoService;

    /**
     * 获取API列表
     * @return Boolean
     */
    @Operation(summary = "获取数据类型下拉列表")
    @PostMapping("/list")
    public ResponseDataResult<List<DataTypeOptionDTO>> getDataTypeOptions(){
        List<DataTypeOptionDTO> list = dataTypeInfoService.getDataTypeOptions();
        return ResponseDataResult.setResponseResult(list);
    }

}
