package in.strikes.crudDemo.controller;

import in.strikes.crudDemo.dto.CreateStudentRequestDto;
import in.strikes.crudDemo.dto.CreateStudentResponseDto;
import in.strikes.crudDemo.dto.UpdateStudentRequestDto;
import in.strikes.crudDemo.dto.UpdateStudentResponseDto;
import in.strikes.crudDemo.entity.CRUDStudent;
import in.strikes.crudDemo.service.CRUDService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class CRUDController {

    private CRUDService crudService;

    public CRUDController(CRUDService crudService) {
        this.crudService = crudService;
    }

    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent (
            @Valid
            @RequestBody CreateStudentRequestDto studentreq){

        CreateStudentResponseDto Response = crudService.createStudent(studentreq);
        return ResponseEntity.status(HttpStatus.CREATED).body(Response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id){
        CreateStudentResponseDto studentReq = crudService.getStudent(id);
//
//        if(studentReq == null){
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.status(HttpStatus.OK).body(studentReq);
    }

    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudent(){
        List<CreateStudentResponseDto> studentReq = crudService.getAllStudent();

        if(studentReq.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentReq);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id,
                                                                  @RequestBody UpdateStudentRequestDto student){
        UpdateStudentResponseDto studentReq = crudService.updateStudent(id, student);

        if(studentReq==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentReq);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
//        boolean isDeleted =
                crudService.deleteStudent(id);

//        if (!isDeleted) return ResponseEntity.notFound().build();

        return ResponseEntity.ok("DELETED");
    }

    @PatchMapping("soft-delete/{id}")
    public ResponseEntity<String> deleteSoftly(@PathVariable Long id){
//        boolean isSoftDeleted =
                crudService.deleteSoftly(id);

//        if(!isSoftDeleted) return ResponseEntity.notFound().build();

        return ResponseEntity.ok("SOFT DELETE DONE");
    }
}