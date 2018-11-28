package harness;

import org.slf4j.Logger;

public class LoggerFactory {

    public static Logger newLogger(Class clazz){
        return org.slf4j.LoggerFactory.getLogger(clazz);
    }

}
