package fix.connection;

import harness.LoggerFactory;
import org.slf4j.Logger;
import quickfix.*;

import static java.lang.invoke.MethodHandles.lookup;

class FixConnectionManager {

    private static final Logger LOGGER = LoggerFactory.newLogger(lookup().lookupClass());
    private final Connector connector;

    FixConnectionManager(
            Connector connector){
        this.connector = connector;
    }

    void connect() {
        try{
            connector.start();
        } catch (ConfigError e) {
            LOGGER.error("Error connecting over fix <{}>",connector, e);
            throw new RuntimeException(e);
        }

    }
}
