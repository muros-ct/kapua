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
package org.eclipse.kapua.service.device.management;

import javax.inject.Inject;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class KapuaAppPropertiesXmlAdapter extends XmlAdapter<String, KapuaAppProperties> {

    @Inject
    private KapuaRequestMessageFactory requestMessageFactory;


    @Override
    public KapuaAppProperties unmarshal(String v) throws Exception {
        return requestMessageFactory.newAppProperties(v);
    }

    @Override
    public String marshal(KapuaAppProperties v) throws Exception {
        return v.getValue();
    }
}
