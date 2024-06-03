package com.iStudent.service;

import com.iStudent.model.DTOs.MarkDTO;
import com.iStudent.model.DTOs.StudentDTO;
import com.iStudent.model.entity.Mark;
import com.iStudent.model.entity.Student;
import com.iStudent.model.entity.base.BasePersonEntity;
import com.iStudent.repository.MarkRepository;
import com.iStudent.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final MarkRepository markRepository;

    private final ModelMapper mapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public StudentService(StudentRepository studentRepository, MarkRepository markRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.markRepository = markRepository;
        this.mapper = mapper;
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(this::mapToStudentDTO)
                .toList();
    }

    public StudentDTO[] getListByTown(Long idTown) {
        List<Student> students = studentRepository.findByTownId(idTown);

        return students.stream()
                .map(this::mapToStudentDTO)
                .toArray(StudentDTO[]::new);
    }


    public Optional<StudentDTO> getStudentById(Long studentId) {
        return studentRepository
                .findById(studentId)
                .map(this::mapToStudentDTO);
    }

    public Optional<Student> getStudentRealById(Long studentId) {
        return studentRepository
                .findById(studentId);
    }

    public Optional<Long> getParentId(Long studentId) {
        return studentRepository
                .findById(studentId)
                .map(Student::getParentId);
    }

    public long addStudent(StudentDTO studentDTO) {
        Student student = mapper.map(studentDTO, Student.class);

        student.setParent(studentDTO.getParent());
        studentRepository.save(student);

        // Send confirmation message
        String confirmationMessage = "OK: " + student.getId();
        System.out.println("Sending confirmation message: " + confirmationMessage);
        kafkaTemplate.send("studentOkTopic", confirmationMessage);


        return student.getId();
    }

    public boolean addMarkToStudent(Long studentId, MarkDTO markDTO) {
        Mark markToAdd = markRepository.findByMark(markDTO.getMark());
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.addMark(markToAdd);
            studentRepository.save(student);
            return true;
        }

        return false;
    }

    public void deleteStudentById(Long studentId) {
        System.out.println("rip student" + studentId);
        studentRepository.deleteById(studentId);
    }

    private StudentDTO mapToStudentDTO(Student student) {
        return mapper.map(student, StudentDTO.class);
    }
}
