package comunication.requester.quote;

import pojo.Quote;
import pojo.QuoteCancel;
import pojo.QuoteReject;

public interface QuoteRequestListener {

    void onReceived(Quote quote);

    void onCancelled(QuoteCancel quoteCancel);

    void onRejected(QuoteReject quoteReject);
}