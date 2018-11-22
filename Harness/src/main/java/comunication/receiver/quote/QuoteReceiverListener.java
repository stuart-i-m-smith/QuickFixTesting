package comunication.receiver.quote;

import pojo.QuoteRequest;

public interface QuoteReceiverListener {

    void onReceived(QuoteRequest quoteRequest);

    void onCanceled(String quoteRequestId);

}
