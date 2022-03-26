package demo.controller;

import demo.entity.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import demo.pagination.pageRender;

@Controller
public class employeeController {

    @Autowired
    private demo.Service.employeeService employeeService;

    @GetMapping({"/","/listing",""})
    public String listingEmployees(@RequestParam(name = "page", defaultValue = "0") int page, Model model){
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Employees> employees = employeeService.findAll(pageRequest);
        pageRender<Employees> pageRender = new pageRender<>("/listing", employees);
        model.addAttribute("Title", "Employees List");
        model.addAttribute("employees", employees);
        model.addAttribute("Page", pageRender);

        return "listing";
    }
}