package com.matecoder.juggle.console.interfaces.param.user;

/**
 * @author husong
 */
public class UpdatePasswordParam {
    private String oldSecret;
    private String newSecret;

    public String getOldSecret() {
        return oldSecret;
    }

    public void setOldSecret(String oldSecret) {
        this.oldSecret = oldSecret;
    }

    public String getNewSecret() {
        return newSecret;
    }

    public void setNewSecret(String newSecret) {
        this.newSecret = newSecret;
    }
}
