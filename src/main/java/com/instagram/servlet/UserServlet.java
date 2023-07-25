package com.instagram.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.instagram.controller.UserController;
import com.instagram.model.User;
import com.instagram.validation.CommonValidation;

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
 *  Handles the http request and response of the user operation
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserController userController;
    private final CommonValidation validation;

    public UserServlet() {
        userController = UserController.getInstance();
        validation = CommonValidation.getInstance();
    }

    /**
     * <p>
     * Creates an user account for the application using user information
     * </p>
     *
     * @param request Represents http request of the user
     * @param response Represents http response of the user request
     * @throws IOException
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonInput = getRequest(request);
        final PrintWriter out = response.getWriter();
        final JSONObject jsonResponse = new JSONObject();
        final ObjectMapper objectMapper = new ObjectMapper();
        final User user = objectMapper.readValue(jsonInput, User.class);

        if (validation.isNameExist(user.getName())) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);

            jsonResponse.put("error", "Conflict");
            jsonResponse.put("message", "User Name Already exist");

            out.print(jsonResponse);

            return;
        }

        if (null == user.getName() || user.getName().trim().isEmpty() || null == user.getPassword()
                || user.getPassword().trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            jsonResponse.put("error", "Bad Request");
            jsonResponse.put("message", "Invalid User Details");

            out.print(jsonResponse);

            return;
        }

        if (userController.signUp(user)) {
            jsonResponse.put("message", "Sign Up Successfully");
            jsonResponse.put("status", "Success");

            out.print(jsonResponse);

            return;
        }

        jsonResponse.put("message", "Sign Up Unsuccessfully");

        out.print(jsonResponse);
    }

    /**
     * <p>
     * Users to access the features by sign in process
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
        final User user = objectMapper.readValue(jsonInput, User.class);

        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getMobileNumber());

        if (userController.signIn(user)) {
            jsonResponse.put("message", "Sign In Successfully");
            jsonResponse.put("status", "Success");

            out.print(jsonResponse);

            return;
        }

        jsonResponse.put("status", "Sign In Unsuccessfully");
        jsonResponse.put("message", "User Not Found");

        out.print(jsonResponse);
    }

    /**
     * <p>
     * Updates the user details by user information
     * </p>
     *
     * @param request Represents http request of the client
     * @param response Represents http response of the client request
     * @throws IOException
     */
    @Override
    protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonInput = getRequest(request);
        final PrintWriter out = response.getWriter();
        final JSONObject patchData = new JSONObject(jsonInput);
        final JSONObject oldData = getDataFromDataBase(patchData.getLong("id"));

        for (final String key : patchData.keySet()) {
            oldData.put(key, patchData.get(key));
        }

        final String input = oldData.toString();
        final JSONObject jsonResponse = new JSONObject();
        final ObjectMapper objectMapper = new ObjectMapper();
        final User user = objectMapper.readValue(input, User.class);

        System.out.println(user);

        if (userController.update(user)) {
            jsonResponse.put("message", "User Account Updated Successfully");
            jsonResponse.put("status", "Success");

            out.print(jsonResponse);

            return;
        }
        jsonResponse.put("message", "User Account Not Found");

        out.print(jsonResponse);
    }

    /**
     * <p>
     * Users to delete his account
     * </p>
     *
     * @param request Represents http request of the user
     * @param response Represents http response of the user request
     * @throws IOException
     */
    @Override
    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonInput = getRequest(request);
        final PrintWriter out = response.getWriter();
        final JSONObject jsonResponse = new JSONObject();
        final ObjectMapper objectMapper = new ObjectMapper();
        final User user = objectMapper.readValue(jsonInput, User.class);


        if (userController.delete(user.getId())) {
            jsonResponse.put("message", "User Account Deleted Successfully");
            jsonResponse.put("status", "Success");

            out.print(jsonResponse);

            return;
        }
        jsonResponse.put("message", "User Account Not Found");
        jsonResponse.put("status", "Not Success");

        out.print(jsonResponse);
    }

    /**
     * <p>
     * Gets the data from database and represent it in json object
     * </p>
     *
     * @param id Represents user id
     * @return The existing user information
     */
    private JSONObject getDataFromDataBase(final Long id) {
        final User user = userController.getUser(id);
        final JSONObject oldData = new JSONObject();

        if (null != user) {
            oldData.put("name", user.getName());
            oldData.put("mobileNumber", user.getMobileNumber());
            oldData.put("email", user.getEmail());
            oldData.put("password", user.getPassword());

            return oldData;
        }

        return oldData;
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
