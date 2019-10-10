package t1708e.springasm2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import t1708e.springasm2.repository.StudentRepository;
import t1708e.springasm2.entity.Student;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email);
        if (student == null) {
            throw new UsernameNotFoundException("Email not found");
        }
        UserDetails user =
                User.builder()
                        .username(student.getEmail())
                        .password(student.getPassword())
                        .roles("")
                        .build();
        return user;
    }
}
