package fix;

import quickfix.Message;

public interface MessageListener {

    void onReceived(Message message);
}
