package ru.gb.j2ee.servlet.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

/**
 * @author Nikita Ermakov
 *
 * Converter for orders admin panel for state enum form
 */
@FacesConverter(value = "stateConverter")
public class StateConverter implements Converter {

    private static final Map<Object, String> states = new WeakHashMap<>();

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object state) {
        synchronized (states) {
            if (!states.containsKey(state)) {
                final String uuid = UUID.randomUUID().toString();
                states.put(state, uuid);
                return uuid;
            } else {
                return states.get(state);
            }
        }
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String uuid) {
        for (Map.Entry<Object, String> entry : states.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
