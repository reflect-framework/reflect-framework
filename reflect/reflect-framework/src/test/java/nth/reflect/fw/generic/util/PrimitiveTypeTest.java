package nth.reflect.fw.generic.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

public class PrimitiveTypeTest {

	// =================== primitiveToWraper
	@Test
	public void testPrimitiveToWraper_givenNull_mustReturnEmpty() {
		assertThat(PrimitiveType.primitiveToWrapper(null)).isEqualTo(Optional.empty());
	}

	@Test
	public void testPrimitiveToWraper_givenString_mustReturnEmpty() {
		assertThat(PrimitiveType.primitiveToWrapper(String.class)).isEqualTo(Optional.empty());
	}

	@Test
	public void testPrimitiveToWraper_givenPrimitiveBoolean_mustReturnBoolean() {
		assertThat(PrimitiveType.primitiveToWrapper(boolean.class)).isEqualTo(Optional.of(Boolean.class));
	}

	@Test
	public void testIsPrimative_givenPrimitiveByte_mustReturnByte() {
		assertThat(PrimitiveType.primitiveToWrapper(byte.class)).isEqualTo(Optional.of(Byte.class));
	}

	@Test
	public void testIsPrimative_givenPrimitiveCharacter_mustReturnCharacter() {
		assertThat(PrimitiveType.primitiveToWrapper(char.class)).isEqualTo(Optional.of(Character.class));
	}

	@Test
	public void testIsPrimative_givenPrimitiveShort_mustReturnShort() {
		assertThat(PrimitiveType.primitiveToWrapper(short.class)).isEqualTo(Optional.of(Short.class));
	}

	@Test
	public void testIsPrimative_givenPrimitiveInt_mustReturnInteger() {
		assertThat(PrimitiveType.primitiveToWrapper(int.class)).isEqualTo(Optional.of(Integer.class));
	}

	@Test
	public void testIsPrimative_givenPrimitiveLong_mustReturnLong() {
		assertThat(PrimitiveType.primitiveToWrapper(long.class)).isEqualTo(Optional.of(Long.class));
	}

	@Test
	public void testIsPrimative_givenPrimitiveDouble_mustReturnDouble() {
		assertThat(PrimitiveType.primitiveToWrapper(double.class)).isEqualTo(Optional.of(Double.class));
	}

	@Test
	public void testIsPrimative_givenPrimitiveFloat_mustReturnFloat() {
		assertThat(PrimitiveType.primitiveToWrapper(float.class)).isEqualTo(Optional.of(Float.class));
	}

