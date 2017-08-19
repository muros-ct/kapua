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
package org.eclipse.kapua.service.device.registry;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlRegistry;

/**
 * Device xml factory class
 * 
 * @since 1.0
 *
 */
@XmlRegistry
public class DeviceXmlRegistry {

    @Inject
    private DeviceFactory factory;

    /**
     * Creates a new {@link Device}
     * 
     * @return
     */
    public Device newDevice() {
        return factory.newEntity(null);
    }

    /**
     * Creates a new device creator
     * 
     * @return
     */
    public DeviceCreator newDeviceCreator() {
        return factory.newCreator(null, null);
    }

    /**
     * Creates a new device list result
     * 
     * @return
     */
    public DeviceListResult newDeviceListResult() {
        return factory.newListResult();
    }

    public DeviceQuery newQuery() {
        return factory.newQuery(null);
    }
}
