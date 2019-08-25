package br.com.ideianoar.university.enrollment;

import lombok.Getter;

@Getter
public class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }
}
