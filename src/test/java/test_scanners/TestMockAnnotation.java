package test_scanners;

import enums.HouseClassificationByGender;
import enums.UserType;
import exceptions.UnacceptableValueException;
import models.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import scanners.CustomizedScanners;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class TestMockAnnotation {
        @Mock
        private Scanner scanner;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        void testScanInt() {
            // normal condition
            when(scanner.nextInt()).thenReturn(1);
            int result = CustomizedScanners.scanInt("test int", scanner);
            assertEquals(1, result);

            // exception
            when(scanner.nextInt()).thenThrow(InputMismatchException.class);
            assertDoesNotThrow( () -> CustomizedScanners.scanInt("test int",scanner));
        }

        @Test
        void testScanNonEmptyString() {
            String result;
            when(scanner.nextLine()).thenReturn("Complete line");
            result = CustomizedScanners.scanNonEmptyString("test non empty string", scanner);
            assertEquals("Complete line", result);

            // empty case
            when(scanner.nextLine()).thenReturn("");
            result = CustomizedScanners.scanNonEmptyString("test string",scanner);
            assertNull(result);
        }

        @Test
        void testScanString() {
            when(scanner.nextLine()).thenReturn("Complete line");
            String result = CustomizedScanners.scanString("test non empty string", scanner);
            assertEquals("Complete line", result);
        }

        @Test
        void testScanBoolean() {
            when(scanner.nextBoolean()).thenReturn(true);
            boolean result = CustomizedScanners.scanBoolean("test boolean", scanner);
            assertTrue(result);

            // exception
            when(scanner.nextBoolean()).thenThrow(InputMismatchException.class);
            assertDoesNotThrow(() -> CustomizedScanners.scanBoolean("test boolean", scanner));
        }

        @Test
        void testScanBirthdate() throws ParseException {
            when(scanner.nextLine()).thenReturn("12/06/2002");
            Date result = CustomizedScanners.scanBirthdate(scanner);
            assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002"), result);

            when(scanner.nextLine()).thenReturn("12.06.2002");
            assertDoesNotThrow(() -> CustomizedScanners.scanBirthdate(scanner));
        }

        @Test
        void testScanUserType() {
            UserType result;

            when(scanner.nextLine()).thenReturn("admin");
            result = CustomizedScanners.scanUserType(scanner);
            assertEquals(UserType.ADMIN, result);

            when(scanner.nextLine()).thenReturn("Owner");
            result = CustomizedScanners.scanUserType(scanner);
            assertEquals(UserType.OWNER, result);

            when(scanner.nextLine()).thenReturn("TEnAnT");
            result = CustomizedScanners.scanUserType(scanner);
            assertEquals(UserType.TENANT, result);

            when(scanner.nextLine()).thenReturn("role");
            assertNull(CustomizedScanners.scanUserType(scanner));
        }

        @Test
        void testScanHouseClassificationByGender() {
            HouseClassificationByGender result;

            when(scanner.next()).thenReturn("family");
            result = CustomizedScanners.scanHouseClassificationByGender(scanner);
            assertEquals(HouseClassificationByGender.FAMILY, result);

            when(scanner.next()).thenReturn("MaLe");
            result = CustomizedScanners.scanHouseClassificationByGender(scanner);
            assertEquals(HouseClassificationByGender.MALE, result);

            when(scanner.next()).thenReturn("FeMAle");
            result = CustomizedScanners.scanHouseClassificationByGender(scanner);
            assertEquals(HouseClassificationByGender.FEMALE, result);

            when(scanner.next()).thenReturn("class");
            assertNull(CustomizedScanners.scanHouseClassificationByGender(scanner));
        }

        @Test
        void testScanServices() throws UnacceptableValueException {
            Services expectedServices = new Services(true, true, true, true, true, 3, 3);
            Services result;
            when(scanner.nextBoolean()).thenReturn(true);
            when(scanner.nextInt()).thenReturn(2);
            result = CustomizedScanners.scanServices(scanner);
            assertEquals(expectedServices, result);

            when(scanner.nextInt()).thenReturn(-1);
            assertDoesNotThrow(() -> CustomizedScanners.scanServices(scanner));
            assertNull(CustomizedScanners.scanServices(scanner));
        }
    }