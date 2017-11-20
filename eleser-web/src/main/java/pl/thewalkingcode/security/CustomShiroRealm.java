package pl.thewalkingcode.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import pl.thewalkingcode.model.User;
import pl.thewalkingcode.repository.UserRepository;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Set;

public class CustomShiroRealm extends AuthorizingRealm {

    private UserRepository userRepository;

    public CustomShiroRealm() {
        BeanManager beanManager;
        try {
            beanManager = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
            Bean<UserRepository> userRepositoryBean = (Bean<UserRepository>) beanManager.resolve(beanManager.getBeans(UserRepository.class));
            CreationalContext<UserRepository> creationalContext = beanManager.createCreationalContext(null);
            userRepository = userRepositoryBean.create(creationalContext);
            creationalContext.release();
        } catch (NamingException ne) {
            ne.printStackTrace();
        }
    }

    //TODO roles for user
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == null) {
            throw new AuthenticationException("PrincipalCollection method argument cannot be null");
        }
        String username = (String) this.getAvailablePrincipal(principalCollection);
        Set<String> roleNames = null;
        return new SimpleAuthorizationInfo(roleNames);
    }

    //TODO salt password
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        if (username == null) {
            throw new AccountException("Null usernames are not allowed");
        }
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new AccountException("User with this username [" + username + "] does not exists");
        }
        if (user.getPassword() == null || user.getSalt() == null) {
            throw new AccountException("Null password are not allowed");
        }
        return new SimpleAuthenticationInfo(username, user.getPassword().toCharArray(), this.getName());
    }

}
