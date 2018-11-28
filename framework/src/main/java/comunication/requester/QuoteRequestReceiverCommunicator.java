package comunication.requester;

import comunication.receiver.quote.request.QuoteRequestReceiver;
import comunication.receiver.quote.request.QuoteRequestReceiverListener;
import fix.FixService;
import fix.MessageListener;
import fix.transform.from.pojo.QuoteRejectTransformer;
import pojo.QuoteReject;
import quickfix.Message;

import java.util.ArrayList;
import java.util.Collection;

public class QuoteRequestReceiverCommunicator implements QuoteRequestReceiver, MessageListener {

    private final FixService fixService;
    private final Collection<QuoteRequestReceiverListener> quoteRequestReceiverListeners = new ArrayList<>();
    private final QuoteRejectTransformer quoteRejectTransformer;

    public QuoteRequestReceiverCommunicator(FixService fixService, QuoteRejectTransformer quoteRejectTransformer) {
        this.fixService = fixService;
        this.quoteRejectTransformer = quoteRejectTransformer;
    }

    @Override
    public void addQuoteReceiverListener(QuoteRequestReceiverListener quoteRequestReceiverListener) {
        quoteRequestReceiverListeners.add(quoteRequestReceiverListener);
    }

    @Override
    public void reject(QuoteReject quoteReject) {

        Message quoteRejectMessage = quoteRejectTransformer.apply(quoteReject);

        fixService.send(
            quoteRejectMessage,
            quoteReject.getConnectionId());
    }

    @Override
    public void onReceived(Message message) {

        for(QuoteRequestReceiverListener listener : quoteRequestReceiverListeners){
            listener.onReceived(null);
        }

    }
}
