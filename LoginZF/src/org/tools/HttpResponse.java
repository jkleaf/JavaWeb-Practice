package org.tools;

public class HttpResponse {

    private int status;

    private String response;

    public HttpResponse(int code, String res) {
        status   = code;
        response = res;
    }

    public int getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }
}
