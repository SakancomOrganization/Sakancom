package test_models;

import enums.SaleStatus;
import enums.UserType;
import exceptions.InvalidEmailFormatException;
import exceptions.UnacceptableValueException;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestSaleContract {
    private SaleContract saleContract;
    private User tenant;
    private Date date;

    @Before
    public void setup() throws ParseException, UnacceptableValueException, InvalidEmailFormatException {
        tenant = new User("mo-alawneh",
                "Mohammad12002",
                UserType.TENANT,
                new Name("Mohammad","AbdAllateef","Alawneh"),
                new UserLocation("Jenin","Abu-Baker Street","4070",1),
                new ContactInfo("mo.a.alawneh@gmail.com","0592838433",new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002"),"Computer Engineering"));
        date = new SimpleDateFormat("dd/MM/yyyy").parse("07/07/2023");
        saleContract = new SaleContract(null,
                null,
                SaleStatus.AVAILABLE);
    }

    @Test
    public void testDate() {
        saleContract.setDate(date);
        assertEquals(date, saleContract.getDate());
    }

    @Test
    public void testTenant() {
        saleContract.setTenant(tenant);
        assertEquals(tenant, saleContract.getTenant());
    }

    @Test
    public void testSaleStatus() {
        saleContract.setSaleStatus(SaleStatus.REQUESTED);
        assertEquals(SaleStatus.REQUESTED, saleContract.getSaleStatus());
    }
}
