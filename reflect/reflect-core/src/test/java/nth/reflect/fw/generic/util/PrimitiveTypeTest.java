package nth.reflect.fw.generic.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PrimitiveTypeTest {

	//=================== primitiveToWraper
	@Test
	public void testPrimitiveToWraper_givenNull_mustReturnNull() {
		assertThat(PrimitiveType.primitiveToWrapper(null)).isEqualTo(null);
	}

	@Test
	public void testPrimitiveToWraper_givenString_mustReturnNull() {
		assertThat(PrimitiveType.primitiveToWrapper(String.class)).isEqualTo(null);
	}

	@Test
	public void testPrimitiveToWraper_givenPrimitiveBoolean_mustReturnBoolean() {
		assertThat(PrimitiveType.primitiveToWrapper(boolean.class)).isEqualTo(Boolean.class);
	}

	@Test
	public void testIsPrimative_givenPrimitiveByte_mustReturnByte() {
		assertThat(PrimitiveType.primitiveToWrapper(byte.class)).isEqualTo(Byte.class);
	}

	@Test
	public void testIsPrimative_givenPrimitiveCharacter_mustReturnCharacter() {
		assertThat(PrimitiveType.primitiveToWrapper(char.class)).isEqualTo(Character.class);
	}

	@Test
	public void testIsPrimative_givenPrimitiveShort_mustReturnShort() {
		assertThat(PrimitiveType.primitiveToWrapper(short.class)).isEqualTo(Short.class);
	}

	@Test
	public void testIsPrimative_givenPrimitiveInt_mustReturnInteger() {
		assertThat(PrimitiveType.primitiveToWrapper(int.class)).isEqualTo(Integer.class);
	}

	@Test
	public void testIsPrimative_givenPrimitiveLong_mustReturnLong() {
		assertThat(PrimitiveType.primitiveToWrapper(long.class)).isEqualTo(Long.class);
	}
	
	@Test
	public void testIsPrimative_givenPrimitiveDouble_mustReturnDouble() {
		assertThat(PrimitiveType.primitiveToWrapper(double.class)).isEqualTo(Double.class);
	}

	@Test
	public void testIsPrimative_givenPrimitiveFloat_mustReturnFloat() {
		assertThat(PrimitiveType.primitiveToWrapper(float.class)).isEqualTo(Float.class);
	}

	//=================== isPrimative
	@Test
	public void testIsPrimitive_givenNull_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimative(null)).isEqualTo(false);
	}
	
	@Test
	public void testIsPrimitive_givenString_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimative(String.class)).isEqualTo(false);
	}
	
	@Test
	public void testIsPrimitive_givenBoolean_mustReturnFalse() {
		assertThat(PrimitiveType.isPrimative(Boolean.class)).isEqualTo(false);
	}
	
	@Test
	public void testIsPrimitive_givenPrimitiveBoolean_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimative(boolean.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveByte_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimative(byte.class)).isEqualTo(true);
	}
	@Test
	public void testIsPrimitive_givenPrimitiveCharacter_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimative(char.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveShort_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimative(short.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveInt_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimative(int.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveLong_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimative(long.class)).isEqualTo(true);
	}
	@Test
	public void testIsPrimitive_givenPrimitiveDouble_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimative(double.class)).isEqualTo(true);
	}

	@Test
	public void testIsPrimitive_givenPrimitiveFloat_mustReturnTrue() {
		assertThat(PrimitiveType.isPrimative(float.class)).isEqualTo(true);
	}
	
	//=================== wrapperToPrimitive
	@Test
	public void testWraperToPrimitive_givenNull_mustReturnNull() {
		assertThat(PrimitiveType.wrapperToPrimitive(null)).isEqualTo(null);
	}

	@Test
	public void testWraperToPrimitive_givenString_mustReturnNull() {
		assertThat(PrimitiveType.wrapperToPrimitive(String.class)).isEqualTo(null);
	}

	@Test
	public void testWraperToPrimitive_givenBoolean_mustReturnPrimitiveBoolean() {
		assertThat(PrimitiveType.wrapperToPrimitive(Boolean.class)).isEqualTo(boolean.class);
	}

	@Test
	public void testWraperToPrimitive_givenByte_mustReturnPrimitiveByte() {
		assertThat(PrimitiveType.wrapperToPrimitive(Byte.class)).isEqualTo(byte.class);
	}

	@Test
	public void testWraperToPrimitive_givenCharacter_mustReturnPrimitiveCharacter() {
		assertThat(PrimitiveType.wrapperToPrimitive(Character.class)).isEqualTo(char.class);
	}

	@Test
	public void testWraperToPrimitive_givenShort_mustReturnPrimitiveShort() {
		assertThat(PrimitiveType.wrapperToPrimitive(Short.class)).isEqualTo(short.class);
	}

	@Test
	public void testWraperToPrimitive_givenInteger_mustReturnPrimitiveInteger() {
		assertThat(PrimitiveType.wrapperToPrimitive(Integer.class)).isEqualTo(int.class);
	}

	@Test
	public void testWraperToPrimitive_givenLong_mustReturnPrimitiveLong() {
		assertThat(PrimitiveType.wrapperToPrimitive(Long.class)).isEqualTo(long.class);
	}
	
	@Test
	public void testWraperToPrimitive_givenDouble_mustReturnPrimitiveDouble() {
		assertThat(PrimitiveType.wrapperToPrimitive(Double.class)).isEqualTo(double.class);
	}

	@Test
	public void testWraperToPrimitive_givenFloat_mustReturnPrimitiveFloat() {
		assertThat(PrimitiveType.wrapperToPrimitive(Float.class)).isEqualTo(float.class);
	}

	//=================== isWrapper
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
