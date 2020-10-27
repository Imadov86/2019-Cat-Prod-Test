package org.fetian.sevice;

import org.fetian.entities.AppRole;
import org.fetian.entities.AppUser;

public interface AccountService {
    public AppUser saveUser(String userName,String password,String confirmedPassword);
    public AppRole saveRole(AppRole role);
    public  AppUser loadUserByUserName(String userName);
    public void addRoleToUser(String userName,String roleName);
}
