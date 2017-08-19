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
package org.eclipse.kapua.service.device.management.packages.model;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlRegistry;

import org.eclipse.kapua.service.device.management.packages.DevicePackageFactory;
import org.eclipse.kapua.service.device.management.packages.model.download.DevicePackageDownloadRequest;
import org.eclipse.kapua.service.device.management.packages.model.uninstall.DevicePackageUninstallRequest;

/**
 * Device package xml factory class
 * 
 * @since 1.0
 *
 */
@XmlRegistry
public class DevicePackageXmlRegistry {

    @Inject
    private DevicePackageFactory factory;

    /**
     * Creates a new device package instance
     * 
     * @return
     */
    public DevicePackage newDevicePackage() {
        return factory.newDeviceDeploymentPackage();
    }

    /**
     * Creates a new device packages instance
     * 
     * @return
     */
    public DevicePackages newDevicePackages() {
        return factory.newDeviceDeploymentPackages();
    }

    /**
     * Creates a new device package bundle information instance
     * 
     * @return
     */
    public DevicePackageBundleInfo newDevicePackageBundleInfo() {
        return factory.newDevicePackageBundleInfo();
    }

    /**
     * Creates a new device package bundle informations instance
     * 
     * @return
     */
    public DevicePackageBundleInfos newDevicePackageBundleInfos() {
        return factory.newDevicePackageBundleInfos();
    }

    /**
     * Creates a new device package download request instance
     * 
     * @return
     */
    public DevicePackageDownloadRequest newDevicePackageDownloadRequest() {
        return factory.newPackageDownloadRequest();
    }

    /**
     * Creates a new device package uninstall request instance
     * 
     * @return
     */
    public DevicePackageUninstallRequest newDevicePackageUninstallRequest() {
        return factory.newPackageUninstallRequest();
    }
}
