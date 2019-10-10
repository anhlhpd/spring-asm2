package t1708e.springasm2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import t1708e.springasm2.entity.Student;
import t1708e.springasm2.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Student create(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public Student getById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getList(){
        return studentRepository.findAll();
    }

    public Student getByEmail(String email) {
        return studentRepository.findByEmail(email);
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
