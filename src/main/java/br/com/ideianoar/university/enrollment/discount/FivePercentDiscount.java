package br.com.ideianoar.university.enrollment.discount;

import br.com.ideianoar.university.enrollment.Enrollment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FivePercentDiscount implements Discount {

    @Override
    public Double percentage() {
        return Double.valueOf("5");
    }

    @Override
    public Double apply(Enrollment enrollment) {
        if (enrollment.getEnrolledCourses().size() == 1
                && enrollment.getLastEnrolledCourseDate().isPresent()
                && LocalDate
                .now()
                .minus(90, ChronoUnit.DAYS)
                .isBefore(enrollment.getLastEnrolledCourseDate().get())) {
            return percentage();
        }

        return Double.valueOf("0");
    }
}
