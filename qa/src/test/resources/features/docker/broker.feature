###############################################################################
# Copyright (c) 2019 Eurotech and/or its affiliates and others
#
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#     Eurotech - initial API and implementation
###############################################################################
@docker
Feature: Testing docker steps
  Test that documents functionality of docker steps.

  Scenario: Execute possible docker steps to show its usage
    For now it only lists docker images

    Given List images by name "kapua/kapua-broker:1.1.0-SNAPSHOT"
