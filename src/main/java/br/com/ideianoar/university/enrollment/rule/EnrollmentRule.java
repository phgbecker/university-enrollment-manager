package br.com.ideianoar.university.enrollment.rule;

import br.com.ideianoar.university.enrollment.Enrollment;

public interface EnrollmentRule {

    void apply(Enrollment enrollment);
}
