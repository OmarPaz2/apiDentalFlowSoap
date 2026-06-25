package com.dentalflow.pe.security.soap;

import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JwtSoapHandler implements SOAPHandler<SOAPMessageContext> {

    private final SoapSecurityHelper securityHelper;

    public JwtSoapHandler(SoapSecurityHelper securityHelper) {
        this.securityHelper = securityHelper;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        Boolean outbound =
                (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (!outbound) {

            Map<String, List<String>> headers =
                    (Map<String, List<String>>)
                            context.get(MessageContext.HTTP_REQUEST_HEADERS);

            if (headers != null) {

                List<String> authHeaders =
                        headers.get("Authorization");

                if (authHeaders != null && !authHeaders.isEmpty()) {

                    securityHelper.authenticate(
                            authHeaders.get(0)
                    );
                }
            }
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        securityHelper.clear();
        return true;
    }

    @Override
    public void close(MessageContext context) {
        securityHelper.clear();
    }

    @Override
    public Set getHeaders() {
        return null;
    }
}