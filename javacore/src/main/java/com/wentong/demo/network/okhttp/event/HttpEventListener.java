package com.wentong.demo.network.okhttp.event;

import okhttp3.*;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class HttpEventListener extends EventListener {

    static final AtomicLong CALLER = new AtomicLong();
    private final long callId;
    private long dnsStartTime;

    private HttpUrl url;

    public HttpEventListener(HttpUrl url) {
        callId = CALLER.getAndAdd(1);
        this.url = url;
    }

    @Override
    public void dnsStart(Call call, String domainName) {
        super.dnsStart(call, domainName);
        this.dnsStartTime = System.nanoTime();
        System.out.println("HttpEventListener.dnsStart");
    }

    @Override
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        super.dnsEnd(call, domainName, inetAddressList);
        System.out.println("url： " + url.host() + ", Dns time: " + (System.nanoTime() - dnsStartTime) + "ns"); //计算dns解析的时间
        System.out.println("HttpEventListener.dnsEnd");
    }

    @Override
    public void callStart(Call call) {
        super.callStart(call);
        System.out.println("HttpEventListener.callStart");
    }

    @Override
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        super.connectStart(call, inetSocketAddress, proxy);
        System.out.println("HttpEventListener.connectStart");
    }

    @Override
    public void secureConnectStart(Call call) {
        super.secureConnectStart(call);
        System.out.println("HttpEventListener.secureConnectStart");
    }

    @Override
    public void secureConnectEnd(Call call, @Nullable Handshake handshake) {
        super.secureConnectEnd(call, handshake);
        System.out.println("HttpEventListener.secureConnectEnd");
    }

    @Override
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol);
        System.out.println("HttpEventListener.connectEnd");
    }

    @Override
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol, IOException ioe) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, ioe);
        System.out.println("HttpEventListener.connectFailed");
    }

    @Override
    public void connectionAcquired(Call call, Connection connection) {
        super.connectionAcquired(call, connection);
        System.out.println("HttpEventListener.connectionAcquired");
    }

    @Override
    public void connectionReleased(Call call, Connection connection) {
        super.connectionReleased(call, connection);
        System.out.println("HttpEventListener.connectionReleased");
    }

    @Override
    public void requestHeadersStart(Call call) {
        super.requestHeadersStart(call);
        System.out.println("HttpEventListener.requestHeadersStart");
    }

    @Override
    public void requestHeadersEnd(Call call, Request request) {
        super.requestHeadersEnd(call, request);
        System.out.println("HttpEventListener.requestHeadersEnd");
    }

    @Override
    public void requestBodyStart(Call call) {
        super.requestBodyStart(call);
        System.out.println("HttpEventListener.requestBodyStart");
    }

    @Override
    public void requestBodyEnd(Call call, long byteCount) {
        super.requestBodyEnd(call, byteCount);
        System.out.println("HttpEventListener.requestBodyEnd");
    }

    @Override
    public void requestFailed(Call call, IOException ioe) {
        super.requestFailed(call, ioe);
        System.out.println("HttpEventListener.requestFailed");
    }

    @Override
    public void responseHeadersStart(Call call) {
        super.responseHeadersStart(call);
        System.out.println("HttpEventListener.responseHeadersStart");
    }

    @Override
    public void responseHeadersEnd(Call call, Response response) {
        super.responseHeadersEnd(call, response);
        System.out.println("HttpEventListener.responseHeadersEnd");
    }

    @Override
    public void responseBodyStart(Call call) {
        super.responseBodyStart(call);
        System.out.println("HttpEventListener.responseBodyStart");
    }

    @Override
    public void responseBodyEnd(Call call, long byteCount) {
        super.responseBodyEnd(call, byteCount);
        System.out.println("HttpEventListener.responseBodyEnd");
    }

    @Override
    public void responseFailed(Call call, IOException ioe) {
        super.responseFailed(call, ioe);
        System.out.println("HttpEventListener.responseFailed");
    }

    @Override
    public void callEnd(Call call) {
        super.callEnd(call);
        System.out.println("HttpEventListener.callEnd");
    }

    @Override
    public void callFailed(Call call, IOException ioe) {
        super.callFailed(call, ioe);
        System.out.println("HttpEventListener.callFailed");
    }
}
