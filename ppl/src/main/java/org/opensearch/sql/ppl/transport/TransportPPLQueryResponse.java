package org.opensearch.sql.ppl.transport;

/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.opensearch.core.action.ActionResponse;
import org.opensearch.core.common.io.stream.InputStreamStreamInput;
import org.opensearch.core.common.io.stream.OutputStreamStreamOutput;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;
import org.opensearch.core.xcontent.ToXContentObject;
import org.opensearch.core.xcontent.XContentBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

@RequiredArgsConstructor
public class TransportPPLQueryResponse extends ActionResponse implements ToXContentObject {
    @Getter
    private final String result;

    public TransportPPLQueryResponse(StreamInput in) throws IOException {
        super(in);
        result = in.readString();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(result);
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject();
        if (result != null) {
            builder.field("result", result);
        }
        return builder.endObject();
    }

    public static TransportPPLQueryResponse fromActionResponse(ActionResponse actionResponse) {
        if (actionResponse instanceof TransportPPLQueryResponse) {
            return (TransportPPLQueryResponse) actionResponse;
        }
        try (
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); OutputStreamStreamOutput osso = new OutputStreamStreamOutput(baos)
        ) {
            actionResponse.writeTo(osso);
            try (StreamInput input = new InputStreamStreamInput(new ByteArrayInputStream(baos.toByteArray()))) {
                return new TransportPPLQueryResponse(input);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("failed to parse ActionResponse into TransportPPLQueryResponse", e);
        }

    }
}

