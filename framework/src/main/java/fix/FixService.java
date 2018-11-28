package fix;

import dispatch.Dispatcher;
import harness.LoggerFactory;
import org.slf4j.Logger;
import quickfix.*;
import transform.MessageToEventTransformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.invoke.MethodHandles.lookup;

public class FixService implements Application {

    private static final Logger LOGGER = LoggerFactory.newLogger(lookup().lookupClass());

    private final Dispatcher messageDispatcher;
    private final MessageToEventTransformer messageToEventTransformer;
    private final Map<String, SessionID> sessions = new ConcurrentHashMap<>();
    private final Collection<MessageListener> messageListeners = new ArrayList<>();

    public FixService(Dispatcher messageDispatcher,
                      MessageToEventTransformer messageToEventTransformer){
        this.messageToEventTransformer = messageToEventTransformer;
        this.messageDispatcher = messageDispatcher;
    }

    @Override
    public void onCreate(SessionID sessionId) {
        LOGGER.debug("onCreate <{}>", sessionId);
    }

    @Override
    public void onLogon(SessionID sessionId) {
        LOGGER.debug("onLogon <{}>", sessionId);
    }

    @Override
    public void onLogout(SessionID sessionId) {
        LOGGER.debug("onLogout <{}>", sessionId);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        LOGGER.debug("toAdmin <{}>", sessionId);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        LOGGER.debug("fromAdmin <{}>, <{}>", message, sessionId);
    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        LOGGER.debug("toApp <{}>, <{}>", message, sessionId);
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        LOGGER.debug("fromApp <{}>, <{}>", message, sessionId);

        for(MessageListener listener : messageListeners){
            listener.onReceived(message);
        }

        //message.getHeader();
        int dispatchKey = 0;
        Object pojo = null;
        Object key = null;

        //messageDispatcher.dispatch(key, pojo);

        //Event event = messageToEventTransformer.transform(message);


    }

    public void send(Message message, String connectionId) {
        try {
            Session.sendToTarget(message, sessions.get(connectionId));
        } catch (SessionNotFound e) {
            throw new RuntimeException("Error sending.", e);
        }
    }

    public void addMessageListener(MessageListener messageListener) {
        this.messageListeners.add(messageListener);
    }
}
