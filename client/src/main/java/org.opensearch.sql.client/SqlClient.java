package org.opensearch.sql.client;

import org.opensearch.core.action.ActionListener;
import org.opensearch.sql.ppl.transport.TransportPPLQueryRequest;
import org.opensearch.sql.ppl.transport.TransportPPLQueryResponse;

public interface SqlClient {
    void pplQuery(TransportPPLQueryRequest request, ActionListener<TransportPPLQueryResponse> listener);
}
