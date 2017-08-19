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
package org.eclipse.kapua.service.device.management.bundle;

import javax.inject.Inject;

/**
 * Device bundle xml factory class
 *
 * @since 1.0
 *
 */
public class DeviceBundleXmlRegistry {

    @Inject
    private DeviceBundleFactory factory;

    /**
     * Creates a new device bundles list
     *
     * @return
     */
    public DeviceBundles newBundleListResult() {
        return factory.newBundleListResult();
    }

    /**
     * Creates a new device bundle
     *
     * @return
     */
    public DeviceBundle newDeviceBundle() {
        return factory.newDeviceBundle();
    }
}
