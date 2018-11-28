package fix.transform.from.pojo;

import pojo.QuoteReject;
import quickfix.Message;

import java.util.function.Function;

public class QuoteRejectTransformer implements Function<QuoteReject, Message> {
    @Override
    public Message apply(QuoteReject quoteReject) {
        return null;
    }
}
