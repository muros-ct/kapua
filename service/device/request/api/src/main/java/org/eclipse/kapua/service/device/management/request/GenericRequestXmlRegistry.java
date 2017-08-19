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
 *******************************************************************************/
package org.eclipse.kapua.service.device.management.request;

import org.eclipse.kapua.service.device.management.request.message.request.GenericRequestChannel;
import org.eclipse.kapua.service.device.management.request.message.request.GenericRequestMessage;
import org.eclipse.kapua.service.device.management.request.message.request.GenericRequestPayload;
import org.eclipse.kapua.service.device.management.request.message.response.GenericResponseChannel;
import org.eclipse.kapua.service.device.management.request.message.response.GenericResponseMessage;
import org.eclipse.kapua.service.device.management.request.message.response.GenericResponsePayload;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class GenericRequestXmlRegistry {

    @Inject
    private GenericRequestFactory factory;

    public GenericRequestChannel newRequestChannel() {
        return factory.newRequestChannel();
    }

    public GenericRequestPayload newRequestPayload() {
        return factory.newRequestPayload();
    }

    public GenericRequestMessage newRequestMessage() {
        return factory.newRequestMessage();
    }

    public GenericResponseChannel newResponseChannel() {
        return factory.newResponseChannel();
    }

    public GenericResponsePayload newResponsePayload() {
        return factory.newResponsePayload();
    }

    public GenericResponseMessage newResponseMessage() {
        return factory.newResponseMessage();
    }
}
