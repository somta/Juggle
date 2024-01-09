package net.somta.juggle.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetUtil {


    /**
     * 获取本机IP地址
     * @return local ip address
     */
    public static String getLocalIP() {
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();
            return ipAddress;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}
