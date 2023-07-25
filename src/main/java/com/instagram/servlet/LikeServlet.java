package com.instagram.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.controller.LikeController;
import com.instagram.model.Like;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 *  Handles the http request and response of the like operation
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    private final LikeController likeController;

    public LikeServlet() {
        likeController = LikeController.getInstance();
    }

    /**
     * <p>
     * Creates a like for the user post of the application
     * </p>
     *
     * @param request Represents http request of the client
     * @param response Represents http response of the client request
     * @throws IOException
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonInput = getRequest(request);
        final PrintWriter out = response.getWriter();
        final JSONObject jsonResponse = new JSONObject();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Like like = objectMapper.readValue(jsonInput, Like.class);

        likeController.likePost(like);
        jsonResponse.put("message", "Post Liked Successfully");
        jsonResponse.put("status", "Success");

        out.print(jsonResponse);
    }

    /**
     * <p>
     * Removes the like of the user post
     * </p>
     *
     * @param request Represents http request of the client
     * @param response Represents http response of the client request
     * @throws IOException
     */
    @Override
    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonInput = getRequest(request);
        final PrintWriter out = response.getWriter();
        final JSONObject jsonResponse = new JSONObject();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Like like = objectMapper.readValue(jsonInput, Like.class);

        if (likeController.unlikePost(like.getId())) {
            jsonResponse.put("message", "Like Removed Successfully");
            jsonResponse.put("status", "Success");

            out.print(jsonResponse);

            return;
        }
        jsonResponse.put("message", "Like Not Found");

        out.print(jsonResponse);
    }

    /**
     * <p>
     * Converts the json object request to string format
     * </p>
     *
     * @param request Represents http request of the client
     * @return The string format of requested object
     * @throws IOException
     */
    private String getRequest(final HttpServletRequest request) throws IOException {
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
