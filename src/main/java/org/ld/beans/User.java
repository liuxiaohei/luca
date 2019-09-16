package org.ld.beans;

public final class User {
    private final long userId;
    private final String userName;
    private final int age;

    public User(long userId, String userName, int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }
}
