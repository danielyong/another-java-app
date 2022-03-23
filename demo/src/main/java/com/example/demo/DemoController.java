package com.example.demo;

import com.example.entities.Courses;
import com.example.entities.CoursesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@Autowired 
	private CoursesRepository courseRepo;

    @PostMapping(path="/add_new_course")
  	public @ResponseBody String addNewCourse (@RequestParam String name) {

		Courses c = new Courses();
		c.setCourseName(name);
		courseRepo.save(c);
    	return "Saved";
  	}

	@GetMapping(path="/list_courses")
	public @ResponseBody Iterable<Courses> getAllCourses() {
		// This returns a JSON or XML with the users
		return courseRepo.findAll();
	}
}
