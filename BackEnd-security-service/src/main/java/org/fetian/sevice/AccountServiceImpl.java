package org.fetian.sevice;
import org.fetian.entities.AppUser;
import org.fetian.entities.AppRole;
import org.fetian.dao.AppUserRepository;
import org.fetian.dao.AppRoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public AppUser saveUser(String userName, String password, String confirmedPassword) {
        AppUser user=appUserRepository.findByUserName(userName );
        if(user!=null) throw new RuntimeException("User already exist ");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser=new AppUser();
        appUser.setActived(true);
        appUser.setUserName(userName);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        addRoleToUser(userName,"USER");
        return appUser;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUserName(String userName) {
        return appUserRepository.findByUserName(userName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser appuser=appUserRepository.findByUserName(userName);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appuser.getRoles().add(appRole);

    }
}
