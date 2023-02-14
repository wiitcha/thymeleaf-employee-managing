package com.emir.securitydemo.controller;

import com.emir.securitydemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String home(Model model,
                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "size", required = false, defaultValue = "2") Integer size,
                       @RequestParam(value = "search", required = false, defaultValue = "") String searchParameter) {

        List<Integer> pages = new ArrayList<>();
        int numberOfEmployees;
        int numberOfPages;

        Pageable pageable = PageRequest.of(page-1, size);

        employeeRepository.count();

        if (searchParameter.isEmpty()) {
            numberOfEmployees = (int) employeeRepository.count();
            model.addAttribute("employees", employeeRepository.findAll(pageable));
        } else {
            numberOfEmployees = employeeRepository.findFilteredEmployees(searchParameter, pageable).getSize();
            model.addAttribute("employees", employeeRepository.findFilteredEmployees(searchParameter, pageable));
        }

        if (numberOfEmployees % size == 0) {
            numberOfPages =  numberOfEmployees / size;
        } else {
            numberOfPages = (numberOfEmployees / size) + 1;
        }

        for (int i = 1; i <= numberOfPages; i++) {
            pages.add(i);
        }

        model.addAttribute("numOfPages", pages);





        return "employees/home";
    }

}
