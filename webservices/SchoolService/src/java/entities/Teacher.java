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
@XmlRootElement(name="Teacher")
public class Teacher {
    
    // Fields
    private int id;
    private String firstname;
    private String lastname;
    
    // Constructor
    protected Teacher() {}
    public Teacher(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
        
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
