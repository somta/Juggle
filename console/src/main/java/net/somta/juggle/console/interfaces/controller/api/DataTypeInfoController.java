/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.interfaces.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.interfaces.dto.DataTypeOptionDTO;
import net.somta.juggle.console.application.service.IDataTypeInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_API_PREFIX;
import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 * @since 1.0.0
 */
@Tag(name = "数据类型接口")
@RestController
@RequestMapping(JUGGLE_API_PREFIX + "/dataType")
public class DataTypeInfoController {

    private final IDataTypeInfoService dataTypeInfoService;

    public DataTypeInfoController(IDataTypeInfoService dataTypeInfoService) {
        this.dataTypeInfoService = dataTypeInfoService;
    }

    /**
     * 获取数据类型下拉列表
     * @return Boolean
     */
    @Operation(summary = "获取数据类型下拉列表")
    @GetMapping("/list")
    public ResponseDataResult<List<DataTypeOptionDTO>> getDataTypeOptions(){
        List<DataTypeOptionDTO> list = dataTypeInfoService.getDataTypeOptions();
        return ResponseDataResult.setResponseResult(list);
    }

}
