package fix.connection;

import harness.LoggerFactory;
import org.slf4j.Logger;
import quickfix.*;
import sun.security.krb5.Config;

import java.io.InputStream;

import static java.lang.invoke.MethodHandles.lookup;

public class FixConnectionAcceptorManager {

    private static final Logger LOGGER = LoggerFactory.newLogger(lookup().lookupClass());

    private final Application fixService;
    private final InputStream fileInputStream;

    private FixConnectionManager connectionManager;
    private SocketAcceptor acceptor;

    public FixConnectionAcceptorManager(InputStream fileInputStream,
                                        Application fixService){
        this.fileInputStream = fileInputStream;
        this.fixService = fixService;
    }

    public void connect(){

        SessionSettings settings = null;
        try {
            settings = new SessionSettings(fileInputStream);

            acceptor = new SocketAcceptor(
                    fixService,
                    new FileStoreFactory(settings),
                    settings,
                    new ScreenLogFactory(settings),
                    new DefaultMessageFactory());

            this.connectionManager = new FixConnectionManager(acceptor);
            this.connectionManager.connect();

        }catch (ConfigError e){
            LOGGER.error("Error connecting over fix InputStream <{}> Settings <{}>",
                    fileInputStream, settings, e);
            throw new RuntimeException(e);
        }
    }

}
