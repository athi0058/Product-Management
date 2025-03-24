package com.athiaa.AA2_web.Model;

import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class LinkedInPostRequest {
    private String author;
    private String lifecycleState;
    public LinkedInPostRequest(){}

    public LinkedInPostRequest(String author, String lifecycleState, Map<String, Object> specificContent, Map<String, String> visibility) {
        this.author = author;
        this.lifecycleState = lifecycleState;
        this.specificContent = specificContent;
        this.visibility = visibility;
    }

    private Map<String, Object> specificContent;
    private Map<String, String> visibility;

    // Getters and Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLifecycleState() {
        return lifecycleState;
    }

    public void setLifecycleState(String lifecycleState) {
        this.lifecycleState = lifecycleState;
    }

    public Map<String, Object> getSpecificContent() {
        return specificContent;
    }

    public void setSpecificContent(Map<String, Object> specificContent) {
        this.specificContent = specificContent;
    }

    public Map<String, String> getVisibility() {
        return visibility;
    }

    public void setVisibility(Map<String, String> visibility) {
        this.visibility = visibility;
    }
}
