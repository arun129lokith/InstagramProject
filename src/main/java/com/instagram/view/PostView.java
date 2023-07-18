package com.instagram.view;

import com.instagram.model.Post;
import com.instagram.model.Post.Format;
import com.instagram.model.PostBuilder;

import java.sql.Timestamp;

import java.time.Instant;

/**
 * <p>
 * Displays post information of the user and provides methods to render post data on the screen
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class PostView extends CommonView {

    private final UserView userView;
    private static PostView postView;

    private PostView() {
        userView = UserView.getInstance();
    }

    /**
     * <p>
     * Gets the object of the post view class
     * </p>
     *
     * @return The post view object
     */
    public static PostView getInstance() {
        return null == postView ? postView = new PostView() : postView;
    }

    /**
     * <p>
     * Prints the post menu of the user
     * </p>
     *
     * @param userId Represents user id
     */
    public void menu(final Long userId) {
        printMessage(String.join(" ","Click 1 To Create Post\nClick 2 To Display All Post",
                "\nClick 3 To Delete Post\nClick 4 To Update Post\nClick 5 To Display Post By Id\nClick 6 To User",
                "Screen\nClick 7 To Like Menu"));

        if (continueOrExit()) {
            userView.userScreen(userId);
        }
        final LikeView like = LikeView.getInstance();

        switch (getFeatures()) {
            case ONE:
                create(userId);
                break;
            case TWO:
                displayAll();
                break;
            case THREE:
                delete();
                break;
            case FOUR:
                update(userId);
                break;
            case FIVE:
                getPost();
                break;
            case SIX:
                userView.userScreen(userId);
                break;
            case SEVEN:
                like.menu(userId);
                break;
            default:
                printMessage("Invalid User Choice. Please Enter The Choice In The Range[1-7]");
                menu(userId);
                break;
        }
        menu(userId);
    }

    /**
     * <p>
     * Gets user location
     * </p>
     *
     * @return The location of the user
     */
    private String getLocation() {
        printMessage("Enter Your Location Of Your Post:");
        final String location = scanner.nextLine().trim();

        return validation.isValidLocation(location) ? location : getLocation();
    }

    /**
     * <p>
     * Gets user caption
     * </p>
     *
     * @return The caption of the user
     */
    private String getCaption() {
        printMessage("Enter Your Caption:");

        return scanner.nextLine().trim();
    }

    /**
     * <p>
     * Creates the post of the user
     * </p>
     *
     * @param userId Represents user id
     */
    private void create(final Long userId) {
        final PostBuilder post = PostBuilder.getInstance();

        post.withId(idGenerator());
        post.withFormat(getFormat());
        post.withLocation(getLocation());
        post.withCaption(getCaption());
        post.withUploadedTime(Timestamp.from(Instant.now()));
        post.withUserId(userId);

        printMessage(postController.create(post.build()) ? "User Posted Successfully" : "Post Not Created");
        menu(userId);
    }

    /**
     * <p>
     * Gets the format of the post of the user
     * </p>
     *
     * @return Represents {@link Post} format of the user
     */
    private Format getFormat() {
        printMessage("Click 1 To Image Format\nClick 2 To Video Format");
        final Format format = Format.existingFormat(userView.getChoice());

        if (null != format) {
            return format;
        } else {
            printMessage("Invalid Post Format Choice. Please Enter The Choice For Post Format In The Range[1-2]");

            return getFormat();
        }
    }

    /**
     * <p>
     * Prints the all posts, posted by user
     * </p>
     */
    private void displayAll() {
        System.out.println(postController.getAllPost());
    }

    /**
     * <p>
     * Gets the post detail of the user
     * </p>
     *
     * @return The post details of the id
     */
    private Post getPost() {
        final Post post = postController.getPost(getPostId());

        System.out.println(null != post ? post : "Post Not Found");

        return post;
    }

    /**
     * <p>
     * Users to delete the post
     * </p>
     */
    private void delete() {
        printMessage("Enter Your PostId:");
        printMessage(postController.delete(getPostId()) ? "Post Deleted Successfully"
                : "Post Not Found");
    }

    /**
     * <p>
     * Sets the details of the user post to update
     * </p>
     */
    private void update(final Long userId) {
        printMessage("Get The Post Of The User To Update Post Details");
        final Post post = new Post();
        final Post existingPost = getPostById(userId);

        if (null != existingPost) {
            post.setId(existingPost.getId());
            post.setUserId(existingPost.getUserId());
            post.setFormat(existingPost.getFormat());
            post.setLocation(userView.continueOrExit() ? existingPost.getLocation() : getLocation());
            post.setCaption(userView.continueOrExit() ? existingPost.getCaption() : getCaption());
            post.setUploadedTime(Timestamp.from(Instant.now()));

            postController.update(post);
            printMessage("Post Updated Successfully");
        } else {
            printMessage("Please Try Again");
        }
    }

    /**
     * <p>
     * Gets valid post id of the user
     * </p>
     *
     * @return The post id of the user
     */
    public Long getPostId() {
        printMessage("Enter Your PostId:");

        try {
            return Long.parseLong(scanner.nextLine().trim());
        } catch (final NumberFormatException message) {
            printMessage("Invalid Post Id Format. Please Enter A Number");
        }

        return getPostId();
    }

    /**
     * <p>
     * Gets a post of the user, if the post is posted by the user
     * </p>
     *
     * @param userId Represents id of the user
     * @return The post details of the user
     */
    private Post getPostById(final Long userId) {
        final Post post = getPost();

        if (null != post) {
            System.out.println(userId.equals(post.getUserId()) ? post : "Post Not Created By This User And No Access To Update");

            return post;
        }

        return null;
    }

    /**
     * <p>
     * Generates id for the new post
     * </p>
     *
     * @return The post id
     */
    private long idGenerator() {
        return postController.getAllPost().size() + 1;
    }
}



