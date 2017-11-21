package pl.thewalkingcode;

import org.apache.shiro.authc.credential.PasswordMatcher;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class GeneratePassword {

    @Test
    public void genPassword() {
        PasswordMatcher passwordMatcher = new PasswordMatcher();
        String encrypt = passwordMatcher.getPasswordService().encryptPassword("admin");
        System.out.println(encrypt);
    }

}
