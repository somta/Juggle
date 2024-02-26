package net.somta.juggle.console.interfaces.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.IObjectService;
import net.somta.juggle.console.interfaces.dto.ObjectDTO;
import net.somta.juggle.console.interfaces.dto.ObjectInfoDTO;
import net.somta.juggle.console.interfaces.param.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 */
@Tag(name = "对象接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/object")
public class ObjectController {

    private final IObjectService objectService;

    public ObjectController(IObjectService objectService) {
        this.objectService = objectService;
    }

    @Operation(summary = "新增对象")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addObject(@RequestBody ObjectAddParam objectAddParam){
        objectService.addObject(objectAddParam);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "根据ID删除对象")
    @DeleteMapping("/delete/{objectId}")
    public ResponseDataResult<Boolean> deleteObject(@PathVariable Long objectId){
        objectService.deleteObject(objectId);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "修改对象")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateObject(@RequestBody ObjectUpdateParam objectUpdateParam){
        objectService.updateObject(objectUpdateParam);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "查询对象详情")
    @GetMapping("/info/{objectId}")
    public ResponseDataResult<ObjectInfoDTO> getObject(@PathVariable Long objectId){
        ObjectInfoDTO objectInfoDto = objectService.getObjectInfo(objectId);
        return ResponseDataResult.setResponseResult(objectInfoDto);
    }

    @Operation(summary = "根据对象列表")
    @GetMapping("/list")
    public ResponseDataResult<List<ObjectDTO>> getObjectList(){
        List<ObjectDTO> objList = objectService.getObjectList();
        return ResponseDataResult.setResponseResult(objList);
    }

    @Operation(summary = "查询对象分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<List<ObjectDTO>> getObjectPageList(@RequestBody ObjectQueryParam objectQueryParam){
        PageInfo pageInfo = objectService.getObjectPageList(objectQueryParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }

}
