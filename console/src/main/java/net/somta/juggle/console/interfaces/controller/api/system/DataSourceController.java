package net.somta.juggle.console.interfaces.controller.api.system;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.system.IDataSourceService;
import net.somta.juggle.console.interfaces.dto.system.DataSourceDTO;
import net.somta.juggle.console.interfaces.param.system.DataSourceAddParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceQueryParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceUpdateParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_API_PREFIX;
import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

@Tag(name = "数据源接口")
@RestController
@RequestMapping(JUGGLE_API_PREFIX + "/datasource")
public class DataSourceController {

    private final IDataSourceService dataSourceService;

    public DataSourceController(IDataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    @Operation(summary = "新增数据源")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addDataSource(@RequestBody DataSourceAddParam dataSourceAddParam){
        return ResponseDataResult.setResponseResult(dataSourceService.addDataSource(dataSourceAddParam));
    }

    @Operation(summary = "根据ID删除数据源")
    @DeleteMapping("/delete/{dataSourceId}")
    public ResponseDataResult<Boolean> deleteDataSource(@PathVariable Long dataSourceId){
        dataSourceService.deleteDataSource(dataSourceId);
        return ResponseDataResult.setResponseResult(true);
    }

    @Operation(summary = "修改数据源")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateDataSource(@RequestBody DataSourceUpdateParam dataSourceUpdateParam){
        return ResponseDataResult.setResponseResult(dataSourceService.updateDataSource(dataSourceUpdateParam));
    }

    @Operation(summary = "查询数据源详情")
    @GetMapping("/info/{dataSourceId}")
    public ResponseDataResult<DataSourceDTO> getDataSource(@PathVariable Long dataSourceId){
        return ResponseDataResult.setResponseResult(dataSourceService.getDataSource(dataSourceId));
    }

    @Operation(summary = "查询数据源列表")
    @GetMapping("/list")
    public ResponseDataResult<List<DataSourceDTO>> getAllDataSourceList(){
        return ResponseDataResult.setResponseResult(dataSourceService.getAllDataSourceList());
    }

    @Operation(summary = "查询数据源分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<DataSourceDTO> getDataSourcePageList(@RequestBody DataSourceQueryParam dataSourceQueryParam){
        PageInfo pageInfo = dataSourceService.getDataSourcePageList(dataSourceQueryParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Operation(summary = "连接数据源")
    @GetMapping("/connect/{dataSourceId}")
    public ResponseDataResult<Boolean> connectDataSource(@PathVariable Long dataSourceId){
        return ResponseDataResult.setResponseResult(dataSourceService.connectDataSource(dataSourceId));
    }
}
