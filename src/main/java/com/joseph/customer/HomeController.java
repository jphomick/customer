package com.joseph.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class HomeController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PositionRepository positionRepository;

    @RequestMapping("/")
    public String listCourses(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        ArrayList<String> states = new ArrayList<>();
        int curr = 1;
        for (State state : stateRepository.findAll()) {
            states.add(stateRepository.findById((long) curr).get().getLetter_code());
            curr++;
        }
        model.addAttribute("states", states);
        ArrayList<String> cities = new ArrayList<>();
        curr = 1;
        for (City city : cityRepository.findAll()) {
            cities.add(cityRepository.findById((long) curr).get().getName());
            curr++;
        }
        model.addAttribute("cities", cities);
        ArrayList<String> positions = new ArrayList<>();
        curr = 1;
        for (Position position : positionRepository.findAll()) {
            positions.add(positionRepository.findById((long) curr).get().getName());
            curr++;
        }
        model.addAttribute("positions", positions);
        ArrayList<String> companies = new ArrayList<>();
        curr = 1;
        for (Company company : companyRepository.findAll()) {
            companies.add(companyRepository.findById((long) curr).get().getName());
            curr++;
        }
        model.addAttribute("companies", companies);
        return "list";
    }

    @GetMapping("/add")
    public String courseForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Customer course, BindingResult result) {
        if (result.hasErrors()){
            return "customerform";
        }
        customerRepository.save(course);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id).get());
        return "customerform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id) {
        customerRepository.deleteById(id);
        return "redirect:/";
    }
}
