package livr;

import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author vladislavbaluk (creator)
 * @author Gábor KOLÁROVICS
 * 
 * @since 2017/09/28
 */
public class LIVRUtils {

	public static JSONParser newParser() {
		return new JSONParser();
	}

	public static boolean isPrimitiveValue(Object value) {
		if (value.getClass() == String.class || value.getClass() == Boolean.class)
			return true;
		if (value instanceof Number)
			return true;
		if (value.equals("true") || value.equals("false"))
			return true;
		return false;
	}

	public static boolean looksLikeNumber(Object value) {

		return value instanceof Number || NumberUtils.isCreatable(value + "");
	}

	public static boolean isObject(Object value) {
		return value.getClass() == JSONObject.class;
	}

	public static boolean isEmptyObject(Map<String, String> map) {
		return map.isEmpty();
	}

	public static boolean isNoValue(Object value) {
		return value == null || (value + "").equals("");
	}

	public static boolean isInteger(Object value) {
		if (value instanceof Long) {
			return true;
		}

		return NumberUtils.isDigits(value + "");
	}

	public static boolean isDecimal(Object value) {
		if (value instanceof Double) {
			return true;
		}

		try {
			Double.valueOf(value + "");
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
