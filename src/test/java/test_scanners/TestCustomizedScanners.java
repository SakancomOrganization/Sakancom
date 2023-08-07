package test_scanners;

import enums.HouseClassificationByGender;
import enums.UserType;
import exceptions.UnacceptableValueException;
import models.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scanners.CustomizedScanners;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestCustomizedScanners {
    private Scanner scanner;

    @BeforeEach
    public void setup() {
        scanner = mock(Scanner.class);
    }

    @Test
    void testScanInt() {
        when(scanner.nextInt()).thenReturn(1); // Stubbing the scanner's nextInt() method
        int result = CustomizedScanners.scanInt("test int", scanner); // Calling the method under test
        assertEquals(1, result); // Verifying the result
    }

    @Test
    void testScanNonEmptyString() {
        when(scanner.nextLine()).thenReturn("Complete line"); // Stubbing the scanner's nextInt() method
        String result = CustomizedScanners.scanNonEmptyString("test non empty string", scanner); // Calling the method under test
        assertEquals("Complete line", result); // Verifying the result
    }

    @Test
    void testScanString() {
        when(scanner.nextLine()).thenReturn("Complete line"); // Stubbing the scanner's nextInt() method
        String result = CustomizedScanners.scanString("test non empty string", scanner); // Calling the method under test
        assertEquals("Complete line", result); // Verifying the result
    }

    @Test
    void testScanBoolean() {
        when(scanner.nextBoolean()).thenReturn(true); // Stubbing the scanner's nextInt() method
        boolean result = CustomizedScanners.scanBoolean("test boolean", scanner); // Calling the method under test
        assertTrue(result); // Verifying the result
    }

    @Test
    void testScanBirthdate() throws ParseException {
        when(scanner.nextLine()).thenReturn("12/06/2002"); // Stubbing the scanner's nextInt() method
        Date result = CustomizedScanners.scanBirthdate(scanner); // Calling the method under test
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002"), result); // Verifying the result
    }

    @Test
    void testScanUserType() {
        when(scanner.nextLine()).thenReturn("admin"); // Stubbing the scanner's nextInt() method
        UserType result = CustomizedScanners.scanUserType(scanner); // Calling the method under test
        assertEquals(UserType.ADMIN, result); // Verifying the result
    }

    @Test
    void testScanHouseClassificationByGender() {
        when(scanner.next()).thenReturn("family"); // Stubbing the scanner's nextInt() method
        HouseClassificationByGender result = CustomizedScanners.scanHouseClassificationByGender(scanner); // Calling the method under test
        assertEquals(HouseClassificationByGender.FAMILY, result); // Verifying the result
    }

    @Test
    void testScanServices() throws UnacceptableValueException {
        Services expectedServices = new Services(true, true, true, true, true, 3, 3);
        when(scanner.nextBoolean()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(2);
        Services result = CustomizedScanners.scanServices(scanner); // Calling the method under test
        assertEquals(expectedServices, result); // Verifying the result
    }
}
