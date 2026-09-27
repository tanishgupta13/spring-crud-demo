package in.strikes.crudDemo.dto;


import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 to 50")
    private String name;

    @NotNull
    @Min(value = 18)
    private Integer age;

    @Email(message = "Please enter a valid email")
    private String email;

    @NotNull(message = "Roll No cannot be null")
    private Integer rollNo;

    @NotBlank(message = "Subject is required")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
