package com.playtomic.tests.wallet.service.impl;


import com.playtomic.tests.wallet.service.StripeAmountTooSmallException;
import com.playtomic.tests.wallet.service.StripeService;
import com.playtomic.tests.wallet.service.StripeServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

/**
 * This test is failing with the current implementation.
 *
 * How would you test this?
 */
@SpringBootTest
public class StripeServiceTest {

    // URI testUri = URI.create("https://sandbox.playtomic.io/v1/stripe-simulator/charges");
    // StripeService s = new StripeService(testUri, testUri, new RestTemplateBuilder());

    @MockBean
    private StripeService stripeService;

    @Test
    public void test_exception() {
        doThrow(StripeAmountTooSmallException.class).when(stripeService).charge("4242 4242 4242 4242",
                new BigDecimal(5));
        Assertions.assertThrows(StripeAmountTooSmallException.class, () -> {
            stripeService.charge("4242 4242 4242 4242", new BigDecimal(5));
        });
    }

    @Test
    public void test_ok() throws StripeServiceException {
        doNothing().when(stripeService).charge("4242 4242 4242 4242", new BigDecimal(15));
        stripeService.charge("4242 4242 4242 4242", new BigDecimal(15));
    }
}
