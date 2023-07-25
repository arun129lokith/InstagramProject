package com.instagram.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.instagram.controller.AuthenticationController;
import com.instagram.model.User;
import com.instagram.validation.CommonValidation;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * Handles user authentication operation for sign up and sign in
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
@WebServlet("/user/authentication")
public class AuthenticationServlet extends HttpServlet {

    private final CommonValidation validation;
    private final AuthenticationController authenticationController;
    private final HttpRequestProcessor httpRequestProcessor;
    private final JSONObject jsonResponse;
    private final ObjectMapper objectMapper;

    public AuthenticationServlet() {
        this.validation = CommonValidation.getInstance();
        this.authenticationController = AuthenticationController.getInstance();
        this.httpRequestProcessor = HttpRequestProcessor.getInstance();
        this.jsonResponse = new JSONObject();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * <p>
     * Handles HTTP POST requests for user sign up
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
        final User user = objectMapper.readValue(jsonPayload, User.class);

        if (validation.isNameExist(user.getName())) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            jsonResponse.put("error", "Conflict");
            jsonResponse.put("message", "User Name Already exist");
            writer.print(jsonResponse);

            return;
        }

        if (null == user.getName() || user.getName().trim().isEmpty() || null == user.getPassword()
                || user.getPassword().trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jsonResponse.put("error", "Bad Request");
            jsonResponse.put("message", "Invalid User Details");
            writer.print(jsonResponse);

            return;
        }
        jsonResponse.put("message", authenticationController.signUp(user) ? "Sign Up Successfully"
                : "Sign Up Unsuccessfully" );
        writer.print(jsonResponse);
    }

    /**
     * <p>
     * Handles HTTP GET requests for user sign in
     * </p>
     *
     * @param request The HttpServletRequest object representing the client's request
     * @param response The HttpServletResponse object to send the response to the client
     * @throws IOException Represents an error while processing the request or sending the response
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        final String jsonInput = httpRequestProcessor.getRequestPayload(request);
        final PrintWriter writer = response.getWriter();
        final User user = objectMapper.readValue(jsonInput, User.class);

        jsonResponse.put("message", authenticationController.signIn(user) ? "Sign In Successfully"
                : "Sign In Unsuccessfully" );
        writer.print(jsonResponse);
    }
}
