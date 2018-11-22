package fix.connection;

import harness.LoggerFactory;
import org.slf4j.Logger;
import quickfix.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.invoke.MethodHandles.lookup;

class FixConnectionManager {

    private static final Logger LOGGER = LoggerFactory.newLogger(lookup().lookupClass());
    private final Connector connector;

    FixConnectionManager(
            Connector connector){
        this.connector = connector;
    }

    Collection<SessionID> connect() {
        try{
            connector.start();
        } catch (ConfigError e) {
            LOGGER.error("Error connecting over fix <{}>",connector, e);
            throw new RuntimeException(e);
        }

        return connector.getSessions();
    }
}
