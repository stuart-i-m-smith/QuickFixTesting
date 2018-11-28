package pojo;

public class QuoteRequest {
    public String getConnectionId() {
        return "connectionId";
    }

    public long getId(){
        return 123l;
    }

    public String getCcy1(){
        return "EUR";
    }

    public String getCcy2(){
        return "GBP";
    }

    public String getInstrumentType(){
        return "SPOT";
    }
}
