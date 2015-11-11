package sun.yamorn.blog.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.yamorn.blog.admin.dao.IUserDao;
import sun.yamorn.blog.admin.domain.auth.IRole;
import sun.yamorn.blog.admin.domain.auth.IUser;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by root on 2015/11/7.
 *
 * Spring Security Service Level. Valid user.
 */
@Service("appUserDetailService")
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IUser user = userDao.query(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        String password = user.getPassword();
        boolean enabled = user.isEnable();
        boolean accountNonExpired = user.isEnable();
        boolean credentialsNonExpired = user.isEnable();
        boolean accountNonLocked = user.isEnable();

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (IRole role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new User(username, password, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

    }


}
