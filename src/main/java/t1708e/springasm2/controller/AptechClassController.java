package t1708e.springasm2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import t1708e.springasm2.entity.AptechClass;
import t1708e.springasm2.entity.Student;
import t1708e.springasm2.repository.AptechClassRepository;
import t1708e.springasm2.repository.StudentRepository;
import t1708e.springasm2.service.AptechClassService;
import t1708e.springasm2.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/aptechClass")
public class AptechClassController {
    @Autowired
    AptechClassService aptechClassService;

    @Autowired
    StudentService studentService;

    @Autowired
    AptechClassRepository aptechClassRepository;

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable int id, Model model) {
        AptechClass aptechClass = aptechClassService.findById(id);
        List<Student> studentList = studentService.getList();
        if (aptechClass == null) {
            return "error/404";
        }
        model.addAttribute("studentList", studentList);
        model.addAttribute("aptechClass", aptechClass);
        return "aptechClass/detail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addStudent(int[] studentIds, int id) {
        List<Integer> intList = new ArrayList<Integer>();
        for (int i : studentIds)
        {
            intList.add(i);
        }
        List<Student> studentList = studentRepository.findAllById(intList);
        AptechClass aptechClass = aptechClassService.findById(id);
        for (Student student: studentList
        ) {
            aptechClass.add(student);
        }
        aptechClassRepository.save(aptechClass);
        return "redirect:/aptechClass/" + id;
    }
}
