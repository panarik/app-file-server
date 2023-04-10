package com.github.panarik.responceService.typeDecorator;

/**
 * Basic response with methods for any response
 */
public interface Response {

    /**
     * Different answer for different type of response.
     *
     * @return Full response lines.
     */
    String answer();

}
