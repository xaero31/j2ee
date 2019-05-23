package ru.gb.j2ee.model.meta;

import lombok.Getter;

/**
 * @author Nikita Ermakov
 *
 * States of order
 */
public enum State {

    COMPLETE("Complete"),
    IN_PROGRESS("In progress"),
    CANCELED("Canceled");

    @Getter
    private String name;

    State(String name) {
        this.name = name;
    }


}
