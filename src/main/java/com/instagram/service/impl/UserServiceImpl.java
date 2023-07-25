package com.instagram.service.impl;

import com.instagram.dao.UserDao;
import com.instagram.dao.impl.UserDaoImpl;
import com.instagram.model.User;
import com.instagram.service.UserService;

import java.util.Collection;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl;
    private final UserDao userDao;

    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
    }

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The user service implementation object
     */
    public static UserServiceImpl getInstance() {
        return null == userServiceImpl ? userServiceImpl = new UserServiceImpl() : userServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if sign-up is successful, false otherwise
     */
    @Override
    public boolean signUp(final User user) {
        return userDao.signUp(user);
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
        return userDao.isMobileNumberExist(user);
    }

    /**
     * Checks the email and password is exists
     *
     * @param user Represents {@link User} details
     * @return True if email is exists, false otherwise
     */
    private boolean isEmailExist(final User user) {
        return userDao.isEmailExist(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param name Represents user name
     * @return True if name is exists, false otherwise
     */
    @Override
    public boolean isNameExist(final String name) {
        return userDao.isNameExist(name);
    }

    /**
     * {@inheritDoc}
     *
     * @param email Represents user email
     * @return True if email is exists, false otherwise
     */
    @Override
    public boolean isEmailExist(final String email) {
        return userDao.isEmailExist(email);
    }

    /**
     * {@inheritDoc}
     *
     * @param mobileNumber Represents user mobile number
     * @return True if mobile number is exists, false otherwise
     */
    @Override
    public boolean isMobileNumberExist(final String mobileNumber) {
        return userDao.isMobileNumberExist(mobileNumber);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if update is successful, false otherwise
     */
    @Override
    public boolean update(final User user) {
        return userDao.update(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents user id
     * @return True if account is deleted, false otherwise
     */
    @Override
    public boolean delete(final Long id) {
        return userDao.delete(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents user id
     * @return The user details
     */
    @Override
    public User get(final Long id) {
        return userDao.get(id);
    }

    /**
     * {@inheritDoc}
     *
     * @return The collection of user details
     */
    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }
}
