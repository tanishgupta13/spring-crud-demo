package in.strikes.crudDemo.service;

import in.strikes.crudDemo.dto.CreateStudentRequestDto;
import in.strikes.crudDemo.dto.CreateStudentResponseDto;
import in.strikes.crudDemo.dto.UpdateStudentRequestDto;
import in.strikes.crudDemo.dto.UpdateStudentResponseDto;
import in.strikes.crudDemo.entity.CRUDStudent;
import in.strikes.crudDemo.exceptionHandler.ResourceNotFoundException;
import in.strikes.crudDemo.repository.CRUDRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CRUDService {

    private CRUDRepository crudRepository;

    public CRUDService(CRUDRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentReq){
//        studentReq.setDeleted(false);
        CRUDStudent student = mapToEntity(studentReq);
        CRUDStudent savedStudent = crudRepository.save(student);

        return mapToDto(savedStudent);
    }



    public CreateStudentResponseDto getStudent(Long Id){
        CRUDStudent studentReq = crudRepository.findByIdAndDeletedIsFalse(Id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student with id " +Id + "is not available in the db"
                ));

//        if(studentReq.isPresent()){
//            return mapToDto(studentReq.get());
//        }
//        return null;
        return mapToDto(studentReq);
    }

    public List<CreateStudentResponseDto> getAllStudent(){
       List<CRUDStudent> studentList = crudRepository.findByDeletedIsFalse();
       return studentList.stream().map(this::mapToDto).toList();
    }

    public UpdateStudentResponseDto updateStudent(Long Id, UpdateStudentRequestDto student){
        CRUDStudent studentReq = crudRepository.findByIdAndDeletedIsFalse(Id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Student with id " + Id + " not found"
        ));
//        if(studentReq.isEmpty()){
//            return null;
//        }

//        CRUDStudent s1 = studentReq.get();
        studentReq.setAge(student.getAge());
        studentReq.setName(student.getName());
        studentReq.setSubject(student.getSubject());
        studentReq.setRollNo(student.getRollNo());
        studentReq.setUpdatedAt(LocalDateTime.now());

        CRUDStudent savedStudent = crudRepository.save(studentReq);
        return mapToUpdatedDto(savedStudent);
    }

    public void deleteStudent(Long id){
//        if(!crudRepository.existsById(id)) return false;
        CRUDStudent student = crudRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Student with id " + id + " not found"
        ));;

        crudRepository.delete(student);
//        return true;
    }

    public void deleteSoftly(Long id){
        CRUDStudent student = crudRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student with id " + id + " not found"
                ));;

//        if(student.isEmpty()) return false;

//        CRUDStudent s1 = student.get();
        student.setDeleted(true);
        crudRepository.save(student);
//        return true;
    }

    private CRUDStudent mapToEntity(CreateStudentRequestDto studentReq){
        CRUDStudent student = new CRUDStudent();

        student.setName(studentReq.getName());
        student.setRollNo(studentReq.getRollNo());
        student.setSubject(studentReq.getSubject());
        student.setAge(studentReq.getAge());
        student.setEmail(studentReq.getEmail());
        student.setDeleted(false);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        return student;
    }

    private CreateStudentResponseDto mapToDto(CRUDStudent student){
        CreateStudentResponseDto responseDto = new CreateStudentResponseDto();

        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setEmail(student.getEmail());
        responseDto.setId(student.getId());
        responseDto.setSubject(student.getSubject());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setUpdatedAt(student.getUpdatedAt());

        return responseDto;
    }

    private UpdateStudentResponseDto mapToUpdatedDto(CRUDStudent student){
        UpdateStudentResponseDto responseDto = new UpdateStudentResponseDto();

        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setEmail(student.getEmail());
        responseDto.setId(student.getId());
        responseDto.setSubject(student.getSubject());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setUpdatedAt(student.getUpdatedAt());

        return responseDto;
    }
}