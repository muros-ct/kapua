/*******************************************************************************
 * Copyright (c) 2019 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech
 *******************************************************************************/
package org.eclipse.kapua.docker;

import cucumber.api.CucumberOptions;
import org.eclipse.kapua.test.cucumber.CucumberProperty;
import org.eclipse.kapua.test.cucumber.CucumberWithProperties;
import org.junit.runner.RunWith;

@RunWith(CucumberWithProperties.class)
@CucumberOptions(
        features = "classpath:features/docker/broker.feature",
        glue = {"org.eclipse.kapua.qa.steps"
        },
        plugin = { "pretty",
                "html:target/cucumber/DockerBroker",
                "json:target/DockerBroker_cucumber.json"
        },
        monochrome = true)
@CucumberProperty(key="DOCKER_HOST", value= "127.0.0.1")
//@CucumberProperty(key="DOCKER_CERT_PATH", value= "...")
public class RunDockerBrokerTest {
}
