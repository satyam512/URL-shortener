package com.systemdesign.urlshortener;

import com.systemdesign.urlshortener.service.SimpleEncoder;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlShortenerApplicationTests {

	public SimpleEncoder encoder = new SimpleEncoder("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ=_",
			6);

	@Test
	public void testEncoding() {
		String encodedInteger = encoder.encodeInteger(2l);
		encodedInteger = encoder.addPadding(encodedInteger);
		assertEquals("000002", encodedInteger);
	}
	@Test
	public void testEncoding2() {
		String encodedInteger = encoder.encodeInteger(3l);
		encodedInteger = encoder.addPadding(encodedInteger);
		assertEquals("000003", encodedInteger);
	}
	@Test
	public void testEncoding3() {
		String encodedInteger = encoder.encodeInteger(4l);
		encodedInteger = encoder.addPadding(encodedInteger);
		assertEquals("000004", encodedInteger);
	}
	@Test
	public void testEncoding4() {
		String encodedInteger = encoder.encodeInteger(456l);
		encodedInteger = encoder.addPadding(encodedInteger);
		assertEquals("000078", encodedInteger);
	}

	@Test
	public void testEncoding5() {
		Long index = encoder.getDecodedIndex("000078");
		assertEquals(456l, index);
	}

}
