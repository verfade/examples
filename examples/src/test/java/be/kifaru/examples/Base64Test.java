package be.kifaru.examples;

import org.junit.Test;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Tests {@link Base64}.
 *
 * @author Devid Verfaillie
 * @since 2015-09-12
 */
public class Base64Test {

    // Copied from https://en.wikipedia.org/wiki/Base64#Examples
    private static final String PLAINTEXT =
            "Man is distinguished, not only by his reason, but by this singular passion from"
                    + " other animals, which is a lust of the mind, that by a perseverance of delight"
                    + " in the continued and indefatigable generation of knowledge, exceeds the short"
                    + " vehemence of any carnal pleasure.";

    private static final byte[] PLAINTEXT_BYTES = PLAINTEXT.getBytes(UTF_8);

    // Copied from https://en.wikipedia.org/wiki/Base64#Examples
    private static final String PLAINTEXT_BASE_64 =
            "TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGlz"
                    + "IHNpbmd1bGFyIHBhc3Npb24gZnJvbSBvdGhlciBhbmltYWxzLCB3aGljaCBpcyBhIGx1c3Qgb2Yg"
                    + "dGhlIG1pbmQsIHRoYXQgYnkgYSBwZXJzZXZlcmFuY2Ugb2YgZGVsaWdodCBpbiB0aGUgY29udGlu"
                    + "dWVkIGFuZCBpbmRlZmF0aWdhYmxlIGdlbmVyYXRpb24gb2Yga25vd2xlZGdlLCBleGNlZWRzIHRo"
                    + "ZSBzaG9ydCB2ZWhlbWVuY2Ugb2YgYW55IGNhcm5hbCBwbGVhc3VyZS4=";

    @Test
    public void decodeToByteArray() throws Exception {
        byte[] decodedBase64Bytes = Base64.INSTANCE.decodeToByteArray(PLAINTEXT_BASE_64);
        assertThat(decodedBase64Bytes, is(equalTo(PLAINTEXT_BYTES)));
    }

    @Test
    public void decodeToString() throws Exception {
        String actualPlaintext = Base64.INSTANCE.decodeToString(PLAINTEXT_BASE_64);
        assertThat(actualPlaintext, is(equalTo(PLAINTEXT)));
    }

    @Test
    public void encodeFromByteArray() throws Exception {
        String actualBase64 = Base64.INSTANCE.encodeFromByteArray(PLAINTEXT_BYTES);
        assertThat(actualBase64, is(equalTo(PLAINTEXT_BASE_64)));
    }

    @Test
    public void encodeFromString() throws Exception {
        String actualBase64 = Base64.INSTANCE.encodeFromString(PLAINTEXT);
        assertThat(actualBase64, is(equalTo(PLAINTEXT_BASE_64)));
    }
}
