package ru.gb.j2ee.servlet.meta;

import ru.gb.j2ee.model.meta.State;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Nikita Ermakov
 *
 * Class for resolving SelectOneItem menu for order state in order.xhtml
 */
@ManagedBean(name = "stateData")
@ViewScoped
public class StateData {

    public State[] getStates() {
        return State.values();
    }
}
