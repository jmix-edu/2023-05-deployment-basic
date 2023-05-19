package com.company.pm.screen.user;

import com.company.pm.entity.User;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;

@UiController("pm_User.browse")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersTable")
@Route("users")
public class UserBrowse extends StandardLookup<User> {
}