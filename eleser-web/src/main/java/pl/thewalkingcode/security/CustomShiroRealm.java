package pl.thewalkingcode.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.mindrot.jbcrypt.BCrypt;
import pl.thewalkingcode.model.User;
import pl.thewalkingcode.repository.UserRepository;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashSet;
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

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == null) {
            throw new AuthenticationException("PrincipalCollection method argument cannot be null");
        }
        String username = (String) this.getAvailablePrincipal(principalCollection);
        User user = userRepository.findByLogin(username);
        Set<String> roleNames = null;
        if (user != null) {
            roleNames = new HashSet<>();
            roleNames.add(user.getRole().getName());
        }
        return new SimpleAuthorizationInfo(roleNames);
    }

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
        if (user.getPassword() == null) {
            throw new AccountException("Null password are not allowed");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword().toCharArray(), this.getName());
        super.clearCache(simpleAuthenticationInfo.getPrincipals());
        return simpleAuthenticationInfo;
    }

}
