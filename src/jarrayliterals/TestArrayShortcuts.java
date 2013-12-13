package jarrayliterals;

import static jarrayliterals.ArrayShortcuts.*;

public class TestArrayShortcuts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object[] obj = (Object[]) 
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

}
