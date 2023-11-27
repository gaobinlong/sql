package org.opensearch.sql.client;

import org.opensearch.client.Client;
import org.opensearch.core.action.ActionListener;
import org.opensearch.core.action.ActionResponse;
import org.opensearch.sql.ppl.transport.PPLQueryAction;
import org.opensearch.sql.ppl.transport.TransportPPLQueryRequest;
import org.opensearch.sql.ppl.transport.TransportPPLQueryResponse;

import java.util.function.Function;

public class SqlNodeClient implements SqlClient {
    Client client;

    @Override
    public void pplQuery(TransportPPLQueryRequest request, ActionListener<TransportPPLQueryResponse> listener) {
        client.execute(PPLQueryAction.INSTANCE, request, getListener(listener));
    }

    private ActionListener<TransportPPLQueryResponse> getListener(
        ActionListener<TransportPPLQueryResponse> listener
    ) {
        return wrapActionListener(listener, TransportPPLQueryResponse::fromActionResponse);
    }

    private <T extends ActionResponse> ActionListener<T> wrapActionListener(
        final ActionListener<T> listener, final Function<ActionResponse, T> recreate
    ) {
        return ActionListener.wrap(r -> {
            listener.onResponse(recreate.apply(r));
        }, listener::onFailure);
    }
}
