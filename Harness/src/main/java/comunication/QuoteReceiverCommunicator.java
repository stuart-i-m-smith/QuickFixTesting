package comunication;

import comunication.receiver.quote.QuoteReceiver;
import comunication.receiver.quote.QuoteReceiverListener;
import fix.FixService;
import fix.MessageListener;
import fix.transform.from.pojo.QuoteRejectTransformer;
import pojo.QuoteReject;
import quickfix.Message;

import java.util.ArrayList;
import java.util.Collection;

public class QuoteReceiverCommunicator implements QuoteReceiver, MessageListener {

    private final FixService fixService;
    private final Collection<QuoteReceiverListener> quoteReceiverListeners = new ArrayList<>();
    private final QuoteRejectTransformer quoteRejectTransformer;

    public QuoteReceiverCommunicator(FixService fixService, QuoteRejectTransformer quoteRejectTransformer) {
        this.fixService = fixService;
        this.quoteRejectTransformer = quoteRejectTransformer;
    }

    @Override
    public void addQuoteReceiverListener(QuoteReceiverListener quoteReceiverListener) {
        quoteReceiverListeners.add(quoteReceiverListener);
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

        for(QuoteReceiverListener listener : quoteReceiverListeners){
            listener.onReceived(null);
        }

    }
}
