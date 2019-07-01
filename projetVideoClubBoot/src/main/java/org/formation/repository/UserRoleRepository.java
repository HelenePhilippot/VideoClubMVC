package org.formation.repository;

import org.formation.metier.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public interface UserRoleRepository extends JpaRepository <UserRole, Integer>{

}
