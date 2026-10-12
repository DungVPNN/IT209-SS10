
package javaweb.btap0909.service;

import com.example.studentapi.entity.Student;
import com.example.studentapi.repository.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(Long id, Student student) {
        Student old = getById(id);
        old.setName(student.getName());
        old.setDob(student.getDob());
        old.setGender(student.getGender());
        old.setPhone(student.getPhone());
        old.setGpa(student.getGpa());
        return repository.save(old);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }

    public Page<Student> getPage(
            int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(
                page, size, Sort.by(sortBy).ascending());
        return repository.findAll(pageable);
    }
}
