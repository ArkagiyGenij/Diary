package com.example.diary.service;

import com.example.diary.model.Grade;
import com.example.diary.model.Group;
import com.example.diary.repo.GroupRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepo groupRepo;

    public GroupService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }
    public List<Group> getAllGroups(){
        return groupRepo.findAll();
    }
    public void saveGroup(Group group){
        this.groupRepo.save(group);
    }
    public Optional<Group> getGroupById(Long id){
        return groupRepo.findById(id);
    }
    public void deleteGroup(Long id){
        this.groupRepo.deleteById(id);
    }
}
