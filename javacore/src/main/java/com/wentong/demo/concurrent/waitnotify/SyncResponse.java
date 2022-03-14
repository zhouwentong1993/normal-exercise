package com.wentong.demo.concurrent.waitnotify;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class SyncResponse {

    private Map<String, Response> container = new ConcurrentHashMap<>();

    public Response createResponse(String id) {
        Response response = new Response(id);
        container.put(id, response);
        return response;
    }
}
