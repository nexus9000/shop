package edu.nbu.projectshop.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PriceGoodsCalculationsTest {
private final String productionDate = "2023-02-02";
private final BigDecimal deliveryPrice = BigDecimal.valueOf(10.00);//real price
private final BigDecimal percentage = BigDecimal.valueOf(0.2);//markup 20%
private Date prDate;//date of production
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
    @Test
    @DisplayName("Test price item with expired period")
    void testPriceItem()throws RuntimeException{
      RuntimeException thrown = Assertions.assertThrows(RuntimeException.class,()->
                pgc.calculateRealPrice(currentDate, prDate,30)  );
        assertEquals("Expired product",thrown.getMessage());
    }
    @Test
    @DisplayName("Test price item with discount")
    void testDiscountPrice()throws RuntimeException{
        int period = 60;//product  in days
        BigDecimal priceDiscount = pgc.calculateRealPrice(currentDate, prDate, period);
        //initial price is 10 markup percentage is 20 % so expected price is 12.00
        assertEquals(12.00, priceDiscount);
    }
}