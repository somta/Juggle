package net.somta.juggle.console.web.controller;

import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.model.param.VariableParam;
import net.somta.juggle.console.service.IApiService;
import net.somta.juggle.console.service.IVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private IApiService apiService;

    /**
     * 获取API列表
     * @return Boolean
     */
    @PostMapping("/getApiList")
    public Api getApiList(){
        apiService.getApiList();
        return null;
    }

}
