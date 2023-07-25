package com.instagram.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.instagram.controller.PostController;
import com.instagram.model.Post;
import com.instagram.model.Post.Format;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;

import java.time.Instant;

import java.util.Collection;

/**
 * <p>
 * Handles post related operation of the user post
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
@WebServlet("/post")
public class PostServlet extends HttpServlet {

    private final PostController postController;
    private final HttpRequestProcessor httpRequestProcessor;
    private final JSONObject jsonResponse;
    private final ObjectMapper objectMapper;

    public PostServlet() {
        this.postController = PostController.getInstance();
        this.httpRequestProcessor = HttpRequestProcessor.getInstance();
        this.jsonResponse = new JSONObject();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * <p>
     * Handles HTTP POST requests to create user post
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
        final PrintWriter writer = response.getWriter();
        final Post post = objectMapper.readValue(jsonPayload, Post.class);

        post.setUploadedTime(Timestamp.from(Instant.now()));

        jsonResponse.put("message", postController.create(post) ? "Post Created Successfully"
                : "Post Not Created");
        writer.print(jsonResponse);
    }

    /**
     * <p>
     * Handles HTTP PUT request to update post details
     * </p>
     *
     * @param request The HttpServletRequest object representing the client's request
     * @param response The HttpServletResponse object to send the response to the client
     * @throws IOException Represents an error while processing the request or sending the response
     */
    protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonPayload = httpRequestProcessor.getRequestPayload(request);
        final PrintWriter writer = response.getWriter();
        final JSONObject patchData = new JSONObject(jsonPayload);
        final JSONObject oldData = getDataFromDataBase(patchData.getLong("id"));

        if (! oldData.isEmpty()) {

            for (final String key : patchData.keySet()) {
                oldData.put(key, patchData.get(key));
            }
            final String detail = oldData.toString();
            final Post post = objectMapper.readValue(detail, Post.class);

            post.setUploadedTime(Timestamp.from(Instant.now()));

            if (postController.update(post)) {
                jsonResponse.put("message", "Post Updated Successfully");
                jsonResponse.put("status", "success");
                writer.print(jsonResponse);

                return;
            }
        }
        jsonResponse.put("message", "Post Not Found");
        writer.print(jsonResponse);
    }

    /**
     * <p>
     * Handles HTTP DELETE request to delete post details
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
        final Post post = objectMapper.readValue(jsonPayload, Post.class);

        jsonResponse.put("message", postController.delete(post.getId()) ? "Post Deleted Successfully"
                : "Post Not Found");
        writer.print(jsonResponse);
    }

    /**
     * <p>
     * Handles HTTP GET request to retrieve post details
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

        if (jsonPayload.isEmpty()) {
            final Collection<Post> posts = postController.getAll();
            final JSONArray postsArray = new JSONArray();

            for (final Post existingPost : posts) {
                final JSONObject postObject = new JSONObject();

                postObject.put("id", existingPost.getId());
                postObject.put("caption", existingPost.getCaption());
                postObject.put("location", existingPost.getLocation());
                postObject.put("userId", existingPost.getUserId());
                postObject.put("uploadedTime", existingPost.getUploadedTime().toString());
                postObject.put("format", existingPost.getFormat());
                postsArray.put(postObject);
            }
            writer.print(postsArray);
        } else {
            final Post post = objectMapper.readValue(jsonPayload, Post.class);
            final JSONObject postDetail = getDataFromDataBase(post.getId());

            if (! postDetail.isEmpty()) {
                jsonResponse.put("id", postDetail.getLong("id"));
                jsonResponse.put("userId", postDetail.getLong("userId"));
                jsonResponse.put("caption", postDetail.getString("caption"));
                jsonResponse.put("location", postDetail.getString("location"));
                jsonResponse.put("uploadedTime", postDetail.getString("uploadedTime"));
                jsonResponse.put("format", postDetail.getEnum(Format.class, "format"));
                writer.print(jsonResponse);

                return;
            }
            jsonResponse.put("message", "Post Not Found");
            writer.print(jsonResponse);
        }
    }

    /**
     * <p>
     * Retrieves user data from the database for the given id and returns it as a JSONObject
     * </p>
     *
     * @param id Represents post id
     * @return The existing post information in json object format
     */
    private JSONObject getDataFromDataBase(final Long id) {
        final Post post = postController.get(id);
        final JSONObject existingData = new JSONObject();

        if (null != post) {
            existingData.put("id", post.getId());
            existingData.put("userId", post.getUserId());
            existingData.put("caption", post.getCaption());
            existingData.put("location", post.getLocation());
            existingData.put("uploadedTime", post.getUploadedTime().toString());
            existingData.put("format", post.getFormat());

            return existingData;
        }

        return existingData;
    }
}
