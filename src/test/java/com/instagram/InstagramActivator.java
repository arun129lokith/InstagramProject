package com.instagram;

import com.instagram.view.AuthenticationView;
import org.testng.annotations.Test;

public class InstagramActivator {

    @Test
    public static void main(String[] args) {
        AuthenticationView.getInstance().menu();
    }
}
