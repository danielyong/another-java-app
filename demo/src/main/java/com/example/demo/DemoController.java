package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
public class DemoController {
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private CoursesRepository courseRepo;

	@GetMapping(path="/")
	public @ResponseBody String hi() {
		// This returns a JSON or XML with the users
		return "Hi";
	}

	@GetMapping(path="/student/list_student")
	public @ResponseBody List<Student> listStudents(){
		return studentRepo.findAll();
	}

	@GetMapping(path="/student/find_student")
	public @ResponseBody List<Student> findStudent(@RequestParam String studentName){
		return studentRepo.findByStudentName(studentName);
	}

	@PostMapping(path="/student/enroll_student")
	public @ResponseBody JSONObject enrollStudent(@RequestParam String studentName){
		JSONObject resp = new JSONObject();
		try{
			if(studentName == null || studentName.isEmpty()){ throw new Exception("Please give me a student name"); }

			List<Student> studList = studentRepo.findByStudentName(studentName);
			
			if(studList.size() > 0){ throw new Exception("This student already exist"); }

			Student stud = new Student();
			stud.setStudentName(studentName);
			studentRepo.save(stud);

			resp.put("status", true);
			resp.put("message", "Student added successfully");
		}catch(Exception e){
			resp.put("status", false);
			resp.put("message", e.getMessage());			
		}		

    	return resp;
	}

	@PostMapping(path="/student/enroll_new_course")
	public @ResponseBody JSONObject enrollNewCourse(@RequestParam Long studentId, @RequestParam Long courseId){
		JSONObject resp = new JSONObject();
		try{
			if(studentId == null){ throw new Exception("Please give me a student id"); }
			if(courseId == null){ throw new Exception("Please give me a course"); }

			Student stud = studentRepo.findById(studentId).orElseThrow();
			Courses course = courseRepo.findById(courseId).orElseThrow();

			List<Courses> enrolledCourses = stud.getEnrolledCourses();
			enrolledCourses.add(course);
			studentRepo.save(stud);

			resp.put("status", true);
			resp.put("message", "Course enrolled successfully");
		}catch(NoSuchElementException e){
			resp.put("status", false);
			resp.put("status", "Student or course not found");
		}catch(Exception e){
			resp.put("status", false);
			resp.put("message", e.getMessage());			
		}		

    	return resp;
	}

    @PostMapping(path="/courses/add_new_course")
  	public @ResponseBody JSONObject addNewCourse (@RequestParam String courseName) {

		Courses c = new Courses();
		c.setCourseName(courseName);
		courseRepo.save(c);

		JSONObject resp = new JSONObject();
		resp.put("status", true);
		resp.put("message", "Course added successfully");

    	return resp;
  	}

	@GetMapping(path="/courses/list_courses")
	public @ResponseBody Iterable<Courses> getAllCourses() {
		// This returns a JSON or XML with the users
		return courseRepo.findAll();
	}
}
