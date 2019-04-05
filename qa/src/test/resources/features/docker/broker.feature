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
    #And Pull image "kapua/kapua-sql:1.1.0-SNAPSHOT"
    And Pull image "elasticsearch:5.4.0"
    And Start DB container with name "db"
    Then I wait 15 seconds
#    And Prepare Event broker container
#    And Start Event Broker container
#    And Prepare Elasticsearch container
#    And Start Elasticsearch container
#    And Prepare Message Broker container
#      | name     | brokerAddress  | brokerIp | clusterName  | mqttPort | mqttHostPort | mqttsPort | mqttsHostPort | webPort | webHostPort | debugPort | debugHostPort | brokerInternalDebugPort| dockerImage |
#      | broker-1 | broker         | 0.0.0.0  | test-cluster | 1883     | 1883         | 8883      | 8883          | 8161    | 8161        | 9999      | 9999          | 9991                   | kapua/kapua-broker:1.1.0-SNAPSHOT |
    Then Stop container with name "db"