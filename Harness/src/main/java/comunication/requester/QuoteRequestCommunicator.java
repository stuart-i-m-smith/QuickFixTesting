package comunication.requester;

import comunication.receiver.quote.QuoteReceiver;
import comunication.receiver.quote.QuoteReceiverListener;
import comunication.requester.quote.QuoteRequestListener;
import comunication.requester.quote.QuoteRequester;
import fix.FixService;
import fix.MessageListener;
import fix.transform.from.pojo.QuoteRequestTransformer;
import pojo.QuoteRequest;
import quickfix.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuoteRequestCommunicator implements QuoteRequester {


    private final FixService fixService;
    private final QuoteRequestTransformer quoteRequestTransformer;

    private final Map<String, QuoteRequestListener> requestListeners = new ConcurrentHashMap<>();
    private final Map<String, QuoteRequestListener> cancelListeners = new ConcurrentHashMap<>();

    public QuoteRequestCommunicator(FixService fixService,
                                    QuoteRequestTransformer quoteRequestTransformer) {
        this.fixService = fixService;
        this.quoteRequestTransformer = quoteRequestTransformer;

    }

    @Override
    public void request(QuoteRequest quoteRequest, QuoteRequestListener quoteListener) {

        Message quoteRequestMessage = quoteRequestTransformer.apply(quoteRequest);

        requestListeners.put(quoteRequest.getConnectionId(), quoteListener);

        fixService.send(quoteRequestMessage, quoteRequest.getConnectionId());
    }

    @Override
    public void cancel(String quoteRequestId, QuoteRequestListener quoteListener) {

    }
}
