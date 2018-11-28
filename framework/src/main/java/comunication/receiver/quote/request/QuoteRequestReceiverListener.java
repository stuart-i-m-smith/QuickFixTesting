package comunication.receiver.quote.request;

import pojo.QuoteRequest;

public interface QuoteRequestReceiverListener {

    void onReceived(QuoteRequest quoteRequest);

    void onCanceled(String quoteRequestId);

}
