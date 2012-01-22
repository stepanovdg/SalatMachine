package by.bsu.salatmachine.enums;

import by.bsu.salatmachine.model.entity.User;

public enum Role {
    ADMIN, CLIENT, GUEST;


    public static Role getValue(User user) {
        if (user == null) {
            return GUEST;
        } else {
            if (user.isType()) {
                return ADMIN;
            } else {
                return CLIENT;
            }
        }

    }
}
