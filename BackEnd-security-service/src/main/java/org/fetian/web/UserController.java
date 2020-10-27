package org.fetian.web;

import lombok.Data;
import org.fetian.entities.AppUser;
import org.fetian.sevice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm){
       return accountService.saveUser(userForm.getUserName(),userForm.getPassword(),userForm.getConfirmedPassword() );
    }
}
@Data
class UserForm{
    private String userName;
    private String password;
    private  String confirmedPassword;
}