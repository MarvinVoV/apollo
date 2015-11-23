package sun.focusblog.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import sun.focusblog.admin.dao.IUserDao;
import sun.focusblog.admin.domain.auth.Role;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by root on 2015/11/7.
 * <p/>
 * Spring Security Service Level. Valid user.
 */
@Service("appUserDetailService")
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        sun.focusblog.admin.domain.auth.User user = userDao.query(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        String password = new String(Base64Utils.decodeFromString(user.getPassword()));
        boolean enabled = user.getStatus() > 0;
        boolean accountNonExpired = user.getStatus() > 0;
        boolean credentialsNonExpired = user.getStatus() > 0;
        boolean accountNonLocked = user.getStatus() > 0;

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new User(username, password, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

    }


}
