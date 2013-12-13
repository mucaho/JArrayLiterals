package jarrayliterals;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Shortcuts for creating arbitrary arrays (e.g. for parameterized unit tests).
 * <p>
 * This class has an overloaded method {@link ArrayShortcuts#$(Object...) <code><T>$(T...): T[]</code>}. 
 * The method is used for creating an array of a generic element type and expects a non-zero, 
 * variable amount of arguments.
 * <p>
 * This class has also fields that start with {@linkplain $}.  The fields represent zero-sized 
 * arrays of a specific element type.
 * <p>
 * Note that both the methods and fields return boxed wrapper arrays instead of their respective, 
 * primitive data type arrays.
 * <pre>
 * <code>$null</code> 	<code>null</code>
 * <code>$</code> 	<code>Object[0]</code>
 * <code>$B</code>	<code>Byte[0]</code>
 * <code>$S</code>	<code>Short[0]</code>
 * <code>$I</code>	<code>Integer[0]</code>
 * <code>$L</code>	<code>Long[0]</code>
 * <code>$F</code>	<code>Float[0]</code>
 * <code>$D</code>	<code>Double[0]</code>
 * <code>$b</code>	<code>Boolean[0]</code>
 * <code>$c</code>	<code>Char[0]</code>
 * </pre>
 * There are also shortcut methods which automatically cast <code>int</code> arguments to 
 * <code>Byte</code>s {@link ArrayShortcuts#$B(int...) <code><T>$B(int...): Byte[]</code>} or
 * <code>short</code>s {@link ArrayShortcuts#$S(int...) <code><T>$S(int...): Short[]</code>}.
 * Be careful that these 2 methods trade performance for brevity.
 * <p>
 * There is also an utility method {@link ArrayShortcuts#toString() toString} which prints
 * <i>anything</i> (including multidimensional arrays).
 * <p>
 * Be sure to use {@link ArrayShortcuts#$null $null} instead of the regular <b>null</b>
 * when nesting arrays.
 * 
 * @author mucaho
 */
public class ArrayShortcuts {
	/*
	 * other primitive types: Boolean.class, Character.class, Void.class
	 */
	@SuppressWarnings("unchecked")
	private static final Set<Class<?>> PRIMITIVE_NUMBER_TYPES = new HashSet<Class<?>>(
			Arrays.asList(
					byte.class, Byte.class, 
					short.class, Short.class, 
					int.class, Integer.class, 
					long.class, Long.class, 
					float.class, Float.class, 
					double.class, Double.class));

	private static boolean isPrimitiveNumber(Class<?> clazz) {
		return PRIMITIVE_NUMBER_TYPES.contains(clazz);
	}
	

	/**
	 * Prints a generic object or generic single-/multi-dimensional array.
	 * In essence prints anything (as long as it has the {@link Object#toString() toString()} 
	 * implemented).
	 * <p>
	 * Numbers are suffixed with the first capital letter of their respective types.
	 * 
	 * @param <T>
	 * @param arr	anything
	 * @return		String representation of <b>arr</b>
	 */
	public static <T> String toString(T arr){
		return toString(arr, 0);
	}

	private static String ident(int amount) {
		String out = "\n";
		for (int i=0; i<amount; ++i)
			out += " ";

		return out;
	}

	private static <T> String toString(T arr, int depth){
		String out = "";

		if (arr == null) {
			out += arr;
		} else if (!arr.getClass().isArray()){
			out += arr.toString();
			Class<?> objClass = arr.getClass();
			if (isPrimitiveNumber(objClass)) {
				out += objClass.getSimpleName().substring(0, 1);
			}
		} else {
			out += ident(depth);
			out += "[";

			int arrLength = Array.getLength(arr);
			for (int i=0; i<arrLength; ++i) {
				if (i != 0) out += ", ";
				out += toString(Array.get(arr, i), depth+1);
				if (i < arrLength-1) out += " ";
			}

			out += "]";
			out += ident(depth-1);
		}

		return out;
	}
	
	/**
	 * Shortcut for returning a generic-typed, variable-dimension array of <code>T</code>s. 
	 * All parameters T passed to this method are returned in an array
	 * <code>T[]</code>. Note that the parameters themselves can be arrays.
	 * 
	 * @param param
	 *            Values to be returned in an <code>T[]</code> array.
	 * @return Values passed to this method.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] $(T param) {
		Class<T> klazz;
		if (param != null) {
			klazz = (Class<T>) param.getClass();
		} else {
			klazz = (Class<T>) Void.class;
		}
		T[] out = (T[]) Array.newInstance(klazz, 1);
		Array.set(out, 0, param);
		return out;
	}
	
	/**
	 * Shortcut for returning a generic-typed, variable-dimension array of <code>T</code>s. 
	 * All parameters T passed to this method are returned in an array
	 * <code>T[]</code>. Note that the parameters themselves can be arrays.
	 * 
	 * @param params
	 *            Values to be returned in an <code>T[]</code> array.
	 * @return Values passed to this method.
	 */
	public static <T> Object $(T... params) {
		if (params != null && params.length == 1) {
			return $((Object)params);
		}
		return params;
	}


	/**
	 * Shortcut for returning an array of Bytes. All parameters passed to this
	 * method are returned in an <code>Byte[]</code> array.
	 *
	 * @param params
	 *            Values to be returned in an <code>Byte[]</code> array.
	 * @return Values passed to this method.
	 */
	public static Byte[] $B(int... params) {
		Byte[] out = new Byte[params.length];
		for (int i=0; i<out.length; ++i) {
			out[i] = (byte) params[i];
		}
		return out;
	}

	/**
	 * Shortcut for returning an array of Shorts. All parameters passed to this
	 * method are returned in an <code>Short[]</code> array.
	 *
	 * @param params
	 *            Values to be returned in an <code>Short[]</code> array.
	 * @return Values passed to this method.
	 */
	public static Short[] $S(int... params) {
		Short[] out = new Short[params.length];
		for (int i=0; i<out.length; ++i) {
			out[i] = (short) params[i];
		}
		return out;
	}



	/**
	 * Shortcut for null. Use this instead of the regular null when nesting arrays.
	 */
	public final static Object $null = null;

	/**
	 * Shortcut for an empty <code>Object[0]</code> array.
	 */
	public final static Object $ = new Object[0];

	/**
	 * Shortcut for returning an empty array of objects.
	 * 
	 * @return an empty <code>Object[]</code> array.
	 */
	public static Object $() {
		return $;
	}

	/**
	 * Shortcut for an empty <code>Byte[0]</code> array.
	 * <br>
	 * Do not confuse with {@link ArrayShortcuts#$b $b}. <code>B</code> stands for "Byte".
	 */
	public final static Byte[] $B = new Byte[0];

	/**
	 * Shortcut for an empty <code>Short[0]</code> array.
	 */
	public final static Short[] $S = new Short[0];

	/**
	 * Shortcut for an empty <code>Integer[0]</code> array.
	 */
	public final static Integer[] $I = new Integer[0];

	/**
	 * Shortcut for an empty <code>Long[0]</code> array.
	 */
	public final static Long[] $L = new Long[0];

	/**
	 * Shortcut for an empty <code>Float[0]</code> array.
	 */
	public final static Float[] $F = new Float[0];

	/**
	 * Shortcut for an empty <code>Double[0]</code> array.
	 */
	public final static Double[] $D = new Double[0];

	/**
	 * Shortcut for an empty <code>Boolean[0]</code> array.
	 * <br>
	 * Do not confuse with {@link ArrayShortcuts#$B $B}. <code>b</code> stands for "bit".
	 */
	public final static Boolean[] $b = new Boolean[0];

	/**
	 * Shortcut for an empty <code>Character[0]</code> array.
	 */
	public final static Character[] $C = new Character[0];   

}
