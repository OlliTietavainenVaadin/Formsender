package org.vaadin.olli.client;

import java.util.Map;
import java.util.Map.Entry;

import org.vaadin.olli.FormSender;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.FormElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.Element;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

@Connect(FormSender.class)
public class FormSenderConnector extends AbstractExtensionConnector {

    private static final long serialVersionUID = 5721950329134512038L;
    private Element targetElement;
    private final FormRpc formRpc = new FormRpc() {

        private static final long serialVersionUID = -6376909637382144413L;

        @Override
        public void send(Method method, Map<String, String> values, String target, String action) {
            createAndSendForm(method.toString(), values, target, action);
        }
    };

    @Override
    protected void init() {
        super.init();

        registerRpc(FormRpc.class, formRpc);
    }

    @Override
    public void onUnregister() {
        unregisterRpc(FormRpc.class, formRpc);
        super.onUnregister();
    }

    @Override
    protected void extend(ServerConnector target) {
        targetElement = ((ComponentConnector) target).getWidget().getElement();

    }

    protected void createAndSendForm(String method, Map<String, String> values, String target, String action) {

        FormElement formElement = createFormElement(method, values, target, action);
        formElement.setClassName("formsender");

        targetElement.appendChild(formElement);

        formElement.submit();
    }

    // create the form element to be submitted
    protected FormElement createFormElement(String method, Map<String, String> values, String target, String action) {
        FormElement fe = Document.get().createFormElement();

        fe.setMethod(method);
        fe.setTarget(target);
        fe.setAction(action);

        for (Entry<String, String> nameValue : values.entrySet()) {
            InputElement inputElement = Document.get().createHiddenInputElement();
            inputElement.setName(nameValue.getKey());
            inputElement.setValue(nameValue.getValue());
            fe.appendChild(inputElement);
        }

        return fe;
    }
}
