package comunication.receiver.quote.request;

import pojo.QuoteReject;

public interface QuoteRequestReceiver {

    void addQuoteReceiverListener(QuoteRequestReceiverListener quoteRequestReceiverListener);

    void reject(QuoteReject quoteReject);
}
