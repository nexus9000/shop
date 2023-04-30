package edu.nbu.projectshop.tools;

import edu.nbu.projectshop.exceptions.TooBigMarkupException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PriceGoodsCalculationsTest {
    private final String productionDate = "2023-02-02";
    private final BigDecimal deliveryPrice = BigDecimal.valueOf(10.00);//real price
    private  BigDecimal percentage;//markup 20%
    private final BigDecimal MAXIMUM_MARKUP_PERCENTAGE = BigDecimal.valueOf(0.5);//maximum markup percentage
    private Date prDate;//date of production
    private Date currentDate;
    private PriceGoodsCalculations pgc;
    private Logger logger = Logger.getLogger(PriceGoodsCalculationsTest.class);
    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        prDate = sdf.parse(productionDate);
        currentDate = (new Date());
        percentage = BigDecimal.valueOf(0.30).setScale(2);
        pgc = new PriceGoodsCalculations(deliveryPrice, percentage, prDate);
    }

    @Test
    void priceCalculation() {
        assertEquals(BigDecimal.valueOf(200.7005), pgc.priceCalculation(deliveryPrice, percentage));
    }

    @Test
    @DisplayName("check expiration date")
    void checkExpirationDate() {
        boolean markExpired = pgc.checkExpirationDate(currentDate, prDate, 3);
        boolean markNotExpired = pgc.checkExpirationDate(currentDate, prDate, 100);
        assertFalse(markExpired);
        assertTrue(markNotExpired);
    }

    @Test
    @DisplayName("test maximum markup percentage")
    void testMarkupTooBigException() {
        percentage = BigDecimal.valueOf(0.55);//55% markup percentage should be thrown exception
        TooBigMarkupException exception = Assertions.assertThrows(TooBigMarkupException.class, () -> {
            if (percentage.compareTo(MAXIMUM_MARKUP_PERCENTAGE) > 0)
                throw new TooBigMarkupException("Too big markup. " + "Should less than 50 percent");
        });
        assertEquals("Too big markup. Should less than 50 percent", exception.getMessage());
    }

    @Test
    @DisplayName("Test price item with expired period")
    void testPriceItem() throws RuntimeException {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> pgc.calculateRealPrice(currentDate, prDate, 30));
        assertEquals("Expired product", thrown.getMessage());
    }

    @Test
    @DisplayName("Test price item with discount")
    void testDiscountPrice() throws RuntimeException {
        int period = 120;//product  in days
        BigDecimal priceDiscount = pgc.calculateRealPrice(currentDate, prDate, period);
        //initial price is 10 units markup percentage is 30 % so expected price is 13.00
        assertEquals(BigDecimal.valueOf(13.0040).setScale(2, RoundingMode.HALF_UP), priceDiscount.setScale(2,RoundingMode.HALF_UP));
        logger.info(priceDiscount.setScale(2));
    }
}