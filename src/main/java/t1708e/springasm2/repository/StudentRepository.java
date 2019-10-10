package t1708e.springasm2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1708e.springasm2.entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByEmailAndPassword(String email, String password);
    Student findByEmail (String email);
}
