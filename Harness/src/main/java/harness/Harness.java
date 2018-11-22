package harness;

import dispatch.Dispatcher;
import fix.FixService;
import fix.connection.FixConnectionAcceptorManager;
import fix.connection.FixConnectionInitiatorManager;
import org.slf4j.Logger;
import quickfix.Application;
import transform.MessageToEventTransformer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static java.lang.invoke.MethodHandles.lookup;

public class Harness {

    private static final Logger LOGGER = LoggerFactory.newLogger(lookup().lookupClass());

    private FixConnectionAcceptorManager serverConnection;
    private FixConnectionInitiatorManager clientConnection;
    private Application server;
    private Application client;

    public static void main(String[] args) throws FileNotFoundException {
        Harness harness = new Harness();
        harness.begin();
        harness.subscribe();

    }

    private void begin() throws FileNotFoundException {

        LOGGER.debug("Current path <{}>", System.getProperty("user.dir"));

        String acceptorSettings = "harness/src/main/resources/acceptor.cfg";
        String initiatorSettings = "harness/src/main/resources/initiator.cfg";

        Dispatcher acceptorMessageDispatcher = new Dispatcher();
        Dispatcher initiatorMessageDispatcher = new Dispatcher();
        MessageToEventTransformer messageToEventTransformer = new MessageToEventTransformer();

        server = new FixService(acceptorMessageDispatcher, messageToEventTransformer);
        client = new FixService(initiatorMessageDispatcher, messageToEventTransformer);

        serverConnection = new FixConnectionAcceptorManager(
                new FileInputStream(acceptorSettings),
                server);

        clientConnection = new FixConnectionInitiatorManager(
                new FileInputStream(initiatorSettings),
                client);

        serverConnection.connect();
        clientConnection.connect();
    }


    private void subscribe() {




    }
}
