package nth.introspect.ui.style;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaterialColorPaletteTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGray() {
		MaterialColorPalette gray = MaterialColorPalette.GREY;
		assertEquals("9E9E9E",ColorUtil.getHexName(gray.getColor500().getBackground()));
		assertEquals("000000",ColorUtil.getHexName(gray.getColor500().getForeground1()));
		assertEquals("E0E0E0",ColorUtil.getHexName(gray.getColor300().getBackground()));
		assertEquals("000000",ColorUtil.getHexName(gray.getColor300().getForeground1()));
		assertEquals("F5F5F5",ColorUtil.getHexName(gray.getColor100().getBackground()));
		assertEquals("000000",ColorUtil.getHexName(gray.getColor100().getForeground1()));
	}
	
	@Test
	public void testBlueGray() {
		MaterialColorPalette gray = MaterialColorPalette.BLUE_GRAY;
		assertEquals("607D8B",ColorUtil.getHexName(gray.getColor500().getBackground()));
		assertEquals("FFFFFF",ColorUtil.getHexName(gray.getColor500().getForeground1()));
		assertEquals("90A4AE",ColorUtil.getHexName(gray.getColor300().getBackground()));
		assertEquals("000000",ColorUtil.getHexName(gray.getColor300().getForeground1()));
		assertEquals("CFD8DC",ColorUtil.getHexName(gray.getColor100().getBackground()));
		assertEquals("000000",ColorUtil.getHexName(gray.getColor100().getForeground1()));
	}
}
