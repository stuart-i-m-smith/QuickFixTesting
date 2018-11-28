package harness;

import comunication.receiver.quote.request.QuoteRequestReceiverListener;
import comunication.requester.QuoteRequestReceiverCommunicator;
import comunication.requester.QuoteRequestCommunicator;
import comunication.requester.quote.QuoteRequestListener;
import dispatch.Dispatcher;
import fix.FixService;
import fix.connection.FixConnectionAcceptorManager;
import fix.connection.FixConnectionInitiatorManager;
import org.slf4j.Logger;
import pojo.Quote;
import pojo.QuoteCancel;
import pojo.QuoteReject;
import pojo.QuoteRequest;
import transform.MessageToEventTransformer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static java.lang.invoke.MethodHandles.lookup;

public class Harness {

    private static final Logger LOGGER = LoggerFactory.newLogger(lookup().lookupClass());

    private FixConnectionAcceptorManager serverConnection;
    private FixConnectionInitiatorManager clientConnection;
    private QuoteRequestCommunicator quoteRequestCommunicator;
    private QuoteRequestReceiverCommunicator quoteReceiverCommunicator;


    public static void main(String[] args) throws FileNotFoundException {
        Harness harness = new Harness();
        harness.begin();
        harness.subscribe();

    }

    private void begin() throws FileNotFoundException {

        LOGGER.debug("Current path <{}>", System.getProperty("user.dir"));

        String acceptorSettings = "framework/src/main/resources/acceptor.cfg";
        String initiatorSettings = "framework/src/main/resources/initiator.cfg";

        Dispatcher acceptorMessageDispatcher = new Dispatcher();
        Dispatcher initiatorMessageDispatcher = new Dispatcher();
        MessageToEventTransformer messageToEventTransformer = new MessageToEventTransformer();

        FixService server = new FixService(acceptorMessageDispatcher, messageToEventTransformer);
        FixService client = new FixService(initiatorMessageDispatcher, messageToEventTransformer);

        serverConnection = new FixConnectionAcceptorManager(
                new FileInputStream(acceptorSettings),
                server);

        clientConnection = new FixConnectionInitiatorManager(
                new FileInputStream(initiatorSettings),
                client);

        serverConnection.connect();
        clientConnection.connect();

        quoteRequestCommunicator = new QuoteRequestCommunicator(client, null);
        quoteReceiverCommunicator = new QuoteRequestReceiverCommunicator(server, null);
    }


    private void subscribe() {

        quoteReceiverCommunicator.addQuoteReceiverListener(new QuoteRequestReceiverListener() {
            @Override
            public void onReceived(QuoteRequest quoteRequest) {
                LOGGER.info("Received a quote request <{}>", quoteRequest);


            }

            @Override
            public void onCanceled(String quoteRequestId) {

            }
        });

        quoteRequestCommunicator.request(new QuoteRequest(), new QuoteRequestListener() {
            @Override
            public void onReceived(Quote quote) {

            }

            @Override
            public void onCancelled(QuoteCancel quoteCancel) {

            }

            @Override
            public void onRejected(QuoteReject quoteReject) {

            }
        });




    }
}
