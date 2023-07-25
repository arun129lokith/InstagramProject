package com.instagram.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.instagram.controller.UserController;
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
 * Handles user related operation such as fetching, updating and deleting user details
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserController userController;
    private final HttpRequestProcessor httpRequestProcessor;
    private final JSONObject jsonResponse;
    private final ObjectMapper objectMapper;

    public UserServlet() {
        this.userController = UserController.getInstance();
        this.httpRequestProcessor = HttpRequestProcessor.getInstance();
        this.jsonResponse = new JSONObject();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * <p>
     * Handles HTTP PUT request to update user details
     * </p>
     *
     * @param request The HttpServletRequest object representing the client's request
     * @param response The HttpServletResponse object to send the response to the client
     * @throws IOException Represents an error while processing the request or sending the response
     */
    @Override
    protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonPayload = httpRequestProcessor.getRequestPayload(request);
        final PrintWriter writer = response.getWriter();
        final JSONObject patchData = new JSONObject(jsonPayload);
        final JSONObject oldData = getDataFromDataBase(patchData.getLong("id"));

        for (final String key : patchData.keySet()) {
            oldData.put(key, patchData.get(key));
        }
        final String input = oldData.toString();
        final User user = objectMapper.readValue(input, User.class);

        jsonResponse.put("message", userController.update(user) ? "User Account Updated Successfully"
                : "User Account Not Found");
        writer.print(jsonResponse);
    }

    /**
     * <p>
     * Handles HTTP GET request to retrieve user details
     * </p>
     *
     * @param request The HttpServletRequest object representing the client's request
     * @param response The HttpServletResponse object to send the response to the client
     * @throws IOException Represents an error while processing the request or sending the response
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final PrintWriter writer = response.getWriter();
        final Collection<User> users = userController.getAll();

        if (! users.isEmpty()) {
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
        jsonResponse.put("message", "No User Found");
        writer.print(jsonResponse);
    }

    /**
     * <p>
     * Handles HTTP DELETE request to delete user details
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
        final User user = objectMapper.readValue(jsonPayload, User.class);

        jsonResponse.put("message", userController.delete(user.getId()) ? "User Account Deleted Successfully"
                : "User Account Not Found");
        writer.print(jsonResponse);
    }

    /**
     * <p>
     * Retrieves user data from the database for the given id and returns it as a JSONObject
     * </p>
     *
     * @param id Represents user id
     * @return The existing user information in json object format
     */
    private JSONObject getDataFromDataBase(final Long id) {
        final User user = userController.get(id);
        final JSONObject existingData = new JSONObject();

        if (null != user) {
            existingData.put("name", user.getName());
            existingData.put("mobileNumber", user.getMobileNumber());
            existingData.put("email", user.getEmail());
            existingData.put("password", user.getPassword());

            return existingData;
        }

        return existingData;
    }
}
