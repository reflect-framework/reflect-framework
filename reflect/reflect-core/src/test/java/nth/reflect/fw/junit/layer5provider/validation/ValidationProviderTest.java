package nth.reflect.fw.junit.layer5provider.validation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.BeforeClass;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;

public class ValidationProviderTest  {

	private static ValidationProvider validationProvider;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ReflectApplicationForJUnit application=new ReflectApplicationForJUnit() ;
		DependencyInjectionContainer container = application.createContainer();
		validationProvider=container.get(ValidationProvider.class);
	}
	
	@Test
    public void jsr303Validation_isNull() {
        Car car = new Car( null, "DD-AB-123", 4 );

        Set<ConstraintViolation<Object>> constraintViolations =
        		validationProvider.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "may not be null", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void jsr303Validation_tooShort() {
        Car car = new Car( "Morris", "D", 4 );

        Set<ConstraintViolation<Object>> constraintViolations =
        		validationProvider.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "size must be between 2 and 14",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void jsr303Validation_countTooLow() {
        Car car = new Car( "Morris", "DD-AB-123", 1 );

        Set<ConstraintViolation<Object>> constraintViolations =
        		validationProvider.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "must be greater than or equal to 2",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void jsr303Validation_isValid() {
        Car car = new Car( "Morris", "DD-AB-123", 2 );

        Set<ConstraintViolation<Object>> constraintViolations =
        		validationProvider.validate( car );

        assertEquals( 0, constraintViolations.size() );
    }
    
    @Test
    public void validationMethodForClass_AddressExists() {
    	Address address=new Address("My Street","1566LL",Country.NETHERLANDS);
    	Set<ConstraintViolation<Object>> constraintViolations =
        		validationProvider.validate( address );
    	
    	assertEquals( 0, constraintViolations.size() );
    }
    
    @Test
    public void validationMethodForClass_AddressNotExists() {
    	Address address=new Address("bogus","1566LL",Country.NETHERLANDS);
    	Set<ConstraintViolation<Object>> constraintViolations =
        		validationProvider.validate( address );
    	
    	
    	 assertEquals( 1, constraintViolations.size() );
         assertEquals(
        		 "address does not exist",
                 constraintViolations.iterator().next().getMessage()
         );
    }


    @Test
    public void validationMethodForPropertyClass_GoodZipCode() {
    	Address address=new Address("My Street","1566LL",Country.NETHERLANDS);
    	Set<ConstraintViolation<Object>> constraintViolations =
        		validationProvider.validate( address );
    	
    	assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void validationMethodForPropertyClass_BadZipCode() {
    	Address address=new Address("My Street","bogus",Country.NETHERLANDS);
    	Set<ConstraintViolation<Object>> constraintViolations =
        		validationProvider.validate( address );
    	
    	 assertEquals( 1, constraintViolations.size() );
         assertEquals(
        		 "must contain 4 numbers followed by 2 letters",
                 constraintViolations.iterator().next().getMessage()
         );
    }
    
}
