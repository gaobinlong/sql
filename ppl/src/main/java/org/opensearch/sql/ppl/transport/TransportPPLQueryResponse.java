package org.opensearch.sql.ppl.transport;

/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.opensearch.core.action.ActionResponse;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;

import java.io.IOException;

@RequiredArgsConstructor
public class TransportPPLQueryResponse extends ActionResponse {
    @Getter private final String result;

    public TransportPPLQueryResponse(StreamInput in) throws IOException {
        super(in);
        result = in.readString();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(result);
    }
}

