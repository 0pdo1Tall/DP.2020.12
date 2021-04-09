package utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * The {@link MyMap JSON} class represents JSON objects.
 * To create a new JSON object,
 * JSON jsonObject = new JSON();
 * jsonObject.put("key", value);
 * 
 * @author hieud
 *
 */

/*
 * Coincidental cohesion, do phương thức getNextTerm không có chức năng theo tên class, mà chỉ hỗ trợ xử lí các hàm khác 
 */
public class MyMap extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	/**
	 *  SOLID: Vi phạm nguyên lý SRP, do thực hiện đến hai nhiệm vụ. Một nhiệm vụ liên quan đến xử lý chức năng MyMap.
	 *  Một nhiệm vụ liên quan tới sử dụng phương thức getNextTerm để tìm cụm tiếp theo trong xâu.
	 */
	
	
	/**
	 *  Coincidental cohesion, do có sử dụng phương thức getNextTerm(String str, int idx)
	 *  phương thức này không cùng nhóm với các phương thức trong class MyMap khi xử lý liên quan đến xâu
	 *  không hề liên quan đến các chức năng của lớp MyMap cung cấp
	 */
	
	/**
	 * Return a {@link String String} that represents the JSON object.
	 * 
	 * @author hieudm
	 *         https://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/Hashtable.java
	 * @return a {@link String String}.
	 */
	public String toJSON() {
		int maxIndex = size() - 1;
		if (maxIndex == -1)
			return "{}";
		// Clean Code: sb to keyValueStringBuilder, i to index,max to maxIndex
		StringBuilder keyValueStringBuilder = new StringBuilder();
		Iterator<Map.Entry<String, Object>> it = entrySet().iterator();

		keyValueStringBuilder.append('{');
		for (int index = 0;; index++) {
			Map.Entry<String, Object> e = it.next();
			String key = e.getKey();
			Object value = e.getValue();
			keyValueStringBuilder.append('"' + key.toString() + '"');
			keyValueStringBuilder.append(':');
			keyValueStringBuilder.append(value instanceof MyMap ? ((MyMap) value).toJSON() : ('"' + value.toString() + '"'));

//			if (value instanceof MyMap) {
//				sb.append(((MyMap) value).toJSON());
//			} else {
//				sb.append('"' + value.toString() + '"');
//			}
			if (index == maxIndex)
				return keyValueStringBuilder.append('}').toString();
			keyValueStringBuilder.append(",");
		}
		
		// Data coupling, chỉ nhận vào đủ dữ liệu để xử lý
	}

	/**
	 * Return a {@link Map Map} that represents the mapping among
	 * attribute names and their values of an object.
	 * 
	 * @author hieudm
	 *         https://stackoverflow.com/questions/52406467/convert-object-to-map-in-java
	 * @param obj - an arbitrary {@link Object Object}.
	 * @return a {@link Map Map} mapping the attribute names and its
	 *         values.
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static Map<String, Object> toMyMap(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> map = new MyMap();
		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
		fields.addAll(Arrays.asList(obj.getClass().getSuperclass().getDeclaredFields()));

		for (Field field : fields) {
			field.setAccessible(true);
			Object value = field.get(obj);
			try {
				if (!value.getClass().getPackage().getName().startsWith("java")) {
					value = MyMap.toMyMap(value).toString();
				}
			} catch (Exception ex) {
				;
			}
			map.put(field.getName(), value);
			field.setAccessible(false);
		}
		return map;
		
		// Data coupling, nhận đủ dữ liệu để xử lý nghiệp vụ
	}

	private static int offset = 0; // to trace the current index when calling a function

	/**
	 * Return a {@link String String} that represents the term in between
	 * 2 double quote.
	 * 
	 * @author hieudm
	 * @param 
	 * str - {@link String String}
	 * idx - the index of the open quote
	 * @return the term as {@link String String}
	 * @throws IllegalArgumentException
	 */
	private static String getNextTerm(String str, int idx) {
		if (str == null || idx >= str.length() || str.charAt(idx) != '"') {
			throw new IllegalArgumentException("Cannot resolve the input.");
		}

		if (str.charAt(idx + 1) == '"') {
			return new String();
		}

		int i = idx + 1;
		StringBuilder sb = new StringBuilder();
		do {
			sb.append(str.charAt(i));
			i++;
			if (i == str.length()) {
				throw new IllegalArgumentException("Cannot resolve the input.");
			}
		} while (str.charAt(i) != '"');

		String result = sb.toString();
		offset = result.length() + 2; // update iterator with the term and the 2 double quotes
		return sb.toString();
		
		// Data coupling, phương thức nhận và xử lý dữ liệu vừa đủ
	}
	/**
	 * Return a {@link MyMap MyMap} that represents the interested substring in a {@link String String}.
	 * 
	 * @author hieudm
	 * @param 
	 * str - {@link String String}
	 * idx - the index of the first character in the interested substring in the {@link String String}
	 * @return the term as {@link MyMap MyMap}
	 * @throws IllegalArgumentException
	 */
	public static MyMap toMyMap(String str, int idx) throws IllegalArgumentException {
		if (str == null || str.length() < 2 || str.charAt(idx) != '{') {
			throw new IllegalArgumentException("Cannot resolve the input.");
		} else if (idx >= str.length()) {
			return null;
		}

		MyMap root = new MyMap();
		StringBuilder sb = new StringBuilder();
		int i = idx;
		sb.append(str.charAt(i));

		i++;
		try {
			while (true) {
				// open quote
				if (str.charAt(i) != '"') {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}
				// get key
				String key;
				try {
					key = getNextTerm(str, i);
				} catch (Exception ex) {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}
				if (key == null) {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}

				sb.append(str.subSequence(i, i + offset));

				i += offset;
				offset = 0;

				// check colon
				sb.append(str.charAt(i));
				if (str.charAt(i) != ':') {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}
				i++;
				// get value
				Object value;
				if (str.charAt(i) == '{') {
					value = toMyMap(str, i);
					sb.append(str.subSequence(i, i + offset));
					i += offset;
					offset = 0;
				} else if (str.charAt(i) == '"') {
					try {
						value = getNextTerm(str, i);
					} catch (Exception ex) {
						throw new IllegalArgumentException("Cannot resolve the input.");
					}
					if (value == null) {
						throw new IllegalArgumentException("Cannot resolve the input.");
					}
					sb.append(str.subSequence(i, i + offset));
					i += offset;
					offset = 0;
				} else {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}
				//
				root.put(key, value);
				if (str.charAt(i) == ',') {
					sb.append(str.charAt(i));
					i++;
				} else if (str.charAt(i) == '}') {
					sb.append(str.charAt(i));
					break;
				} else {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}
			}
			offset = sb.toString().length();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Cannot resolve the input.");
		}
		return root;
		
		// Data coupling, phương thức nhận lượng dữ liệu không dư thừa để tiến hành xử lý
	}

}
