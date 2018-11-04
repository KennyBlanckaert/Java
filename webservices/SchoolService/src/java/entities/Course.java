/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kenny Blanckaert
 */
@XmlRootElement(name="Course")
public class Course {
    
    // Fields
    private int id;
    private String name;
    
    // Constructors
    protected Course() {}
    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }  
}
