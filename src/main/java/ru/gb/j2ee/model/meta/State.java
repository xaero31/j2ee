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
    private String text;

    State(String text) {
        this.text = text;
    }


}
