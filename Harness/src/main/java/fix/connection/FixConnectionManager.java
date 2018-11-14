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
/*
            FileStoreFactory storeFactory = new FileStoreFactory(settings);
            LogFactory logFactory = new ScreenLogFactory(settings);

            SocketAcceptor acceptor = new SocketAcceptor(
                    fixService,
                    storeFactory,
                    settings,
                    logFactory,
                    new DefaultMessageFactory());

            SocketInitiator initiator = new SocketInitiator(
                    fixService,
                    storeFactory,
                    settings,
                    logFactory,
                    new DefaultMessageFactory());

            acceptor.start();
            initiator.start();
*/
            connector.start();

        } catch (ConfigError e) {
            LOGGER.error("Error connecting over fix <{}>",connector, e);
            throw new RuntimeException(e);
        }

    }
}
