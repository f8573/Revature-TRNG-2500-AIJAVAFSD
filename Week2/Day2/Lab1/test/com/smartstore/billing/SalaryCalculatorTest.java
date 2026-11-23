package com.smartstore.billing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SalaryCalculatorTest {
    private static final double DELTA = 0.001;

    @Test
    void grossIncludesTwentyPercentHraAndTenPercentDa() {
        assertEquals(20800.00, SalaryCalculator.calculateGross(16000.00), DELTA);
    }

    @Test
    void taxIsZeroAtExactlyTwentyThousand() {
        double basic = 20000.00 / 1.30;
        assertEquals(20000.00, SalaryCalculator.calculateGross(basic), DELTA);
        assertEquals(0.00, SalaryCalculator.calculateProfessionalTax(
                SalaryCalculator.calculateGross(basic)), DELTA);
    }

    @Test
    void taxIsTwoHundredAboveTwentyThousand() {
        assertEquals(200.00, SalaryCalculator.calculateProfessionalTax(20000.01), DELTA);
    }

    @Test
    void netSalarySubtractsProfessionalTax() {
        double gross = SalaryCalculator.calculateGross(16000.00);
        assertEquals(20600.00,
                gross - SalaryCalculator.calculateProfessionalTax(gross), DELTA);
    }

    @Test
    void zeroBasicSalaryProducesNoAllowancesTaxOrNetPay() {
        assertEquals(0.00, SalaryCalculator.calculateHra(0.00), DELTA);
        assertEquals(0.00, SalaryCalculator.calculateDa(0.00), DELTA);
        assertEquals(0.00, SalaryCalculator.calculateGross(0.00), DELTA);
        assertEquals(0.00, SalaryCalculator.calculateProfessionalTax(0.00), DELTA);
    }

    @Test
    void calculatesHraForTenThousand() {
        assertEquals(2000.00, SalaryCalculator.calculateHra(10000.00), DELTA);
    }

    @Test
    void calculatesDaForTenThousand() {
        assertEquals(1000.00, SalaryCalculator.calculateDa(10000.00), DELTA);
    }

    @Test
    void fifteenThousandSalaryHasCorrectHraAndDa() {
        assertEquals(3000.00, SalaryCalculator.calculateHra(15000.00), DELTA);
        assertEquals(1500.00, SalaryCalculator.calculateDa(15000.00), DELTA);
    }

    @Test
    void grossJustAboveTwentyThousandHasTax() {
        double gross = SalaryCalculator.calculateGross(15384.63);
        assertEquals(20000.02, gross, DELTA);
        assertEquals(200.00, SalaryCalculator.calculateProfessionalTax(gross), DELTA);
    }

    @Test
    void grossJustAboveThresholdProducesCorrectNetPay() {
        double gross = SalaryCalculator.calculateGross(15384.63);
        double tax = SalaryCalculator.calculateProfessionalTax(gross);
        assertEquals(19800.02, gross - tax, DELTA);
    }
}
