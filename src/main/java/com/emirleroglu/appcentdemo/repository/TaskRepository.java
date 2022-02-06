package com.emirleroglu.appcentdemo.repository;

import com.emirleroglu.appcentdemo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    //List<Task> findAllByAppUserId(Long appUserId);
    List<Task> findAllByUserId(long userId);

}
