package org.vaadin.olli.client;

import com.vaadin.shared.communication.ClientRpc;

import java.util.Map;

/**
 * Client-side RPC for sending the form.
 *
 * @author Risto Yrjänä / Vaadin }>
 *
 */
public interface FormRpc extends ClientRpc {

    void send(Method formMethod, Map<String, String> values, String formTarget,
            String formAction);
}
