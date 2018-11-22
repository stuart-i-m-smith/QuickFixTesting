package comunication.receiver.quote;

import pojo.QuoteReject;

public interface QuoteReceiver {

    void addQuoteReceiverListener(QuoteReceiverListener quoteReceiverListener);

    void reject(QuoteReject quoteReject);
}
