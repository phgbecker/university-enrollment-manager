package br.com.ideianoar.university.enrollment;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Course {
    private final String name;
    private final LocalDate enrollDate;

    public Course(String name, LocalDate enrollDate) {
        this.name = name;
        this.enrollDate = enrollDate;
    }

    public static class Builder {
        private final String name;
        private LocalDate enrollDate;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withEnrollDate(LocalDate enrollDate) {
            this.enrollDate = enrollDate;
            return this;
        }

        public Course build() {
            return new Course(name, enrollDate);
        }
    }
}
