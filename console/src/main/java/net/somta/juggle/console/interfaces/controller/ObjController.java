package net.somta.juggle.console.interfaces.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.IApiService;
import net.somta.juggle.console.application.service.IObjService;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import net.somta.juggle.console.interfaces.dto.ObjDTO;
import net.somta.juggle.console.interfaces.dto.ObjInfoDTO;
import net.somta.juggle.console.interfaces.param.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 */
@Tag(name = "对象接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/obj")
public class ObjController {

    private final IObjService objService;

    public ObjController(IObjService objService) {
        this.objService = objService;
    }

    @Operation(summary = "新增对象")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addObj(@RequestBody ObjAddParam objAddParam){
        objService.addObj(objAddParam);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "根据ID删除对象")
    @DeleteMapping("/delete/{objId}")
    public ResponseDataResult<Boolean> deleteObj(@PathVariable Long objId){
        objService.deleteObj(objId);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "修改对象")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateObj(@RequestBody ObjUpdateParam objUpdateParam){
        objService.updateObj(objUpdateParam);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "查询对象详情")
    @GetMapping("/info/{objId}")
    public ResponseDataResult<ObjInfoDTO> getObj(@PathVariable Long objId){
        ObjInfoDTO objInfoDto = objService.getObjInfo(objId);
        return ResponseDataResult.setResponseResult(objInfoDto);
    }

    @Operation(summary = "根据对象列表")
    @PostMapping("/list")
    public ResponseDataResult<List<ObjInfoDTO>> getObjList(){
        List<ObjInfoDTO> objList = objService.getObjList();
        return ResponseDataResult.setResponseResult(objList);
    }

    @Operation(summary = "查询对象分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<List<ObjDTO>> getObjPageList(@RequestBody ObjQueryParam objQueryParam){
        PageInfo pageInfo = objService.getObjPageList(objQueryParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }

}
