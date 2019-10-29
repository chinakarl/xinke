package com.xinke.management.entity.beans;

import java.io.Serializable;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */
public class MenuMeta implements Serializable {

    private boolean keepAlive;
    private boolean requireAuth;

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
