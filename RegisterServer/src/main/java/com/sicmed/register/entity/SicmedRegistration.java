package com.sicmed.register.entity;

public class SicmedRegistration {
    private String id;

    private String registrationType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType == null ? null : registrationType.trim();
    }
}