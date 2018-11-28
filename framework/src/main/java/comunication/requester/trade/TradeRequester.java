package comunication.requester.trade;

import pojo.TradeRequest;

public interface TradeRequester {

    void request(TradeRequest tradeRequest, TradeRequestListener tradeListener);

    void cancel(String tradeRequestId, TradeRequestListener tradeListener);
}
