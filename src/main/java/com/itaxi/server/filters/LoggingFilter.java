package com.itaxi.server.filters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    protected static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        MDC.put("traceId", UUID.randomUUID().toString());
        doFilterWrapped(new RequestWrapper(request), new ResponseWrapper(response), filterChain);
        MDC.clear();
    }

    public void doFilterWrapped(
            RequestWrapper request,
            ContentCachingResponseWrapper response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            logRequest(request);
            filterChain.doFilter(request, response);
        } finally {
            logResponse(response);
            response.copyBodyToResponse();
        }
    }

    private static void logRequest(RequestWrapper request) throws IOException {
        String queryString = request.getQueryString();
        String requestURI = request.getRequestURI();
        if (!Objects.isNull(queryString)) {
            requestURI += queryString;
        }
        log.info("Request : {} uri=[{}] content-type=[{}]",
                request.getMethod(),
                requestURI,
                request.getContentType()
        );

        logPayload("Request", request.getContentType(), request.getInputStream());
    }

    private static void logResponse(ContentCachingResponseWrapper response) throws IOException {
        logPayload("Response", response.getContentType(), response.getContentInputStream());
    }

    private static void logPayload(String prefix, String contentType, InputStream inputStream) throws IOException {
        if (Objects.isNull(contentType)) {
            contentType = "application/json";
        }

        boolean visible = isVisible(MediaType.valueOf(contentType));
        if (visible) {
            logContentPayload(prefix, inputStream);
            return;
        }
        log.info("{} Payload: Binary Content", prefix);
    }

    private static void logContentPayload(String prefix, InputStream inputStream) throws IOException {
        byte[] content = StreamUtils.copyToByteArray(inputStream);
        if (content.length > 0) {
            String contentString = new String(content);
            log.info("{} Payload: {}", prefix, contentString);
        }
    }

    private static boolean isVisible(MediaType mediaType) {
        final List<MediaType> VISIBLE_TYPES = Arrays.asList(
                MediaType.valueOf("text/*"),
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_XML,
                MediaType.valueOf("application/*+json"),
                MediaType.valueOf("application/*+xml"),
                MediaType.MULTIPART_FORM_DATA
        );

        return VISIBLE_TYPES.stream()
                            .anyMatch(visibleType -> visibleType.includes(mediaType));
    }
}