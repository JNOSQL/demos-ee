package br.com.thedevconf.communitylounge;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.List;
import java.util.UUID;

@Entity
public class Developer {

    @Id
    private String id;
    @Column
    private String name;
    @Column
    private List<String> technologies;

    public static Developer newDeveloper(String name, List<String> technologies) {
        return new Developer(UUID.randomUUID().toString(),
                name,
                technologies);
    }

    public Developer() {

    }

    public Developer(String id, String name, List<String> technologies) {
        this.id = id;
        this.name = name;
        this.technologies = technologies;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }
}
