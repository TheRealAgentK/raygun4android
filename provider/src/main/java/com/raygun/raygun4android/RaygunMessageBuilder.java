package com.raygun.raygun4android;

import android.content.Context;

import com.raygun.raygun4android.messages.crashreporting.RaygunBreadcrumbMessage;
import com.raygun.raygun4android.messages.crashreporting.RaygunClientMessage;
import com.raygun.raygun4android.messages.crashreporting.RaygunEnvironmentMessage;
import com.raygun.raygun4android.messages.crashreporting.RaygunErrorMessage;
import com.raygun.raygun4android.messages.crashreporting.RaygunMessage;

import java.util.List;
import java.util.Map;

public class RaygunMessageBuilder implements IRaygunMessageBuilder {
    private RaygunMessage raygunMessage;

    private RaygunMessageBuilder() {
        raygunMessage = new RaygunMessage();
    }

    @Override
    public RaygunMessage build() {
        return raygunMessage;
    }

    @Override
    public IRaygunMessageBuilder setMachineName(String machineName) {
        raygunMessage.getDetails().setMachineName(machineName);
        return this;
    }

    @Override
    public IRaygunMessageBuilder setExceptionDetails(Throwable throwable) {
        raygunMessage.getDetails().setError(new RaygunErrorMessage(throwable));
        return this;
    }

    @Override
    public IRaygunMessageBuilder setClientDetails() {
        raygunMessage.getDetails().setClient(new RaygunClientMessage());
        return this;
    }

    @Override
    public IRaygunMessageBuilder setEnvironmentDetails(Context context) {
        raygunMessage.getDetails().setEnvironment(new RaygunEnvironmentMessage(context));
        return this;
    }

    @Override
    public IRaygunMessageBuilder setVersion(String version) {
        raygunMessage.getDetails().setVersion(version);
        return this;
    }

    @Override
    public IRaygunMessageBuilder setTags(List tags) {
        raygunMessage.getDetails().setTags(tags);
        return this;
    }

    @Override
    public IRaygunMessageBuilder setCustomData(Map customData) {
        raygunMessage.getDetails().setCustomData(customData);
        return this;
    }

    @Override
    public IRaygunMessageBuilder setAppContext(String identifier) {
        raygunMessage.getDetails().setAppContext(identifier);
        return this;
    }

    @Override
    public IRaygunMessageBuilder setUserInfo() {
        raygunMessage.getDetails().setUserInfo();
        return this;
    }

    @Override
    public IRaygunMessageBuilder setNetworkInfo(Context context) {
        raygunMessage.getDetails().setNetworkInfo(context);
        return this;
    }

    @Override
    public IRaygunMessageBuilder setGroupingKey(String groupingKey) {
        raygunMessage.getDetails().setGroupingKey(groupingKey);
        return this;
    }

    public IRaygunMessageBuilder setBreadcrumbs(List<RaygunBreadcrumbMessage> breadcrumbs) {
        raygunMessage.getDetails().setBreadcrumbs(breadcrumbs);
        return this;
    }

    public static RaygunMessageBuilder instance() {
        return new RaygunMessageBuilder();
    }
}
