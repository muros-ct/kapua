/*******************************************************************************
 * Copyright (c) 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *     Red Hat Inc
 *******************************************************************************/
package org.eclipse.kapua.service.device.management.request.internal;

import org.eclipse.kapua.KapuaErrorCodes;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.KapuaRuntimeException;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.authorization.domain.Domain;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;
import org.eclipse.kapua.service.device.management.commons.DeviceManagementDomain;
import org.eclipse.kapua.service.device.management.commons.call.DeviceCallExecutor;
import org.eclipse.kapua.service.device.management.request.DeviceRequestManagementService;
import org.eclipse.kapua.service.device.management.request.GenericRequestFactory;
import org.eclipse.kapua.service.device.management.request.message.request.GenericRequestChannel;
import org.eclipse.kapua.service.device.management.request.message.request.GenericRequestMessage;
import org.eclipse.kapua.service.device.management.request.message.request.GenericRequestPayload;
import org.eclipse.kapua.service.device.management.request.message.response.GenericResponseMessage;
import org.eclipse.kapua.service.device.registry.event.DeviceEventCreator;
import org.eclipse.kapua.service.device.registry.event.DeviceEventFactory;
import org.eclipse.kapua.service.device.registry.event.DeviceEventService;

import javax.inject.Inject;
import java.util.Date;

@KapuaProvider
public class DeviceRequestManagementServiceImpl implements DeviceRequestManagementService {

    private static final Domain DEVICE_MANAGEMENT_DOMAIN = new DeviceManagementDomain();

    @Inject
    private GenericRequestFactory factory;

    @Inject
    private AuthorizationService authorizationService;

    @Inject
    private PermissionFactory permissionFactory;

    @Inject
    private DeviceEventService deviceEventService;

    @Inject
    private DeviceEventFactory deviceEventFactory;

    @Override
    public GenericResponseMessage exec(
            GenericRequestMessage requestInput,
            Long timeout) throws KapuaException {
        //
        // Argument Validation
        ArgumentValidator.notNull(requestInput, "requestInput");

        //
        // Check Access
        Actions action;
        switch (requestInput.getChannel().getMethod()) {
        case EXECUTE:
            action = Actions.execute;
            break;
        case READ:
        case OPTIONS:
            action = Actions.read;
            break;
        case CREATE:
        case WRITE:
            action = Actions.write;
            break;
        case DELETE:
            action = Actions.delete;
            break;
        default:
            throw new KapuaRuntimeException(KapuaErrorCodes.OPERATION_NOT_SUPPORTED);
        }
        authorizationService.checkPermission(permissionFactory.newPermission(DEVICE_MANAGEMENT_DOMAIN, action, requestInput.getScopeId()));

        //
        // Prepare the request
        GenericRequestChannel genericRequestChannel = factory.newRequestChannel();
        genericRequestChannel.setAppName(requestInput.getChannel().getAppName());
        genericRequestChannel.setVersion(requestInput.getChannel().getVersion());
        genericRequestChannel.setMethod(requestInput.getChannel().getMethod());
        genericRequestChannel.setResources(requestInput.getChannel().getResources());

        GenericRequestPayload genericRequestPayload = factory.newRequestPayload();
        genericRequestPayload.setMetrics(requestInput.getPayload().getMetrics());
        genericRequestPayload.setBody(requestInput.getPayload().getBody());

        GenericRequestMessage genericRequestMessage = factory.newRequestMessage();
        genericRequestMessage.setScopeId(requestInput.getScopeId());
        genericRequestMessage.setDeviceId(requestInput.getDeviceId());
        genericRequestMessage.setCapturedOn(new Date());
        genericRequestMessage.setChannel(genericRequestChannel);
        genericRequestMessage.setPayload(genericRequestPayload);
        genericRequestMessage.setPosition(requestInput.getPosition());

        //
        // Do exec
        DeviceCallExecutor<?, ?, ?, GenericResponseMessage> deviceApplicationCall = new DeviceCallExecutor<>(genericRequestMessage, timeout);
        GenericResponseMessage responseMessage = deviceApplicationCall.send();

        //
        // Create event
        DeviceEventCreator deviceEventCreator = deviceEventFactory
                .newCreator(requestInput.getScopeId(), requestInput.getDeviceId(), responseMessage.getReceivedOn(), requestInput.getChannel().getAppName().getValue());
        deviceEventCreator.setPosition(responseMessage.getPosition());
        deviceEventCreator.setSentOn(responseMessage.getSentOn());
        deviceEventCreator.setAction(genericRequestChannel.getMethod());
        deviceEventCreator.setResponseCode(responseMessage.getResponseCode());
        deviceEventCreator.setEventMessage(responseMessage.getPayload().toDisplayString());

        deviceEventService.create(deviceEventCreator);

        return responseMessage;
    }
}
