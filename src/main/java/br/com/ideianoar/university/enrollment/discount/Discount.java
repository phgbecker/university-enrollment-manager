package br.com.ideianoar.university.enrollment.discount;

import br.com.ideianoar.university.enrollment.Enrollment;

import java.util.function.Function;

public interface Discount extends Function<Enrollment, Double> {

    Double percentage();
}
