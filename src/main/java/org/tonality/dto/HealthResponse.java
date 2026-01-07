package org.tonality.dto;

public class HealthResponse {
    private String status;
    private String service;
    private String version;

    public HealthResponse(String status, String service, String version) {
        this.status = status;
        this.service = service;
        this.version = version;
    }

    public String getStatus() { return status; }
    public String getService() { return service; }
    public String getVersion() { return version; }

    public void setStatus(String status) { this.status = status; }
    public void setService(String service) { this.service = service; }
    public void setVersion(String version) { this.version = version; }
}