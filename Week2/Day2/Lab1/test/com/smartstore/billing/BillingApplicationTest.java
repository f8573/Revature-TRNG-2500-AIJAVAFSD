package com.smartstore.billing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BillingApplicationTest {
    private static final double DELTA = 0.001;

    @Test
    void noDiscountBelowOneThousand() {
        assertEquals(0.0, BillingApplication.calculateDiscount(999.99), DELTA);
    }

    @Test
    void fivePercentAtOneThousand() {
        assertEquals(50.0, BillingApplication.calculateDiscount(1000.0), DELTA);
    }

    @Test
    void fivePercentAtFourThousandNineHundredNinetyNine() {
        assertEquals(249.95, BillingApplication.calculateDiscount(4999.0), DELTA);
    }

    @Test
    void fivePercentBelowFiveThousandForPaiseBoundary() {
        assertEquals(249.98, BillingApplication.calculateDiscount(4999.50), DELTA);
        assertEquals(4749.52, BillingApplication.calculateFinalAmount(4999.50), DELTA);
    }

    @Test
    void tenPercentAtFiveThousand() {
        assertEquals(500.0, BillingApplication.calculateDiscount(5000.0), DELTA);
    }

    @Test
    void tenPercentAboveFiveThousand() {
        assertEquals(600.0, BillingApplication.calculateDiscount(6000.0), DELTA);
    }

    @Test
    void finalAmountSubtractsDiscount() {
        assertEquals(5400.0, BillingApplication.calculateFinalAmount(6000.0), DELTA);
    }

    @Test
    void rejectsNegativeTotalAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> BillingApplication.calculateDiscount(-1.0));
    }

    @Test
    void rejectsNaNTotalAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> BillingApplication.calculateDiscount(Double.NaN));
    }

    @Test
    void rejectsInfiniteTotalAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> BillingApplication.calculateDiscount(Double.POSITIVE_INFINITY));
    }
}
