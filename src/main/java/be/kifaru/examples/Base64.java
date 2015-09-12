package be.kifaru.examples;

import javax.xml.bind.DatatypeConverter;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Devid Verfaillie
 * @since 2015-09-07
 */
public enum Base64 {

    /**
     * Singleton instance of this class.
     * <p/>
     * See Effective Java 2nd Edition, items 3 (Enforce the singleton property with a private constructor or an enum
     * type) & 77 (For instance control, prefer enum types to readResolve).
     */
    INSTANCE;

    public byte[] decodeToByteArray(String base64StringToDecode) {
        // use the following class and method instead of org.apache.commons.codec.binary.Base64 for example
        // there is no need for wrapping really
        return DatatypeConverter.parseBase64Binary(base64StringToDecode);
    }

    public String decodeToString(String base64StringToDecode) {
        return new String(decodeToByteArray(base64StringToDecode), UTF_8);
    }

    public String encodeFromByteArray(byte[] byteArrayToEncode) {
        // use the following class and method instead of org.apache.commons.codec.binary.Base64 for example
        // there is no need for wrapping really
        return DatatypeConverter.printBase64Binary(byteArrayToEncode);
    }

    public String encodeFromString(String stringToEncode) {
        return encodeFromByteArray(stringToEncode.getBytes(UTF_8));
    }
}
