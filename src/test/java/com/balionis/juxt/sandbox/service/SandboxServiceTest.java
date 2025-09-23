package com.balionis.juxt.sandbox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.balionis.juxt.sandbox.client.SandboxClient;
import com.balionis.juxt.sandbox.model.SandboxEnum;
import com.balionis.juxt.sandbox.model.SandboxRequest;
import com.balionis.juxt.sandbox.model.SandboxResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

/**
 */
@ExtendWith(MockitoExtension.class)
public class SandboxServiceTest {

    @Test
    public void testSuccessForType1() {

        var client = mock(SandboxClient.class);
        var sandboxId = UUID.randomUUID();
        when(client.doSomething(SandboxEnum.TYPE1)).thenReturn(sandboxId);

        var service = new SandboxService(client);

        var actual = service.doSomething(new SandboxRequest(SandboxEnum.TYPE1));

        var expected = new SandboxResponse(sandboxId);

        assertEquals(expected, actual);
    }
}
