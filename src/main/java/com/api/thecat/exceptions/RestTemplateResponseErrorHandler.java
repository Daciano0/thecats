package com.api.thecat.exceptions;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Log4j2
@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {


    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response)
            throws IOException {

        Map<String, String> errorMap = extractErrorDetailsFromResponse(response);

        if (response.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            throw new OrderException("Internal server error. "+errorMap.get("message"));
        } else if (response.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {

            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException(errorMap.get("message"));
            }

            if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {

                throw new OrderException(errorMap.get("message"));
            }

            if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new UnauthorizedException(errorMap.get("message"));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> extractErrorDetailsFromResponse(ClientHttpResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());

        return (Map<String, String>) mapper.readValue(response.getBody(), Map.class);

    }
}