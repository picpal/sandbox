package com.picpal.framework.core.utils;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

@Slf4j
public class HttpRequestUtils {
    private final HttpClient httpClient;

    // 기본 생성자 (디폴트 HttpClient 사용)
    public HttpRequestUtils() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // HTTP/2 사용
                .connectTimeout(Duration.ofSeconds(10)) // 연결 타임아웃 설정
                .build();
    }

    // HttpClient를 주입받는 생성자 (테스트에서 사용)
    public HttpRequestUtils(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * GET 요청을 보내고 응답을 문자열로 반환합니다.
     *
     * @param url     요청을 보낼 URL
     * @param headers 요청 헤더 (Optional)
     * @return 응답 문자열
     * @throws IOException          I/O 예외
     * @throws InterruptedException 요청이 인터럽트된 경우
     */
    public String sendGetRequest(String url, Map<String, String> headers) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = buildRequest(url, "GET", headers, null);
        return sendHttpRequest(request);
    }

    /**
     * POST 요청을 보내고 응답을 문자열로 반환합니다.
     *
     * @param url     요청을 보낼 URL
     * @param body    요청 본문 (JSON 등)
     * @param headers 요청 헤더 (Optional)
     * @return 응답 문자열
     * @throws IOException          I/O 예외
     * @throws InterruptedException 요청이 인터럽트된 경우
     */
    public String sendPostRequest(String url, String body, Map<String, String> headers) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = buildRequest(url, "POST", headers, body);
        return sendHttpRequest(request);
    }

    /**
     * PUT 요청을 보내고 응답을 문자열로 반환합니다.
     *
     * @param url     요청을 보낼 URL
     * @param body    요청 본문 (JSON 등)
     * @param headers 요청 헤더 (Optional)
     * @return 응답 문자열
     * @throws IOException          I/O 예외
     * @throws InterruptedException 요청이 인터럽트된 경우
     */
    public String sendPutRequest(String url, String body, Map<String, String> headers) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = buildRequest(url, "PUT", headers, body);
        return sendHttpRequest(request);
    }

    /**
     * DELETE 요청을 보내고 응답을 문자열로 반환합니다.
     *
     * @param url     요청을 보낼 URL
     * @param headers 요청 헤더 (Optional)
     * @return 응답 문자열
     * @throws IOException          I/O 예외
     * @throws InterruptedException 요청이 인터럽트된 경우
     */
    public String sendDeleteRequest(String url, Map<String, String> headers) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = buildRequest(url, "DELETE", headers, null);
        return sendHttpRequest(request);
    }

    // HttpRequest 생성
    private HttpRequest buildRequest(String url, String method, Map<String, String> headers, String body) throws URISyntaxException {
        // URI 유효성 검사
        URI uri = validateAndCreateUri(url);

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(Duration.ofSeconds(5)) // 요청 타임아웃 설정
                .header("User-Agent", "[서비스 이름으로 변경]/1.0") // User-Agent 헤더 추가 [요청 보낸 클라이언트 식별 및 분석에 사용]
                .header("Accept", "application/json") // JSON 응답 수락 [어떤 형식의 데이터를 받을 수 있는지 기재. 클라이언트와 서버간 데이터 형식을 일관성 있게 유지 가능]
                .header("Content-Security-Policy", "default-src 'self'"); // 보안 정책 헤더 추가 [악성 스크립트의 실행을 방지 , 허용된 출처 외의 자원 로딩을 차단 ]

        // 요청 헤더 추가
        if (headers != null) {
            headers.forEach(requestBuilder::header);
        }

        switch (method.toUpperCase()) {
            case "GET" -> requestBuilder.GET();
            case "POST" -> requestBuilder.POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8));
            case "PUT" -> requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8));
            case "DELETE" -> requestBuilder.DELETE();
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        return requestBuilder.build();
    }

    // HttpRequest 전송 및 응답 처리
    private String sendHttpRequest(HttpRequest request) throws IOException, InterruptedException {
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (response.statusCode() != 200) {
                // 민감한 데이터 로깅 방지: 로깅에 요청 또는 응답 본문 포함 금지
                log.error("Failed " + request.method() + " request. Status code: {0}", response.statusCode());
                throw new IOException("Failed " + request.method() + " request: " + response.statusCode());
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            log.error("HTTP request failed: " + e.getMessage(), e);
            throw e;
        }
    }

    // URI 유효성 검증 및 생성
    private URI validateAndCreateUri(String url) throws URISyntaxException {
        // 객체 생성을 통한 URI 유효성 확인
        URI uri = new URI(url);

        // 프로토콜 검사
        if (!"http".equalsIgnoreCase(uri.getScheme()) && !"https".equalsIgnoreCase(uri.getScheme())) {
            throw new URISyntaxException(url, "Only HTTP/HTTPS protocols are supported.");
        }
        return uri;
    }

}
