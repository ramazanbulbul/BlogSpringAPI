package me.ramazanbulbul.blog.services;

import me.ramazanbulbul.blog.entities.Role;
import me.ramazanbulbul.blog.entities.User;
import me.ramazanbulbul.blog.repos.RoleRepository;
import me.ramazanbulbul.blog.repos.UserRepository;
import me.ramazanbulbul.blog.utils.Hash;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class RoleService extends BaseService{
    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<Role> getAll(){
        return roleRepository.getAllByIsDeleted(false);
    }
    public Role add(Role role){
        return roleRepository.save(role);
    }
    public Role remove(Long roleId){
        Role role = roleRepository.getById(roleId);
        role.setDeleted(true);
        role.setDeletedTimezone(new Date());
        return roleRepository.save(role);
    }
}
