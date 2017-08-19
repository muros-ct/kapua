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
package org.eclipse.kapua.service.device.management.command;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlRegistry;

/**
 * Device bundle xml factory class
 * 
 * @since 1.0
 *
 */
@XmlRegistry
public class DeviceCommandXmlRegistry {

    @Inject
    private DeviceCommandFactory factory;

    /**
     * Creates a new device command input
     * 
     * @return
     */
    public DeviceCommandInput newCommandInput() {
        return factory.newCommandInput();
    }

    /**
     * Creates a new device command output
     * 
     * @return
     */
    public DeviceCommandOutput newCommandOutput() {
        return factory.newCommandOutput();
    }

}
