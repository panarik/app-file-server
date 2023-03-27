package com.github.panarik.requestService.model;

import java.util.LinkedList;

/**
 * Request from client.
 */
public class Request {

    private String status;
    private String protocol;
    private String code;
    private LinkedList<String> headers;
    private String body;

}
