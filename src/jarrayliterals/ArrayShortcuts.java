package jarrayliterals;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Shortcuts for creating arrays for use as test data.
 * <p>
 * This class has an overloaded method {@link ArrayShortcuts#$(Object...) <code><T>$(T...): T[]</code>}. 
 * The method is used for creating an array of a generic element type and expects a non-zero, 
 * variable amount of arguments.
 * <p>
 * This class has also fields that start with {@linkplain $}.  The fields represent zero-sized 
 * arrays of a specific element type.
 * <pre>
 * <code>$null</code> 	<code>null</code>
 * <code>$</code> 	<code>Object[0]</code>
 * <code>$B</code>	<code>byte[0]</code>
 * <code>$S</code>	<code>short[0]</code>
 * <code>$I</code>	<code>int[0]</code>
 * <code>$L</code>	<code>long[0]</code>
 * <code>$F</code>	<code>float[0]</code>
 * <code>$D</code>	<code>double[0]</code>
 * <code>$b</code>	<code>boolean[0]</code>
 * <code>$c</code>	<code>char[0]</code>
 * </pre>
 * There are also shortcut methods which automatically cast <code>int</code> arguments to 
 * <code>byte</code>s {@link ArrayShortcuts#$B(int...) <code><T>$B(int...): byte[]</code>} or
 * <code>short</code>s {@link ArrayShortcuts#$S(int...) <code><T>$B(int...): byte[]</code>}.
 * These methods should not be used in production code, as they trade performance for
 * brevity.
 * <p>
 * There is also an utility method {@link ArrayShortcuts#toString() toString()} which prints
 * <i>anything</i> (including multidimensional arrays).
 * <p>
 * Example:
 * <pre> <code>
 * Object obj = $($(
 * 	$(1f, 2f), $(3f, 4f), $(5f, 6f), $(7f, 8f)
 * ), $(
 * 	$(100L), $(200L)
 * ), $(
 * 	"a", "B"
 * ), $(
 * 	$($($($($(2d)))))
 * ), $(
 * 	$($(true), $(true, false), $(false, false, false))
 * ), $(
 * 	$('a', 'c')
 * ), $(
 * 	$S(2,3,4), $S(1,5,6,7,8,9,0)
 * ), $(
 * 	$($, $($null))
 * ), $(
 * 	$B(2,5,6,7,8,9), $B(101,199), $B
 * ), $(
 * 	$($($($($($())))))
 * ),  $(
 *	$($($($($($B)))))
 * ));
 * System.out.println(toString(obj));
 * </code> </pre>
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
			int nrDims = 1 + arr.getClass().getName().lastIndexOf('[');
			
			out += "[";
			for (int i=0; i<nrDims; ++i)
				out += " ";

			int arrLength = Array.getLength(arr);
			for (int i=0; i<arrLength; ++i) {
				if (i != 0) out += ", ";
				out += toString(Array.get(arr, i));
				if (i < arrLength-1) out += " ";
			}

			for (int i=0; i<nrDims; ++i)
				out += " ";
			out += "]";
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
	 * Shortcut for returning an array of bytes. All parameters passed to this
	 * method are returned in an <code>byte[]</code> array.
	 *
	 * @param params
	 *            Values to be returned in an <code>byte[]</code> array.
	 * @return Values passed to this method.
	 */
	public static byte[] $B(int... params) {
		byte[] out = new byte[params.length];
		for (int i=0; i<out.length; ++i) {
			out[i] = (byte) params[i];
		}
		return out;
	}

	/**
	 * Shortcut for returning an array of shorts. All parameters passed to this
	 * method are returned in an <code>short[]</code> array.
	 *
	 * @param params
	 *            Values to be returned in an <code>short[]</code> array.
	 * @return Values passed to this method.
	 */
	public static short[] $S(int... params) {
		short[] out = new short[params.length];
		for (int i=0; i<out.length; ++i) {
			out[i] = (short) params[i];
		}
		return out;
	}



	/**
	 * Shortcut for null.
	 */
	public final static Object $null = null;

	/**
	 * Shortcut for an empty array of objects.
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
	 * Shortcut for an empty array of bytes.
	 */
	public final static byte[] $B = new byte[0];

	/**
	 * Shortcut for an empty array of shorts.
	 */
	public final static short[] $S = new short[0];

	/**
	 * Shortcut for an empty array of integers.
	 */
	public final static int[] $I = new int[0];

	/**
	 * Shortcut for an empty array of longs.
	 */
	public final static long[] $L = new long[0];

	/**
	 * Shortcut for an empty array of floats.
	 */
	public final static float[] $F = new float[0];

	/**
	 * Shortcut for an empty array of doubles.
	 */
	public final static double[] $D = new double[0];

	/**
	 * Shortcut for an empty array of booleans.
	 */
	public final static boolean[] $b = new boolean[0];

	/**
	 * Shortcut for an empty array of chars.
	 */
	public final static char[] $c = new char[0];   

}
