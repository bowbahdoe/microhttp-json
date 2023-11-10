package dev.mccue.microhttp.json;

import dev.mccue.json.Json;
import dev.mccue.json.JsonEncodable;
import dev.mccue.microhttp.handler.IntoResponse;
import dev.mccue.reasonphrase.ReasonPhrase;
import org.microhttp.Header;
import org.microhttp.Response;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of {@link IntoResponse} which encodes a blob of {@link Json}
 * as its body.
 *
 * @param status The status code the response.
 * @param headers The headers to include with the response. {@code Content-Type} is automatically added.
 * @param body A {@link JsonEncodable} to use as the body.
 */
public record JsonResponse(
        int status,
        List<Header> headers,
        JsonEncodable body
) implements IntoResponse {
    /**
     * Construct a new {@link JsonResponse} with a status code of 200 and no extra headers.
     * @param body The body to use for the {@link JsonResponse}.
     */
    public JsonResponse(JsonEncodable body) {
        this(200, List.of(), body);
    }

    /**
     * Construct a new {@link JsonResponse} with no extra headers.
     * @param status The status code.
     * @param body The body to use.
     */
    public JsonResponse(int status, JsonEncodable body) {
        this(status, List.of(), body);
    }

    @Override
    public Response intoResponse() {
        var headers = new ArrayList<>(headers());
        headers.add(
                new Header("Content-Type", "application/json; charset=utf-8")
        );
        return new Response(
                status,
                ReasonPhrase.forStatus(status),
                Collections.unmodifiableList(headers),
                Json.writeString(body).getBytes(StandardCharsets.UTF_8)
        );
    }
}
