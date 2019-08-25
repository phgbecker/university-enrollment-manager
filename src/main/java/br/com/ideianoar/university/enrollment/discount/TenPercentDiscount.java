package br.com.ideianoar.university.enrollment.discount;

import br.com.ideianoar.university.enrollment.Enrollment;

import java.time.LocalDate;

public class TenPercentDiscount implements Discount {

    @Override
    public Double percentage() {
        return Double.valueOf("10");
    }

    @Override
    public Double apply(Enrollment enrollment) {
        if (enrollment.getEnrolledCourses().size() > 1
                && enrollment.getLastEnrolledCourseDate().isPresent()
                && LocalDate
                .now()
                .equals(enrollment.getLastEnrolledCourseDate().get())) {
            return percentage();
        }

        return Double.valueOf("0");
    }
}
