package com.instagram.service.impl2;

import com.instagram.dao.AuthenticationDao;
import com.instagram.dao.impl.AuthenticationDaoImpl;
import com.instagram.model.User;
import com.instagram.service.AuthenticationService;

/**
 * <p>
 * Implements the service of the authentication related operation
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    private static AuthenticationServiceImpl authenticationServiceImpl;
    private final AuthenticationDao authenticationDao;

    private AuthenticationServiceImpl() {
        authenticationDao = AuthenticationDaoImpl.getInstance();
    }

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The authentication service implementation object
     */
    public static AuthenticationServiceImpl getInstance() {
        return null == authenticationServiceImpl ? authenticationServiceImpl = new AuthenticationServiceImpl()
                : authenticationServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if sign-up is successful, false otherwise
     */
    @Override
    public boolean signUp(final User user) {
        return authenticationDao.signUp(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if sign-in is successful, false otherwise
     */
    @Override
    public boolean signIn(final User user) {
        return null != user.getMobileNumber() ? isMobileNumberExist(user) : isEmailExist(user);
    }

    /**
     * Checks the mobile number and password is exists
     *
     * @param user Represents {@link User} details
     * @return True if mobile number is exists, false otherwise
     */
    private boolean isMobileNumberExist(final User user) {
        return authenticationDao.isMobileNumberExist(user);
    }

    /**
     * Checks the email and password is exists
     *
     * @param user Represents {@link User} details
     * @return True if email is exists, false otherwise
     */
    private boolean isEmailExist(final User user) {
        return authenticationDao.isEmailExist(user);
    }
}
