package org.hibernate.bugs;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    public Event() {
    }

    public Long getId() {
        return id;
    }
}
