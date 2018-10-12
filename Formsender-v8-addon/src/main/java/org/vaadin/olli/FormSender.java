package org.vaadin.olli;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.UI;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.olli.client.FormRpc;
import org.vaadin.olli.client.Method;

/**
 * Component that allows submitting post or get requests from a Vaadin
 * application. Submitting a form with this component will redirect the user
 * away from the current application.
 *
 * @author Risto Yrjänä / Vaadin }>
 *
 */
public class FormSender extends AbstractExtension {

    private static final long serialVersionUID = -2072702453956623077L;

    private Method formMethod;

    private Map<String, String> values;

    private String formTarget;

    private String formAction;

    public FormSender() {
        this(Method.POST);
    }

    public FormSender(Method formMethod) {
        this.formMethod = formMethod;
        values = new HashMap<String, String>();
    }

    public void extend(UI target) {
        super.extend(target);
    }

    /**
     * Submit the form with the current values, target, action and method.
     */
    public void submit() {
        getRpcProxy(FormRpc.class).send(formMethod, values, formTarget,
                formAction);
    }

    /**
     * Set the method to be used when submitting the form.
     *
     * @param formMethod
     */
    public void setFormMethod(Method formMethod) {
        this.formMethod = formMethod;
    }

    public Method getFormMethod() {
        return formMethod;
    }

    /**
     * Set the name / value pairs to use when submitting the form.
     *
     * @param values
     *            the values to set
     */
    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public Map<String, String> getValues() {
        return values;
    }

    /**
     * Add a name / value pair.
     *
     * @param name
     * @param value
     */
    public void addValue(String name, String value) {
        values.put(name, value);
    }

    /**
     * Set the submit target. E.g. "_blank".
     *
     * @param formTarget
     */
    public void setFormTarget(String formTarget) {
        this.formTarget = formTarget;
    }

    public String getFormTarget() {
        return formTarget;
    }

    public String getFormAction() {
        return formAction;
    }

    /**
     * Set the action of the form. This can be a full valid URL or a part of it
     * relative to the current application.
     *
     * @param formAction
     */
    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }
}
