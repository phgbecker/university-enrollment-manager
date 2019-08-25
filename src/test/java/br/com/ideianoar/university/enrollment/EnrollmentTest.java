package br.com.ideianoar.university.enrollment;

import br.com.ideianoar.university.enrollment.discount.Discount;
import br.com.ideianoar.university.enrollment.discount.FivePercentDiscount;
import br.com.ideianoar.university.enrollment.discount.TenPercentDiscount;
import br.com.ideianoar.university.enrollment.rule.EnrollmentRule;
import br.com.ideianoar.university.enrollment.rule.MaxCourseEnrollmentRule;
import br.com.ideianoar.university.enrollment.rule.exception.EnrollmentRuleException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class EnrollmentTest {
    private List<EnrollmentRule> enrollmentRules;
    private List<Discount> discounts;

    @Before
    public void setUp() {
        enrollmentRules = Collections.singletonList(
                new MaxCourseEnrollmentRule()
        );

        discounts = Arrays.asList(
                new FivePercentDiscount(),
                new TenPercentDiscount()
        );
    }

    @Test
    public void givenStudent_whenEnroll_thenDiscountIsZero() {
        Enrollment enrollment = new Enrollment(mock(Student.class))
                .addCourse(mock(Course.class))
                .enroll(enrollmentRules, discounts);

        assertThat(enrollment.getDiscount()).isEqualTo(Double.valueOf("0"));
    }

    @Test
    public void givenStudent_whenEnrollMoreThanTwoCourses_thenExceptionsIsThrown() {
        Enrollment enrollment = new Enrollment(mock(Student.class))
                .addCourse(mock(Course.class))
                .addCourse(mock(Course.class))
                .addCourse(mock(Course.class));

        assertThatThrownBy(
                () -> enrollment.enroll(
                        Collections.singletonList(new MaxCourseEnrollmentRule()),
                        Collections.singletonList(mock(Discount.class))
                )
        ).isInstanceOf(EnrollmentRuleException.class).hasMessage("Oops, you can enroll up to two courses at a time");
    }

    @Test
    public void givenStudentWithOneCurse_whenEnroll_thenDiscountIsFive() {
        Enrollment enrollment = new Enrollment(mock(Student.class))
                .addCourse(
                        new Course.Builder("Logistics")
                                .withEnrollDate(LocalDate.now())
                                .build()
                )
                .enroll(enrollmentRules, discounts);

        enrollment.addCourse(
                new Course.Builder("Mathematics")
                        .withEnrollDate(LocalDate.now())
                        .build()
        ).enroll(enrollmentRules, discounts);

        assertThat(enrollment.getDiscount()).isEqualTo(Double.valueOf("5"));
    }

    @Test
    public void givenStudentWithTwoCurses_whenEnroll_thenDiscountIsTen() {
        Enrollment enrollment = new Enrollment(mock(Student.class))
                .addCourse(
                        new Course.Builder("Logistics")
                                .withEnrollDate(LocalDate.now())
                                .build()
                )
                .addCourse(
                        new Course.Builder("Mathematics")
                                .withEnrollDate(LocalDate.now())
                                .build()
                )
                .enroll(enrollmentRules, discounts);

        enrollment.addCourse(
                new Course.Builder("Geometry")
                        .withEnrollDate(LocalDate.now())
                        .build()
        ).enroll(enrollmentRules, discounts);

        assertThat(enrollment.getDiscount()).isEqualTo(Double.valueOf("10"));
    }

    @Test
    public void givenLastCourseHasMoreThan90Days_whenEnroll_thenDiscountIsZero() {
        Enrollment enrollment = new Enrollment(mock(Student.class))
                .addCourse(
                        new Course.Builder("Algebra")
                                .withEnrollDate(LocalDate.now().minusDays(90))
                                .build()
                )
                .enroll(enrollmentRules, discounts)
                .addCourse(
                        new Course.Builder("Mathematics")
                                .withEnrollDate(LocalDate.now())
                                .build()
                ).enroll(enrollmentRules, discounts);

        assertThat(enrollment.getDiscount()).isEqualTo(Double.valueOf("0"));
    }
}