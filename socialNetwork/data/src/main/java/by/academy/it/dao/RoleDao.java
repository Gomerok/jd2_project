package by.academy.it.dao;

import by.academy.it.pojo.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleDao {

    void addRole(Role role);

    List<Role> readRoleByName(String roleName);

    int deleteRoleByName(String roleName);
}
