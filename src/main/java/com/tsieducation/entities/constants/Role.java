package com.tsieducation.entities.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Role {

    CLIENT("ROLE_CLIENT"),
    ADMIN("ROLE_ADMIN");

    private final String springSecValue;
}
