package comunication.requester.quote;

import pojo.QuoteRequest;

public interface QuoteRequester {

    void request(QuoteRequest quoteRequest, QuoteRequestListener quoteListener);

    void cancel(String quoteRequestId, QuoteRequestListener quoteListener);
}
