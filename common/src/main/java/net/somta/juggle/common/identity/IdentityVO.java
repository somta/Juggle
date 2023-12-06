package net.somta.juggle.common.identity;

/**
 * @author husong
 */
public class IdentityVO {
    public static final String USER_ID = "userId";

    private Long userId;

    public IdentityVO(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
