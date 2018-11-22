package comunication.receiver.trade;

import pojo.QuoteRequest;
import pojo.TradeRequest;

public interface TradeRequestReceiverListener {

    void onReceived(TradeRequest quoteRequest);

    void onCanceled(String quoteRequestId);

}
