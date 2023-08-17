package io.github.ndimovt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScientistTest {
    private String userName;
    private String password;
    public ScientistTest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}