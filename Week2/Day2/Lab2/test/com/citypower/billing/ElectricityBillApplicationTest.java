
package com.citypower.billing;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ElectricityBillApplicationTest {
    private static final double DELTA = 0.001;

    @Test
    void zeroUnitsHaveNoEnergyCharge() {
        assertEquals(0.00, ElectricityBillApplication.calculateEnergyCharge(0), DELTA);
    }

    @Test
    void eightyUnitsUseFirstSlab() {
        assertEquals(240.00, ElectricityBillApplication.calculateEnergyCharge(80), DELTA);
    }

    @Test
    void exactlyOneHundredUnitsUseFirstSlab() {
        assertEquals(300.00, ElectricityBillApplication.calculateEnergyCharge(100), DELTA);
    }

    @Test
    void oneHundredOneUnitsUseSecondSlabForTheExtraUnit() {
        assertEquals(305.00, ElectricityBillApplication.calculateEnergyCharge(101), DELTA);
    }

    @Test
    void exactlyThreeHundredUnitsUseFirstTwoSlabs() {
        assertEquals(1300.00, ElectricityBillApplication.calculateEnergyCharge(300), DELTA);
    }

    @Test
    void threeHundredOneUnitsUseThirdSlabForTheExtraUnit() {
        assertEquals(1307.50, ElectricityBillApplication.calculateEnergyCharge(301), DELTA);
    }

    @Test
    void threeHundredNinetyThreeUnitsRemainBelowSurchargeBoundary() {
        double energy = ElectricityBillApplication.calculateEnergyCharge(393);
        assertEquals(1997.50, energy, DELTA);
        assertEquals(0.00, ElectricityBillApplication.calculateSurcharge(energy), DELTA);
    }

    @Test
    void threeHundredNinetyFourUnitsCrossSurchargeBoundary() {
        double energy = ElectricityBillApplication.calculateEnergyCharge(394);
        assertEquals(2005.00, energy, DELTA);
        assertEquals(100.25, ElectricityBillApplication.calculateSurcharge(energy), DELTA);
    }

    @Test
    void exactlyTwoThousandEnergyChargeHasNoSurcharge() {
        assertEquals(0.00, ElectricityBillApplication.calculateSurcharge(2000.00), DELTA);
    }

    @Test
    void fourHundredUnitsProduceExpectedTotal() {
        double energy = ElectricityBillApplication.calculateEnergyCharge(400);
        assertEquals(2050.00, energy, DELTA);
        assertEquals(2202.50, ElectricityBillApplication.calculateTotal(energy), DELTA);
    }

    @Test
    void currentReadingBelowPreviousIsRejected() {
        assertFalse(ElectricityBillApplication.isValidReadingPair(1600, 1200));
        assertThrows(IllegalArgumentException.class,
                () -> ElectricityBillApplication.calculateUnits(1600, 1200));
    }

    @Test
    void validReadingsCalculateUnits() {
        assertTrue(ElectricityBillApplication.isValidReadingPair(1000, 1400));
        assertEquals(400, ElectricityBillApplication.calculateUnits(1000, 1400));
    }

    @Test
    void negativeUnitsAreRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> ElectricityBillApplication.calculateEnergyCharge(-1));
    }

    @Test
    void negativeSurchargeInputIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> ElectricityBillApplication.calculateSurcharge(-1.0));
    }

    @Test
    void nonFiniteSurchargeInputsAreRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> ElectricityBillApplication.calculateSurcharge(Double.NaN));
        assertThrows(IllegalArgumentException.class,
                () -> ElectricityBillApplication.calculateSurcharge(Double.POSITIVE_INFINITY));
    }

    @Test
    void equalMeterReadingsAreValid() {
        assertTrue(ElectricityBillApplication.isValidReadingPair(1200, 1200));
        assertEquals(0, ElectricityBillApplication.calculateUnits(1200, 1200));
    }

    @Test
    void negativeReadingsAreRejected() {
        assertFalse(ElectricityBillApplication.isValidReadingPair(-1, 100));
        assertThrows(IllegalArgumentException.class,
                () -> ElectricityBillApplication.calculateUnits(-1, 100));
        assertFalse(ElectricityBillApplication.isValidReadingPair(100, -1));
        assertThrows(IllegalArgumentException.class,
                () -> ElectricityBillApplication.calculateUnits(100, -1));
    }
}
