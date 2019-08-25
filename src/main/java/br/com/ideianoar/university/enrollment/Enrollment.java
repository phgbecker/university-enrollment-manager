package br.com.ideianoar.university.enrollment;

import br.com.ideianoar.university.enrollment.discount.Discount;
import br.com.ideianoar.university.enrollment.rule.EnrollmentRule;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Getter
public class Enrollment {
    private final Student student;
    private final List<Course> enrolledCourses;
    private final List<Course> coursesToEnroll;
    private Double discount;

    public Enrollment(Student student) {
        this.student = student;
        this.enrolledCourses = new ArrayList<>();
        this.coursesToEnroll = new ArrayList<>();
    }

    public Enrollment(Student student, List<Course> enrolledCourses, Double discount) {
        this.student = student;
        this.enrolledCourses = enrolledCourses;
        this.coursesToEnroll = new ArrayList<>();
        this.discount = discount;
    }

    public Enrollment addCourse(Course course) {
        this.coursesToEnroll.add(course);
        return this;
    }

    public Optional<LocalDate> getLastEnrolledCourseDate() {
        return Optional.of(
                enrolledCourses.stream()
                        .max(Comparator.comparing(Course::getEnrollDate))
                        .map(Course::getEnrollDate)
        ).orElse(
                Optional.empty()
        );
    }

    public Enrollment enroll(List<EnrollmentRule> enrollmentRules, List<Discount> discounts) {
        applyRules(enrollmentRules);
        applyDiscounts(discounts);
        addNewCourses();

        return new Enrollment(student, enrolledCourses, discount);
    }

    private void applyRules(List<EnrollmentRule> enrollmentRules) {
        enrollmentRules.forEach(rule -> rule.apply(this));
    }

    private void applyDiscounts(List<Discount> discounts) {
        discount = discounts.stream()
                .map(discount -> discount.apply(this))
                .reduce(Double.valueOf("0"), (subtotal, discount) -> subtotal + discount);
    }

    private void addNewCourses() {
        enrolledCourses.addAll(coursesToEnroll);
    }
}
