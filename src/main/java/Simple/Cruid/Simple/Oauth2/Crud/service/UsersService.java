package Simple.Cruid.Simple.Oauth2.Crud.service;

import Simple.Cruid.Simple.Oauth2.Crud.entities.Role;
import Simple.Cruid.Simple.Oauth2.Crud.entities.User;
import Simple.Cruid.Simple.Oauth2.Crud.repositories.RoleRepository;
import Simple.Cruid.Simple.Oauth2.Crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UsersService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public UsersService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            user.get().getRoles().size();
            user.get().getAuthorities().size();
            return user.get();
        }
        else throw new UsernameNotFoundException("User not found!");
    }

    public long count(){return userRepository.count();}
     public User createUser(String username,String password){
        return createUser(new User(username,password));
     }
     public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
     }
     public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles()==null || user.getRoles().size()==0){
            Optional<Role> optionalRole = roleRepository.findByNameHql("ROLE_USER");
            Role userRole = optionalRole.orElseGet(()-> roleRepository.save(new Role("ROLE_USER")));
            assert userRole.getId()!=null;
            user.setRoles(Collections.singleton(userRole));
        }
        else {
            Set<Role> roles = user.getRoles();
            Set<Role> persistedRoles = new HashSet<>(roles.size());
            for (Role role:roles){
                if(role.getId()==null){
                    Role savedRole = roleRepository.save(role);
                    assert  savedRole.getId()!=null;
                    persistedRoles.add(savedRole);
                }
                else persistedRoles.add(role);
            }
            user.setRoles(persistedRoles);
        }
        return userRepository.save(user);
     }

}
