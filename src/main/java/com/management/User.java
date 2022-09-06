package com.management;
import lombok.*;

@AllArgsConstructor
public class User {
    @Getter
    private int id;
    @Getter
    private String username;
    @Getter
    private String password;
}
