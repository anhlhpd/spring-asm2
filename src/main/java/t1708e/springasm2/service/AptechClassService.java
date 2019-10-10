package t1708e.springasm2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t1708e.springasm2.entity.AptechClass;
import t1708e.springasm2.entity.Student;
import t1708e.springasm2.repository.AptechClassRepository;

import java.util.List;

@Service
public class AptechClassService {
    @Autowired
    AptechClassRepository aptechClassRepository;

    public List<AptechClass> list(){
        return aptechClassRepository.findAll();
    }

    public AptechClass findById(int id){
        return aptechClassRepository.findById(id).orElse(null);
    }
}
