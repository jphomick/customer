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

    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("states", stateRepository.findAll());
        return "search";
    }

    @PostMapping("/results")
    public String searchResults(Model model, Customer customer) {
        ArrayList<Customer> found = new ArrayList<>();

        for (Customer foundPerson : customerRepository.findAll()) {
            boolean match = true;
            if (!customer.getFirst_name().equals("") && !foundPerson.getFirst_name().contains(customer.getFirst_name())) {
                match = false;
            }
            if (!customer.getLast_name().equals("") && !foundPerson.getLast_name().contains(customer.getLast_name())) {
                match = false;
            }
            if (!customer.getTitle().equals("") && !foundPerson.getTitle().contains(customer.getTitle())) {
                match = false;
            }
            if (!customer.getStreet_address().equals("") && !foundPerson.getStreet_address().contains(customer.getStreet_address())) {
                match = false;
            }
            if (!customer.getZip_code().equals("") && !foundPerson.getZip_code().contains(customer.getZip_code())) {
                match = false;
            }
            if (!customer.getEmail_address().equals("") && !foundPerson.getEmail_address().contains(customer.getEmail_address())) {
                match = false;
            }

            if (match) {
                found.add(foundPerson);
            }
        }

        model.addAttribute("customers", found);

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

    @PostMapping("/process")
    public String processForm(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()){
            return "customerform";
        }
        customerRepository.save(customer);
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
