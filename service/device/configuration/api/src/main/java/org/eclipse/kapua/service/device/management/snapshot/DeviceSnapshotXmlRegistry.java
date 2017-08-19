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
package org.eclipse.kapua.service.device.management.snapshot;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlRegistry;

/**
 * Device bundle xml factory class
 * 
 * @since 1.0
 *
 */
@XmlRegistry
public class DeviceSnapshotXmlRegistry {

    @Inject
    private DeviceSnapshotFactory factory;

    /**
     * Creates a new device snapshots list
     * 
     * @return
     */
    public DeviceSnapshots newDeviceSnapshots() {
        return factory.newDeviceSnapshots();
    }

    /**
     * Creates a new device snapshot
     * 
     * @return
     */
    public DeviceSnapshot newDeviceSnapshot() {
        return factory.newDeviceSnapshot();
    }
}
