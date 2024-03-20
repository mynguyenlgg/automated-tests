package com.client.config;

import com.client.utils.JsonUtils;
import io.qameta.allure.attachment.DefaultAttachmentProcessor;
import io.qameta.allure.attachment.FreemarkerAttachmentRenderer;
import io.qameta.allure.attachment.http.HttpRequestAttachment;
import io.qameta.allure.attachment.http.HttpResponseAttachment;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.internal.NameAndValue;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllureRestAssuredLogger implements OrderedFilter {
    private static final Logger log = LoggerFactory.getLogger(AllureRestAssuredLogger.class);

    private String requestAttachmentName = "Request";
    private String responseAttachmentName = "Response";

    private String requestTemplatePath = "http-request.ftl";
    private String responseTemplatePath = "http-response.ftl";

    @Getter
    private HttpRequestAttachment requestAttachment;

    @Getter
    private HttpResponseAttachment responseAttachment;

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        Prettifier prettifier = new Prettifier();
        String url = requestSpec.getURI();
        HttpRequestAttachment.Builder requestAttachmentBuilder = HttpRequestAttachment.Builder.create(this.requestAttachmentName, url).setMethod(requestSpec.getMethod()).setHeaders(toMapConverter(requestSpec.getHeaders())).setCookies(toMapConverter(requestSpec.getCookies()));
        if (Objects.nonNull(requestSpec.getBody())) {
            requestAttachmentBuilder.setBody(prettifier.getPrettifiedBodyIfPossible(requestSpec));
        }

        HttpRequestAttachment requestAttachment = requestAttachmentBuilder.build();
        this.requestAttachment = requestAttachmentBuilder.build();
        (new DefaultAttachmentProcessor()).addAttachment(requestAttachment, new FreemarkerAttachmentRenderer(this.requestTemplatePath));

        Response response = filterContext.next(requestSpec, responseSpec);

        String attachmentName = (String) Optional.ofNullable(this.responseAttachmentName).orElse(response.getStatusLine());
        HttpResponseAttachment responseAttachment = io.qameta.allure.attachment.http.HttpResponseAttachment
                                                            .Builder.create(attachmentName)
                                                            .setResponseCode(response.getStatusCode())
                                                            .setHeaders(toMapConverter(response.getHeaders()))
                                                            .setBody(prettifier.getPrettifiedBodyIfPossible(response, response.getBody()))
                                                            .build();

        this.responseAttachment = responseAttachment;
        (new DefaultAttachmentProcessor()).addAttachment(responseAttachment, new FreemarkerAttachmentRenderer(this.responseTemplatePath));
        return response;
    }

    private static Map<String, String> toMapConverter(Iterable<? extends NameAndValue> items) {
        Map<String, String> result = new HashMap();
        items.forEach((h) -> {
            result.put(h.getName(), h.getValue());
        });
        return result;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

//
//    @Override
//    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
//        Response response = ctx.next(requestSpec, responseSpec);
//        if (response.statusCode() != 200) {
//            log.error(requestSpec.getMethod() + " " + requestSpec.getURI() + " => " +
//                    response.getStatusCode() + " " + response.getStatusLine());
//        }
//        log.info(requestSpec.getMethod() + " " + requestSpec.getURI() + " \n Request Body =>" + requestSpec.getBody() + "\n Response Status => " +
//                response.getStatusCode() + " " + response.getStatusLine() + " \n Response Body => " + response.getBody().prettyPrint());
//        return response;
//    }
}
