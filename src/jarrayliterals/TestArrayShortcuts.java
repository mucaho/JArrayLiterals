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
		Object flat = $( $S(1,2,3,4), $B(2,3), $(true, false), $('c'), $($null), $, $null);
		Object[][] flat2 = new Object[][]{
				{(short) 1, (short) 2, (short) 3, (short) 4},
				{(byte) 2, (byte) 3},
				{true, false},
				{'c'},
				{null},
				{},
				null
		};
		System.out.println(Arrays.deepToString((Object[]) flat));
		System.out.println(Arrays.deepToString(flat2));
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
