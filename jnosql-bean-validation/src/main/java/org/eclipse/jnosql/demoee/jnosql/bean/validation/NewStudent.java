package org.eclipse.jnosql.demoee.jnosql.bean.validation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;
@Schema(name = "NewStudent")
public class NewStudent {

    @NotBlank
    @Schema(description = "Student name")
    private String name;

    @Positive
    @Min(18)
    @Schema(description = "Student age")
    private int age;

    public NewStudent() {
    }

    Student toModel() {
        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setName(this.getName());
        student.setAge(this.getAge());
        return student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
