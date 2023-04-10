package com.github.panarik.responceService.typeDecorator.types;

import com.github.panarik.data.FileDataProvider;
import com.github.panarik.requestService.model.Request;
import com.github.panarik.responceService.bodyBuild.ResponseBuilder;
import com.github.panarik.responceService.typeDecorator.ResponseDecorator;

/**
 * Provides GET response.
 */
public class GetResponse extends ResponseDecorator {


    public GetResponse(Request request) {
        super(request);
        System.out.println("Forming response for GET request.");
    }

    public String answer() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        if (request.isValid()) {
            responseBuilder.setStatus(200).setBody(new FileDataProvider().getData(request.getPath()));
        } else {
            responseBuilder.setStatus(404).setBody(new FileDataProvider().getData("/404.html"));
        }
        return responseBuilder.build();
    }
}
