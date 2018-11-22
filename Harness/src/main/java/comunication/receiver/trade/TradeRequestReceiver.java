package comunication.receiver.trade;

import pojo.TradeRequest;

public interface TradeRequestReceiver {

    void addQuoteReceiverListener(TradeRequestReceiverListener tradeRequestReceiverListener);

    void reject(TradeRequest tradeRequest);
}
