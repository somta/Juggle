package net.somta.juggle.console.web.controller;

import net.somta.juggle.console.model.param.VariableParam;
import net.somta.juggle.console.service.IVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VariableController {

    @Autowired
    private IVariableService variableService;

    /**
     * 新增变量
     * @param variableParam 变量实体参数
     * @return Boolean
     */
    @PostMapping("/addVariable")
    public Boolean addVariable(@RequestBody VariableParam variableParam){
        variableService.addVariable();
        return null;
    }

}
