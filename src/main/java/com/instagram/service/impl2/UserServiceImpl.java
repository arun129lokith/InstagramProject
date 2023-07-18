package com.instagram.service.impl2;

import com.instagram.service.UserService;
import com.instagram.dao.UserDao;
import com.instagram.model.User;
import com.instagram.dao.impl.UserDaoImpl;

import java.util.Collection;

/**
 * <p>
 * Implements the service of the user related operation
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl = null;
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
     * @param id Represents user id
     * @return The user details
     */
    @Override
    public User getUser(final Long id) {
        return userDao.getUser(id);
    }

    /**
     * {@inheritDoc}
     *
     * @return The collection of user details
     */
    @Override
    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
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
     * @param user Represents {@link User} detail
     * @return Users id
     */
    @Override
    public Long getId(final User user) {
        return userDao.getId(user);
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
}