	// =================== isPrimative
	@Test
	public void testIsPrimitive_givenNull_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimitive(null)).isEqualTo(false);
	}

	@Test
	public void testIsPrimitive_givenString_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimitive(String.class)).isEqualTo(false);
	}

	@Test
	public void testIsPrimitive_givenBoolean_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimitive(Boolean.class)).isEqualTo(false);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveBoolean_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitive(boolean.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveByte_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitive(byte.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveCharacter_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitive(char.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveShort_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitive(short.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveInt_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitive(int.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveLong_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitive(long.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveDouble_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitive(double.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveFloat_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitive(float.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveVoid_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitive(void.class)).isEqualTo(true);
	}

	
	// =================== isPrimativeNumber
	@Test
	public void testIsPrimitiveNumber_givenNull_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimitiveNumber(null)).isEqualTo(false);
	}

	@Test
	public void testIsPrimitiveNumber_givenString_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimitiveNumber(String.class)).isEqualTo(false);
	}

	@Test
	public void testIsPrimitiveNumber_givenBoolean_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimitiveNumber(Boolean.class)).isEqualTo(false);
	}

	@Test
	public void testIsPrimitiveNumber_givenPrimitiveBoolean_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimitiveNumber(boolean.class)).isEqualTo(false);
	}

	@Test
	public void testIsPrimitiveNumber_givenPrimitiveByte_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitiveNumber(byte.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitiveNumber_givenPrimitiveCharacter_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimitiveNumber(char.class)).isEqualTo(false);
	}

	@Test
	public void testIsPrimitiveNumber_givenPrimitiveShort_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitiveNumber(short.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitiveNumber_givenPrimitiveInt_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitiveNumber(int.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitiveNumber_givenPrimitiveLong_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitiveNumber(long.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitiveNumber_givenPrimitiveDouble_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitiveNumber(double.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitiveNumber_givenPrimitiveFloat_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimitiveNumber(float.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitiveNumber_givenPrimitiveVoid_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimitiveNumber(void.class)).isEqualTo(false);
	}
	
	// =================== wrapperToPrimitive
	@Test
	public void testWraperToPrimitive_givenNull_mustReturnEmpty() {
		assertThat(PrimitiveType.wrapperToPrimitive(null)).isEqualTo(Optional.empty());
	}

	@Test
	public void testWraperToPrimitive_givenString_mustReturnEmpty() {
		assertThat(PrimitiveType.wrapperToPrimitive(String.class)).isEqualTo(Optional.empty());
	}

	@Test
	public void testWraperToPrimitive_givenBoolean_mustReturnPrimitiveBoolean() {
		assertThat(PrimitiveType.wrapperToPrimitive(Boolean.class)).isEqualTo(Optional.of(boolean.class));
	}

	@Test
	public void testWraperToPrimitive_givenByte_mustReturnPrimitiveByte() {
		assertThat(PrimitiveType.wrapperToPrimitive(Byte.class)).isEqualTo(Optional.of(byte.class));
	}

	@Test
	public void testWraperToPrimitive_givenCharacter_mustReturnPrimitiveCharacter() {
		assertThat(PrimitiveType.wrapperToPrimitive(Character.class)).isEqualTo(Optional.of(char.class));
	}

	@Test
	public void testWraperToPrimitive_givenShort_mustReturnPrimitiveShort() {
		assertThat(PrimitiveType.wrapperToPrimitive(Short.class)).isEqualTo(Optional.of(short.class));
	}

	@Test
	public void testWraperToPrimitive_givenInteger_mustReturnPrimitiveInteger() {
		assertThat(PrimitiveType.wrapperToPrimitive(Integer.class)).isEqualTo(Optional.of(int.class));
	}

	@Test
	public void testWraperToPrimitive_givenLong_mustReturnPrimitiveLong() {
		assertThat(PrimitiveType.wrapperToPrimitive(Long.class)).isEqualTo(Optional.of(long.class));
	}

	@Test
	public void testWraperToPrimitive_givenDouble_mustReturnPrimitiveDouble() {
		assertThat(PrimitiveType.wrapperToPrimitive(Double.class)).isEqualTo(Optional.of(double.class));
	}

	@Test
	public void testWraperToPrimitive_givenFloat_mustReturnPrimitiveFloat() {
		assertThat(PrimitiveType.wrapperToPrimitive(Float.class)).isEqualTo(Optional.of(float.class));
	}

	// =================== isWrapper
	@Test
	public void testIsWrapper_givenNull_mustReturnFalse() {
		assertThat(PrimitiveType.isWrapper(null)).isEqualTo(false);
	}

	@Test
	public void testIsWrapper_givenString_mustReturnFalse() {
		assertThat(PrimitiveType.isWrapper(String.class)).isEqualTo(false);
	}

	@Test
	public void testIsWrapper_givenPrimativeBoolean_mustReturnFalse() {
		assertThat(PrimitiveType.isWrapper(boolean.class)).isEqualTo(false);
	}

	@Test
	public void testIsWrapper_givenBoolean_mustReturnTrue() {
		assertThat(PrimitiveType.isWrapper(Boolean.class)).isEqualTo(true);
	}

	@Test
	public void testIsWrapper_givenByte_mustReturnTrue() {
		assertThat(PrimitiveType.isWrapper(Byte.class)).isEqualTo(true);
	}

	@Test
	public void testIsWrapper_givenCharacter_mustReturnTrue() {
		assertThat(PrimitiveType.isWrapper(Character.class)).isEqualTo(true);
	}

	@Test
	public void testIsWrapper_givenShort_mustReturnTrue() {
		assertThat(PrimitiveType.isWrapper(Short.class)).isEqualTo(true);
	}

	@Test
	public void testIsWrapper_givenInt_mustReturnTrue() {
		assertThat(PrimitiveType.isWrapper(Integer.class)).isEqualTo(true);
	}

	@Test
	public void testIsWrapper_givenLong_mustReturnTrue() {
		assertThat(PrimitiveType.isWrapper(Long.class)).isEqualTo(true);
	}

	@Test
	public void testIsWrapper_givenDouble_mustReturnTrue() {
		assertThat(PrimitiveType.isWrapper(Double.class)).isEqualTo(true);
	}

	@Test
	public void testIsWrapper_givenFloat_mustReturnTrue() {
		assertThat(PrimitiveType.isWrapper(Float.class)).isEqualTo(true);
	}
}
