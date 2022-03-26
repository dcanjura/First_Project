package demo.controller;

import demo.entity.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import demo.pagination.pageRender;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class employeeController {

    @Autowired
    private demo.Service.employeeService employeeService;

    @GetMapping("view/{id}")
    public String viewEmployeeDetails(@PathVariable(value= "id") Long id, Map<String, Object> model, RedirectAttributes flash){
        Employees employee = employeeService.findEmployee(id);
        if(employee == null){
            flash.addAttribute("error", "Employee does not exist in DataBase");

            return "redirect:/listing";
        }
        model.put("employee", employee);
        model.put("title", "Employee Details" + employee.getName());

        return "view";
    }

    @GetMapping({"/","/listing",""})
    public String listingEmployees(@RequestParam(name = "page", defaultValue = "0") int page, Model model){
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Employees> employees = employeeService.findAll(pageRequest);
        pageRender<Employees> pageRender = new pageRender<>("/listing", employees);
        model.addAttribute("Title", "Employees List");
        model.addAttribute("employees", employees);
        model.addAttribute("page", pageRender);

        return "listing";
    }

    @GetMapping("/form")
    public String showForm(Map<String, Object> model){
        Employees employee = new Employees();
        model.put("employee", employee);
        model.put("title", "Employee Record");

        return "form";
    }

    @PostMapping("/form")
    public String saveEmployee(@Valid Employees employee, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status){
        if(result.hasErrors()){
            model.addAttribute("title", "Employee Record");

            return "form";
        }
        String message = (employee.getId() != null) ? "The employee has been edited successfully" : "Employee recorded successfully";
        employeeService.Save(employee);
        status.setComplete();
        flash.addFlashAttribute("success", message);

        return "redirect:/listing";
    }

    @GetMapping("/form/{id}")
    public String updateEmployee(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash){
        Employees employee = null;
        if(id > 0){
            employee = employeeService.findEmployee(id);
            if(employee == null){
                flash.addFlashAttribute("error", "The employee does not exist in the DataBase");

                return "redirect:/listing";
            }
        }
        else{
            flash.addAttribute("error", "The employee id cannot be 0");

            return "redirect:/listing";
        }
        model.put("employee", employee);
        model.put("title", "Employee Modification");

        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id, RedirectAttributes flash){
        if(id > 0){
            employeeService.Delete(id);
            flash.addFlashAttribute("success", "Employee record deleted successfully");
        }

        return "redirect:/listing";
    }
}