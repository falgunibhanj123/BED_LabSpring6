package com.greatlearning.students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.students.model.Student;
import com.greatlearning.students.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService service;

	@GetMapping("/list")
	public String getAllStudents(Model model) {
		List<Student> student = service.getAll();
		model.addAttribute("students", student);

		return "list-studs";
	}

	@GetMapping("/showFormForAdd")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "stud-form";
	}

	@PostMapping("/showFormForUpdate")
	public String updateStudent(Model model, @RequestParam("studentId") int studId) {
		Student student = service.getById(studId);
		model.addAttribute("student", student);
		return "stud-form";
	}

	@PostMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int studId) {
		service.deleteById(studId);
		return "redirect:/students/list";
	}

	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student stud) {
		if (stud.getId() != 0) {
			service.update(stud);
		} else {
			service.create(stud);
		}
		return "redirect:/students/list";
	}

	@PostMapping("/403")
	public String access(Model model) {
		return "/403";
	}
}
