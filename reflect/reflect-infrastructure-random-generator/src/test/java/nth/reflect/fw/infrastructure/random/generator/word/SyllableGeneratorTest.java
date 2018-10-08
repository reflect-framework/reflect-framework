package nth.reflect.fw.infrastructure.random.generator.word;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class SyllableGeneratorTest {

	@Test
	public void testSyllableGenerator() {
		List<String> syllables = Random.syllable().generateList(100);
		for (String syllable : syllables) {
			assertTrue("Too many characters in: "+syllable,syllable.length()<=4);
			assertTrue("Too few characters in: "+syllable,syllable.length()>=2);
		}
	}

}
