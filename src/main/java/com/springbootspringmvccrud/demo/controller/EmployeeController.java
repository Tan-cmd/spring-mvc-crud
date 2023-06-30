package com.springbootspringmvccrud.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootspringmvccrud.demo.entity.Employee;
import com.springbootspringmvccrud.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// List employee
	@GetMapping("/list")
	public String listEmployees(Model model) {
		List<Employee> theEmployee = employeeService.findAll();

		model.addAttribute("employees", theEmployee);

		return "employees/list-employees";
	}

	// Form add employee
	@GetMapping("/showFormForAdd")
	public String addEpmloyee(Model model) {
		Employee theEmployee = new Employee();

		model.addAttribute("employee", theEmployee);

		return "employees/employee-form";
	}

	// Save method
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.save(theEmployee);

		return "redirect:/employees/list";
	}

	// Update method
	@GetMapping("/showFormForUpdate")
	public String updateEmployee(@RequestParam("employeeId") int id, Model model) {
		// get the employee from the service
		Employee theEmployee = employeeService.findById(id);

		// set employee in the model to prepopulate the form
		model.addAttribute("employee", theEmployee);

		// sent over to form
		return "employees/employee-form";
	}
	
	// Delete method
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
		employeeService.delete(id);

		return "redirect:/employees/list";
	}

}
