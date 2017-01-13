# Kapua data model

This document describes basics about data model behind Kapua services.  
Each service has its own data base schema. For example: User service is only concerned about schema based on
table **usr_user**.

Following chapters represent data from perspective of system as a whole.

Column data of each entity is not described in detail. This detail is available as part of database creation scripts.

## Account, User and Permissions

![alt text][user_account_permission_ER]
ER diagram describing Account, User and Permissions 

### act_account
Entity for account and hierarchy of accounts. This entity provides ways to create multi tenant
hierarchies of accounts. All other entities in data model have relation to account. Based on that, permissions
are managed on user account and other entities.

### usr_user
Entity for basic user data, such as name address, contact,...

### atht_credential
Entity containing users credentials, basically encrypted password.

### atht_access_token
Entity containing access token that is provided to user as part of authentication process.

### athz_access_info
This entity connects user to permissions that is / are in **athz_access_permission** entity.
This means that permission is tied to concrete user. User can have personal permissions as well
as permissions that are role based.

### athz_access_permission
Entity for user specific permissions. This entity is connected with user by **athz_access_info** entity.

### athz_access_role
Entity that connects user to a specific role or roles in **athz_role**.

### athz_role
Entity that describes role, mainly just name of the role and all the scope and creation data
that is part of all entities.

### athz_role_permission
Entity that connects role to all the permissions that that role has. For example that *admin* role
can have all the permissions.

![alt text][kapua_small]

[user_account_permission_ER]: user_account_permission_ER.png "User and account with permissions"
[kapua_small]: ../images/kapua-75.png "Kapua logo"