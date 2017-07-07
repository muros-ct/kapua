###############################################################################
# Copyright (c) 2017 Eurotech and/or its affiliates and others
#
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#     Eurotech - initial API and implementation
###############################################################################
Feature: User Coupling


  Scenario: Test user coupling

    Given I login as user with name "kapua-sys" and password "kapua-password"
    Given account and users
    And I logout

    Given The account name is test-acc-1 and the client ID is device-1
    And The broker URI is tcp://test-acc-1:kapua-password@localhost:1883
    And My credentials are username "test-acc-1" and password "kapua-password"

    When I start the simulator
    Then Device device-1 for account test-acc-1 is registered after 5 seconds
#    And Connection is established
    When I stop the simulator
    Then Device device-1 for account test-acc-1 is not registered after 5 seconds

#    Given The account name is test-acc-1 and the client ID is device-1
#    And The broker URI is tcp://test-user-1:kapua-password@localhost:1883
#    And My credentials are username "test-user-2" and password "kapua-password"
#
#    When I start the simulator
#    Then Device device-1 for account test-user-1 is registered after 5 seconds
##    And Connection is established
#    When I stop the simulator
#    Then Device device-1 for account test-user-1 is not registered after 5 second
