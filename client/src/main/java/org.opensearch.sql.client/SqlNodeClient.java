package org.opensearch.sql.client;

import org.opensearch.client.Client;
import org.opensearch.core.action.ActionListener;
import org.opensearch.sql.ppl.transport.PPLQueryAction;
import org.opensearch.sql.ppl.transport.TransportPPLQueryRequest;
import org.opensearch.sql.ppl.transport.TransportPPLQueryResponse;

public class SqlNodeClient implements SqlClient {
    Client client;
    @Override
    public void pplQuery(TransportPPLQueryRequest request, ActionListener<TransportPPLQueryResponse> listener) {
        client.execute(
            PPLQueryAction.INSTANCE,
            request,
            ActionListener.wrap(listener::onResponse, listener::onFailure)
        );
    }
}
