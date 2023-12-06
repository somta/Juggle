package net.somta.juggle.console.interfaces.param;

import net.somta.core.base.page.PageParam;

/**
 * @author husong
 */
public class ObjQueryParam extends PageParam {

    private String objName;

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }
}
