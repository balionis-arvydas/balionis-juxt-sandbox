package com.balionis.juxt.sandbox.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.balionis.juxt.sandbox.model.SandboxEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SandboxClientTest {

    @Test
    public void testSuccessForType1() {

        var client = new SandboxClient();

        var actual = client.doSomething(SandboxEnum.TYPE2);

        var expected = UUID.nameUUIDFromBytes(SandboxClient.class.getName().getBytes());

        assertEquals(expected, actual);
    }

}