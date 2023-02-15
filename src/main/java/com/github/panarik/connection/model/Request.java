package com.github.panarik.connection.model;

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
