package com.instagram.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.instagram.controller.LikeController;
import com.instagram.model.Like;

import com.instagram.model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Collection;

/**
 * <p>
 * Handles operations related to user likes on a post
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
@WebServlet("/post/like")
public class LikeServlet extends HttpServlet {

    private final LikeController likeController;
    private final HttpRequestProcessor httpRequestProcessor;
    private final JSONObject jsonResponse;
    private final ObjectMapper objectMapper;

    public LikeServlet() {
        this.likeController = LikeController.getInstance();
        this.httpRequestProcessor = HttpRequestProcessor.getInstance();
        this.jsonResponse = new JSONObject();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * <p>
     * Handles HTTP POST requests to create like for the post
     * </p>
     *
     * @param request The HttpServletRequest object representing the client's request
     * @param response The HttpServletResponse object to send the response to the client
     * @throws IOException Represents an error while processing the request or sending the response
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonPayload = httpRequestProcessor.getRequestPayload(request);
        final PrintWriter out = response.getWriter();
        final Like like = objectMapper.readValue(jsonPayload, Like.class);

        likeController.likePost(like);
        jsonResponse.put("message", "Post Liked Successfully");
        jsonResponse.put("status", "Success");
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles HTTP DELETE request to remove like for the post
     * </p>
     *
     * @param request The HttpServletRequest object representing the client's request
     * @param response The HttpServletResponse object to send the response to the client
     * @throws IOException Represents an error while processing the request or sending the response
     */
    @Override
    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonPayload = httpRequestProcessor.getRequestPayload(request);
        final PrintWriter writer = response.getWriter();
        final Like like = objectMapper.readValue(jsonPayload, Like.class);

        jsonResponse.put("message", likeController.unlikePost(like.getId()) ? "Like Removed Successfully"
                : "Like Not Found");
        writer.print(jsonResponse);
    }

    /**
     * <p>
     * Handles HTTP GET request to retrieve user details who likes the post
     * </p>
     *
     * @param request The HttpServletRequest object representing the client's request
     * @param response The HttpServletResponse object to send the response to the client
     * @throws IOException Represents an error while processing the request or sending the response
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonPayload = httpRequestProcessor.getRequestPayload(request);
        final PrintWriter writer = response.getWriter();
        final Like like = objectMapper.readValue(jsonPayload, Like.class);
        final Collection<User> users = likeController.getLikeUser(like.getPostId());

        if (null != users && ! users.isEmpty()) {
            final JSONArray usersArray = new JSONArray();

            for (final User user : users) {
                final JSONObject userJson = new JSONObject();

                userJson.put("id", user.getId());
                userJson.put("name", user.getName());
                userJson.put("email", user.getEmail());
                userJson.put("mobileNumber", user.getMobileNumber());
                usersArray.put(userJson);
            }
            writer.print(usersArray);

            return;
        }
        jsonResponse.put("message", "Post Not Liked By User");
        writer.print(jsonResponse);
    }
}
