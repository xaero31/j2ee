package ru.gb.j2ee.model.meta;

/**
 * @author Nikita Ermakov
 *
 * States of order
 */
public enum State {

    COMPLETE("Complete"),
    IN_PROGRESS("In progress"),
    CANCELED("Canceled");

    private String text;

    State(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
