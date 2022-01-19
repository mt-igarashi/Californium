package com.example.demo.coap;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.elements.config.TcpConfig;
import org.eclipse.californium.elements.config.UdpConfig;
import org.springframework.stereotype.Component;
import org.eclipse.californium.elements.util.NetworkInterfacesUtil;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@Component
public class CaliforniumServer extends CoapServer {

    private static final int COAP_PORT = Configuration.getStandard().get(CoapConfig.COAP_PORT);

    static {
		CoapConfig.register();
		UdpConfig.register();
		TcpConfig.register();
	}

    public CaliforniumServer() {
    }

    public CaliforniumServer(int... ports) {
        super(ports);
    }

    public CaliforniumServer(Configuration config, int... ports) {
        super(config, ports);
    }

    public void addEndpoints() {
        for (InetAddress addr :  NetworkInterfacesUtil.getNetworkInterfaces()) {
            if (addr instanceof Inet4Address || addr.isLoopbackAddress()) {
                InetSocketAddress bindToAddress = new InetSocketAddress(addr, COAP_PORT);
                CoapEndpoint.Builder builder = new CoapEndpoint.Builder();
				builder.setInetSocketAddress(bindToAddress);
				builder.setConfiguration(Configuration.getStandard());
				addEndpoint(builder.build());
            }
        }
    }
}
