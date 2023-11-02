package my.backendproductioncode;

public class InstallationRequest {
    private int requestId;
    private String description;
    private String nameProdect;

    public InstallationRequest(int requestId,String nameProdect, String description) {
        this.requestId = requestId;
        this.description = description;
        this.nameProdect = nameProdect;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getDescription() {
        return description;
    }
}
