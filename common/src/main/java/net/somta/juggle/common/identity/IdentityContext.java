package net.somta.juggle.common.identity;

/**
 * @author husong
 */
public class IdentityContext {
    private static ThreadLocal<IdentityVO> identityThreadLocal = new ThreadLocal<>();

    public static void setIdentity(IdentityVO identity) {
        identityThreadLocal.set(identity);
    }

    public static IdentityVO getIdentity() {
        return identityThreadLocal.get();
    }

    public static void clearIdentity() {
        identityThreadLocal.remove();
    }

}