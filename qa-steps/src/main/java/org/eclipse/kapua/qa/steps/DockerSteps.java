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
package org.eclipse.kapua.qa.steps;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.messages.Image;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ScenarioScoped
public class DockerSteps {

    private static final Logger logger = LoggerFactory.getLogger(DockerSteps.class);

    private DockerClient docker;

    @Before
    public void setupDockerClient() {
        logger.info("Creating docker client.");
        try {
            docker = DefaultDockerClient.fromEnv().build();
        } catch (DockerCertificateException e) {
            logger.error("Could not connect to docker.");
            throw new RuntimeException("Could not connect to docker.");
        }
    }

    @Given("^List images by name \"(.*)\"$")
    public void listImages(String imageName) throws Exception {
        List<Image> images = docker.listImages(DockerClient.ListImagesParam.byName(imageName));
        if ((images != null) && (images.size() > 0)) {
            for (Image image: images) {
                logger.info("Image: " + image);
            }
        } else {
            logger.info("No docker images found.");
        }
    }
}