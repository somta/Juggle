package net.somta.juggle.client;

/**
 * @author husong
 * @since 1.2.0
 */
public class JuggleFactory {
    private static volatile JuggleClient juggleClient;

    public static JuggleClient getClientInstance(JuggleConfig juggleConfig) {
        if (juggleClient == null) {
            synchronized (JuggleClient.class) {
                if (juggleClient == null) {
                    juggleClient = new JuggleClientImpl(juggleConfig);
                }
            }
        }
        return juggleClient;
    }
}
