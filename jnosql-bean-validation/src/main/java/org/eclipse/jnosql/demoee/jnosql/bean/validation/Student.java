package org.eclipse.jnosql.demoee.jnosql.bean.validation;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Student")
@Entity
public class Student {

    @Id
    @Schema(description = "Student id")
    private String id;

    @Column
    @Schema(description = "Student name")
    private String name;

    @Column
    @Schema(description = "Student age")
    private int age;

    public Student() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
