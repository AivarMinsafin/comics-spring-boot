package ru.itis.aivar.comics.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.aivar.comics.app.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
