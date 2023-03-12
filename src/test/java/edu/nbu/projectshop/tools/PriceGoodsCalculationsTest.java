package edu.nbu.projectshop.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PriceGoodsCalculationsTest {
private final String productionDate = "2023-02-02";
private final double deliveryPrice = 10.01;
private final double percentage = 20.05;
private Date prDate;
private Date currentDate;
private PriceGoodsCalculations pgc;
    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        prDate = sdf.parse(productionDate);
        currentDate = (new Date());
        pgc = new PriceGoodsCalculations(deliveryPrice, percentage, prDate);
    }

    @Test
    void priceCalculation() {
        assertEquals(200.7005, pgc.priceCalculation(deliveryPrice, percentage));
    }

    @Test
    @DisplayName("check expiration date")
    void checkExpirationDate() {
        boolean markExpired = pgc.checkExpirationDate(currentDate, prDate, 3);
        boolean markNotExpired = pgc.checkExpirationDate(currentDate, prDate,100);
        assertFalse(markExpired);
        assertTrue(markNotExpired);
    }
}