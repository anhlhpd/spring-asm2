package t1708e.springasm2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import t1708e.springasm2.entity.Student;
import t1708e.springasm2.service.StudentService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(Model model){
        model.addAttribute("student", new Student());
        return "student/form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String store(Model model, @Valid Student student, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("account", student);
            return "student/form";
        }
        studentService.create(student);
        return "redirect:/login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(Model model, @PathVariable int id){
        Student student = studentService.getById(id);
        if (student == null){
            return "error/404";
        }
        model.addAttribute("student", student);
        return "student/detail";
    }
}
