package com.example.entities;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CoursesRepository extends CrudRepository<Courses, Long> {
    public List<Courses> findByName(String name);
    public Courses findById(long id);
}
