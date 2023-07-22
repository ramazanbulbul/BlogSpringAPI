package me.ramazanbulbul.blog.services;

import me.ramazanbulbul.blog.entities.Role;
import me.ramazanbulbul.blog.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
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
        Role role = roleRepository.findById(roleId).orElseThrow();
        role.setDeleted(true);
        role.setDeletedTimestamp(new Date());
        return roleRepository.save(role);
    }
}
