/*******************************************************************************
 * Copyright (c) 2011, 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.device.registry.event;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlRegistry;

/**
 * Device event xml factory class.
 * 
 * @since 1.0
 *
 */
@XmlRegistry
public class DeviceEventXmlRegistry {

    @Inject
    private DeviceEventFactory factory;

    /**
     * Creates a new device event
     * 
     * @return
     */
    public DeviceEvent newDeviceEvent() {
        return factory.newEntity(null);
    }

    /**
     * Creates a new device event list result
     * 
     * @return
     */
    public DeviceEventListResult newDeviceEventListResult() {
        return factory.newListResult();
    }

    public DeviceEventQuery newQuery() {
        return factory.newQuery(null);
    }
}
