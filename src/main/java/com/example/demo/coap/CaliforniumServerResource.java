package com.example.demo.coap;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.stereotype.Component;

@Component
public class CaliforniumServerResource extends CoapResource {

    public CaliforniumServerResource() {
        super("hello");
    }

    public CaliforniumServerResource(String name) {
        super(name);
        getAttributes().setTitle(name);
    }

    public CaliforniumServerResource(String name, boolean visible) {
        super(name, visible);
        getAttributes().setTitle(name);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exchange.respond("hello");
    }
}
