package org.vaadin.olli;

import com.vaadin.ui.UI;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.olli.client.Method;

/**
 * Builder for constructing a {@link FormSender} more easily.
 *
 * @author Risto Yrjänä / Vaadin }>
 */
public class FormSenderBuilder {

    /**
     * Create a new builder instance. Set the values with the {code}with*{code}
     * methods, and call {@link #submit()} to send the request.
     *
     * @return
     */
    public static FormSenderBuilder create() {
        return new FormSenderBuilder();
    }

    /**
     * Use {@link #create()} instead
     */
    protected FormSenderBuilder() {
    }

    private Method method;
    private UI ui;
    private final Map<String, String> values = new HashMap<String, String>();
    private String action;
    private String target;

    public FormSenderBuilder withMethod(Method method) {
        this.method = method;
        return this;
    }

    public FormSenderBuilder withAction(String action) {
        this.action = action;
        return this;
    }

    public FormSenderBuilder withTarget(String target) {
        this.target = target;
        return this;
    }

    public FormSenderBuilder withUI(UI ui) {
        this.ui = ui;
        return this;
    }

    public FormSenderBuilder withValue(String name, String value) {
        values.put(name, value);
        return this;
    }

    public FormSenderBuilder withValues(Map<String, String> values) {
        this.values.putAll(values);
        return this;
    }

    public void submit() {
        FormSender fs = new FormSender(method);
        fs.setValues(values);
        fs.setFormAction(action);
        fs.setFormTarget(target);

        fs.extend(ui);
        fs.submit();
    }
}
