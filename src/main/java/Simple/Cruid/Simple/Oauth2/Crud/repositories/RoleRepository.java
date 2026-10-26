package Simple.Cruid.Simple.Oauth2.Crud.repositories;

import Simple.Cruid.Simple.Oauth2.Crud.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface RoleRepository extends CrudRepository<Role,Long> {
    @Query("select new Role(r.id,r.name) from Role r where r.name=:roleName")
    Optional<Role> findByNameHql(String roleName);
}
