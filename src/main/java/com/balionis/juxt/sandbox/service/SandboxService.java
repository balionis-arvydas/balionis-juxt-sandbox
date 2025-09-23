package com.balionis.juxt.sandbox.service;

import com.balionis.juxt.sandbox.client.SandboxClient;
import com.balionis.juxt.sandbox.model.SandboxRequest;
import com.balionis.juxt.sandbox.model.SandboxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SandboxService {

    private static final Logger logger = LoggerFactory.getLogger(SandboxService.class);

    private final SandboxClient sandboxClient;

    public SandboxService(SandboxClient sandboxClient) {
        this.sandboxClient = sandboxClient;
    }

    public SandboxResponse doSomething(SandboxRequest sandboxRequest) {
        logger.info("sandboxRequest={}", sandboxRequest);
        var sandboxId = sandboxClient.doSomething(sandboxRequest.sandboxEnum());
        logger.info("sandboxId={}", sandboxId);
        return new SandboxResponse(sandboxId);
    }
}
