package fix.transform.from.pojo;

//import pojo.QuoteRequest;
import pojo.QuoteRequest;
import quickfix.Message;

import java.util.function.Function;

public class QuoteRequestTransformer implements Function<QuoteRequest, Message> {
    @Override
    public Message apply(QuoteRequest quoteRequest) {
        return new Message();
    }
}
