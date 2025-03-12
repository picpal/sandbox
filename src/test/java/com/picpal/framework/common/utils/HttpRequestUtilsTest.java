package com.picpal.framework.common.utils;

import com.picpal.framework.core.utils.HttpRequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
class HttpRequestUtilsTest {

    private HttpClient mockHttpClient;
    private HttpResponse<String> mockResponse;
    private com.picpal.framework.core.utils.HttpRequestUtils HttpRequestUtils;

    @BeforeEach
    void setUp() {
        mockHttpClient = mock(HttpClient.class);
        mockResponse = mock(HttpResponse.class);
        HttpRequestUtils = new HttpRequestUtils(mockHttpClient); // Mock HttpClient 주입
    }

    @Disabled
    @Test
    void sendGetRequest_shouldReturnExpectedResponse() throws IOException, InterruptedException, URISyntaxException {
        // Given
        String expectedResponse = "{\"status\":\"success\",\"message\":\"Data selected succes!!!\"}";
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(expectedResponse);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // When
        String response = HttpRequestUtils.sendGetRequest("https://example.com/api/v1/sample.do", null);
        log.info(response);

        // Then
        assertEquals(expectedResponse, response);
    }

    @Disabled
    @Test
    void sendPostRequest_shouldReturnExpectedResponse() throws IOException, InterruptedException, URISyntaxException {
        // Given
        String expectedResponse = "{\"status\":\"success\",\"message\":\"Data saved success!!!\"}";
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(expectedResponse);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // When
        String response = HttpRequestUtils.sendPostRequest("https://example.com/api/v1/sample.do", "{\"name\":\"John\"}", null);
        log.info(response);

        // Then
        assertEquals(expectedResponse, response);
    }
}

