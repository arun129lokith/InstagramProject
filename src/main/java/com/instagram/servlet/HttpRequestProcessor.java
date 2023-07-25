package com.instagram.servlet;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * <p>
 * Handles the responsible for processing HTTP request
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class HttpRequestProcessor {

    private static HttpRequestProcessor httpRequestProcessor;

    private HttpRequestProcessor() {}

    public static HttpRequestProcessor getInstance() {
        return null == httpRequestProcessor ? httpRequestProcessor = new HttpRequestProcessor() : httpRequestProcessor;
    }

    /**
     * <p>
     * Extracts the payload of an HTTP request and returns it as a String
     * </p>
     *
     * @param request The HttpServletRequest object representing the client's request
     * @return A String containing the payload data
     * @throws IOException Represents an error while processing the request or sending the response
     */
    public String getRequestPayload(final HttpServletRequest request) throws IOException {
        final StringBuilder requestInput = new StringBuilder();

        try (final BufferedReader reader = request.getReader()) {
            String line;

            while (null != (line = reader.readLine())) {
                requestInput.append(line);
            }
        }

        return requestInput.toString();
    }
}
