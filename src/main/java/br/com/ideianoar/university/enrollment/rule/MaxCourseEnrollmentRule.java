package br.com.ideianoar.university.enrollment.rule;

import br.com.ideianoar.university.enrollment.Enrollment;
import br.com.ideianoar.university.enrollment.rule.exception.EnrollmentRuleException;

public class MaxCourseEnrollmentRule implements EnrollmentRule {

    @Override
    public void apply(Enrollment enrollment) {
        if (enrollment.getCoursesToEnroll().size() > 2) {
            throw new EnrollmentRuleException("Oops, you can enroll up to two courses at a time");
        }
    }
}
