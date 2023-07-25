package com.instagram.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.instagram.controller.PostController;
import com.instagram.model.Post;
import com.instagram.model.Post.Format;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * <p>
 *  Handles the http request and response of the post operation
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
@WebServlet("/post")
public class PostServlet extends HttpServlet {

    private final PostController postController;

    public PostServlet() {
        postController = PostController.getInstance();
    }

    /**
     * <p>
     * Creates the post of the user
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
        final Post post = objectMapper.readValue(jsonInput, Post.class);

        post.setUploadedTime(Timestamp.from(Instant.now()));
        System.out.println(post);

        if (postController.create(post)) {
            jsonResponse.put("message", "Post Created Successfully");
            jsonResponse.put("status", "Success");

            out.print(jsonResponse);

            return;
        }
        jsonResponse.put("message", "Post Not Created");

        out.print(jsonResponse);
    }

    /**
     * <p>
     * Updates the user post by user information
     * </p>
     *
     * @param request Represents http request of the client
     * @param response Represents http response of the client request
     * @throws IOException
     */
    protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonInput = getRequest(request);
        final PrintWriter out = response.getWriter();
        final JSONObject jsonResponse = new JSONObject();
        final JSONObject patchData = new JSONObject(jsonInput);
        final JSONObject oldData = getDataFromDataBase(patchData.getLong("id"));

        if (! oldData.isEmpty()) {
            for (final String key : patchData.keySet()) {
                oldData.put(key, patchData.get(key));
            }
            final String detail = oldData.toString();
            final ObjectMapper objectMapper = new ObjectMapper();
            final Post post = objectMapper.readValue(detail, Post.class);

            post.setUploadedTime(Timestamp.from(Instant.now()));

            if (postController.update(post)) {
                jsonResponse.put("message", "Post Updated Successfully");
                jsonResponse.put("status", "success");

                out.print(jsonResponse);

                return;
            }
        }
        jsonResponse.put("message", "Post Not Found");

        out.print(jsonResponse);
    }

    /**
     * <p>
     * Users to delete the post
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
        final Post post = objectMapper.readValue(jsonInput, Post.class);

        if (postController.delete(post.getId())) {
            jsonResponse.put("message", "Post Deleted Successfully");
            jsonResponse.put("status", "Success");

            out.print(jsonResponse);

            return;
        }
        jsonResponse.put("message", "Post Not Found");
        jsonResponse.put("status", "Not Success");

        out.print(jsonResponse);

    }

    /**
     * <p>
     * Gets the post information of the user
     * </p>
     *
     * @param request Represents http request of the client
     * @param response Represents http response of the client request
     * @throws IOException
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonInput = getRequest(request);
        final PrintWriter out = response.getWriter();
        final JSONObject jsonResponse = new JSONObject();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Post post = objectMapper.readValue(jsonInput, Post.class);
        final JSONObject postDetail = getDataFromDataBase(post.getId());

        if (! postDetail.isEmpty()) {
            jsonResponse.put("id", postDetail.getLong("id"));
            jsonResponse.put("userId", postDetail.getLong("userId"));
            jsonResponse.put("caption", postDetail.getString("caption"));
            jsonResponse.put("location", postDetail.getString("location"));
            jsonResponse.put("uploadedTime", postDetail.getString("uploadedTime"));
            jsonResponse.put("format", postDetail.getEnum(Format.class,"format"));

            out.print(jsonResponse);

            return;
        }
        jsonResponse.put("message", "Post Not Found");

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

    /**
     * <p>
     * Gets the data from database and represent it in json object
     * </p>
     *
     * @param id Represents post id
     * @return The existing post information
     */
    private JSONObject getDataFromDataBase(final Long id) {
        final Post post = postController.getPost(id);
        final JSONObject oldData = new JSONObject();

        if (null != post) {
            oldData.put("id", post.getId());
            oldData.put("userId", post.getUserId());
            oldData.put("caption", post.getCaption());
            oldData.put("location", post.getLocation());
            oldData.put("uploadedTime", post.getUploadedTime().toString());
            oldData.put("format", post.getFormat());

            return oldData;
        }

        return oldData;
    }
}
