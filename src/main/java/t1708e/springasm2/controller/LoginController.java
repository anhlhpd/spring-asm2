package t1708e.springasm2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import t1708e.springasm2.entity.Student;
import t1708e.springasm2.repository.StudentRepository;
import t1708e.springasm2.service.StudentService;

import java.util.Optional;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(String email, String password) {
        Optional<Student> optionalStudent = studentRepository.findByEmailAndPassword(email, password);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentRepository.save(student);
            return "redirect:/students/" + student.getId();
        }
        return "login";
    }
}
