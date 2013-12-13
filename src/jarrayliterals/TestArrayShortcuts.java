package jarrayliterals;

import static jarrayliterals.ArrayShortcuts.*;

import java.util.Arrays;

public class TestArrayShortcuts {

	public static void complexExample() {
		Object obj =
		$($(
				$(1f, 2f), $(3f, 4f), $(5f, 6f), $(7f, 8f)
		), $(
				$(100L), $(200L)
		), $(
				"a", "B"
		), $(
				$($($($($(2d)))))
		), $(
				$($(true), $(true, false), $(false, false, false))
		), $(
				$('a', 'c')
		), $(
				$S(2,3,4), $S(1,5,6,7,8,9,0)
		), $(
				$($, $($null))
		), $(
				$B(2,5,6,7,8,9), $B(101,199), $B
		), $(
				$($($($($($())))))
		),  $(
				$($($($($($B)))))
		));
		
		System.out.println(ArrayShortcuts.toString(obj));
	}
	
	
	public static void simpleExample() {
		Object[] flat = (Object[])
			$( $b, $(true, false), $($), $S(1,2,3,4), $B(2,3), $('c'), $($null), $, $null );
		testTypes(flat);

		System.out.println();
		
		Object[] tediousTempVar = new Object[][]{{}};
		Object[][] flat2 = new Object[][]{
				{},
				{true, false},
				tediousTempVar,
				{(short) 1, (short) 2, (short) 3, (short) 4},
				{(byte) 2, (byte) 3},
				{'c'},
				{null},
				{},
				null
		};
		testTypes(flat2);
	}
	
	private static void testTypes(Object[] arr) {
		System.out.println(Arrays.deepToString(arr));
		for (int i=0; i<arr.length; i++) {
			if (arr[i] != null) {
				System.out.println(""+i+"th element is typeof "+arr[i].getClass().getSimpleName()+".");
			} else {
				System.out.println(""+i+"th element is null.");
			}
		}
		try {
			testType((Boolean[]) arr[0]);
			testType((Boolean[]) arr[1]);
		} catch (ClassCastException e) {
			System.out.println("Nay :(! Type of array can't be cast Boolean[]!");
		}
	}
	private static void testType(Boolean[] arr) {
		System.out.println("Yay :)! Type of array can be cast to Boolean[]!");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		simpleExample();
		System.out.println("****************************");
		complexExample();
	}

}
