package org.eclipse.kapua.service.connection.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.account.Account;
import org.eclipse.kapua.service.account.AccountFactory;
import org.eclipse.kapua.service.account.AccountService;
import org.eclipse.kapua.service.authentication.AuthenticationService;
import org.eclipse.kapua.service.authentication.credential.CredentialService;
import org.eclipse.kapua.service.authorization.access.AccessInfoService;
import org.eclipse.kapua.service.device.registry.DeviceRegistryService;
import org.eclipse.kapua.service.device.steps.AclCreator;
import org.eclipse.kapua.service.device.steps.MqttDevice;
import org.eclipse.kapua.service.user.User;
import org.eclipse.kapua.service.user.UserFactory;
import org.eclipse.kapua.service.user.UserService;
import org.eclipse.kapua.service.user.steps.TestConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muros on 7/7/17.
 */
public class ConnectionSteps {

    /**
     * Authentication service.
     */
    private static AuthenticationService authenticationService;

    /**
     * Account service.
     */
    private static AccountService accountService;

    /**
     * Account factory.
     */
    private static AccountFactory accountFactory;

    /**
     * User service.
     */
    private static UserService userService;

    /**
     * User factory.
     */
    private static UserFactory userFactory;

    /**
     * Credential service.
     */
    private static CredentialService credentialService;

    /**
     * Accessinfo service.
     */
    private static AccessInfoService accessInfoService;

    private static DeviceRegistryService deviceRegistryService;

    /**
     * Helper for creating Accoutn, User and other artifacts needed in tests.
     */
    private static AclCreator aclCreator;

    @Before
    public void aclStepsBefore() {

        KapuaLocator locator = KapuaLocator.getInstance();
        authenticationService = locator.getService(AuthenticationService.class);
        accountService = locator.getService(AccountService.class);
        accountFactory = locator.getFactory(AccountFactory.class);
        userService = locator.getService(UserService.class);
        userFactory = locator.getFactory(UserFactory.class);
        credentialService = locator.getService(CredentialService.class);
        accessInfoService = locator.getService(AccessInfoService.class);
        deviceRegistryService = locator.getService(DeviceRegistryService.class);

        aclCreator = new AclCreator(accountService, accountFactory, userService, accessInfoService, credentialService);
    }

    @Given("^account and users$")
    public void createAccountAndUsers() throws Throwable {

        Account account = aclCreator.createAccount("test-acc-1","ACME Corp.", "john@acme.org");

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("infiniteChildEntities", true);
        valueMap.put("maxNumberChildEntities", 100);
        valueMap.put("deviceUserCouplingEnabled", false);
        valueMap.put("deviceUserCouplingDefaultMode", "LOOSE");

        deviceRegistryService.setConfigValues(account.getId(), account.getScopeId(), valueMap);

        User user1 = aclCreator.createUser(account, "test-acc-1");
        aclCreator.attachUserCredentials(account, user1);
        aclCreator.attachFullPermissions(account, user1);
        User user2 = aclCreator.createUser(account, "test-user-2");
        aclCreator.attachUserCredentials(account, user2);
        aclCreator.attachFullPermissions(account, user2);
        User user3 = aclCreator.createUser(account, "test-user-3");
        aclCreator.attachUserCredentials(account, user3);
        aclCreator.attachFullPermissions(account, user3);
    }

}
