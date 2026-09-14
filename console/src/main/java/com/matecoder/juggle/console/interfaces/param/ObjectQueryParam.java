package com.matecoder.juggle.console.interfaces.param;

import com.matecoder.core.base.page.PageParam;

/**
 * @author husong
 */
public class ObjectQueryParam extends PageParam {

    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
