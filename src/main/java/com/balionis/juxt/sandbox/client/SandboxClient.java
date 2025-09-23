package com.balionis.juxt.sandbox.client;

import com.balionis.juxt.sandbox.model.SandboxEnum;

import java.util.UUID;

public class SandboxClient {
    public UUID doSomething(SandboxEnum sandboxEnum) {
        return switch (sandboxEnum) {
            case TYPE1 -> UUID.randomUUID();
            case TYPE2 -> UUID.nameUUIDFromBytes(SandboxClient.class.getName().getBytes());
        };
    }
}