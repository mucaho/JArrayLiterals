package jarrayliterals;

import static org.junit.Assert.*;
import static jarrayliterals.ArrayShortcuts.*;

import java.lang.reflect.Array;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ArrayShortcutsTest {

	public Object parametersForTestShortcuts() {
		Object out = $(
		
		$(
			new Object(), "Object", Object.class
		), $(
			$, "Object[]", Object[].class
		), $(
			$(), "Object[]", Object[].class
		), $(
			$($), "Object[][]", Object[][].class
		), $(
			$($()), "Object[][]", Object[][].class
		), $(
			$($,$,$), "Object[][]", $(Object[].class, Object[].class, Object[].class)
		), $(
			$($,$($),$($($))), "Object[]", $(Object[].class,Object[][].class,Object[][][].class)
		), $(
			$($($($($($($($))))))), "Object[][][][][][][][]", Object[][][][][][][][].class
		), 
		
		
		$(
			$null, "null", Void.class
		), $(
			$($null), "Void[]", $(Void.class)
		), $(
			$($null, $null), "Void[]", $(Void.class, Void.class)
		), $(
			$($($null)), "Void[][]", $($(Void.class))
		), $(
			$($($null, $null)), "Void[][]", $($(Void.class, Void.class))
		), $(
			$($($null), $($null, $null)), "Void[][]", $($(Void.class), $(Void.class, Void.class))
		), $(
			$($null, $($null), $($($null))), "Object[]", 
			$(Void.class, $(Void.class), $($(Void.class)))
		), 
		
		
		$(
			true, "Boolean", Boolean.class
		), $(
			$b, "Boolean[]", Boolean[].class
		), $(
			$(true), "Boolean[]", $(Boolean.class)
		), $(
			$(true, false), "Boolean[]", $(Boolean.class, Boolean.class)
		), $(
			$($b), "Boolean[][]", Boolean[][].class
		), $(
			$($(true)), "Boolean[][]", $($(Boolean.class))
		), $( 
			$($(true, false)), "Boolean[][]", $($(Boolean.class, Boolean.class))
		), $(
			$($b, $(false), $(true, false)), "Boolean[][]", 
			$(Boolean[].class, $(Boolean.class), $(Boolean.class, Boolean.class))
		), $(
			$($($b), $($(false), $(true, false))), "Boolean[][][]", 
			$(Boolean[][].class, $($(Boolean.class), $(Boolean.class, Boolean.class)))
		), $(
			$(true, $(false), $($b), $($(true, false))), "Object[]", 
			$(Boolean.class, $(Boolean.class), Boolean[][].class, $($(Boolean.class, Boolean.class)))
		)
			//TODO add tests for $B/$S, String, custom class instances
		);
		
		return out;
	}

	
	private static final void testClass(Object object, Object expectedClass) {
		int arrLength = 0;
		if (expectedClass.getClass().isArray())
			arrLength = Array.getLength(expectedClass);
		
		if (arrLength != 0) {
			assertEquals("Element count mismatch.", arrLength, Array.getLength(object));
			for (int i=0; i<arrLength; i++)
				testClass(Array.get(object, i), Array.get(expectedClass, i));
		} else {
			assertEquals("Class mismatch.", 
					expectedClass, object!=null ? object.getClass() : Void.class);
		}
	}
	@Test
	@Parameters
	public final void testShortcuts(Object test, String expectedName, Object expectedClasses) {
		if (test == null) {
			assertEquals("Is not null!", expectedName, "null");
			assertEquals("Is not null!", expectedClasses, Void.class);
			return;
		}
		
		assertEquals("Class name mismatch.", expectedName, test.getClass().getSimpleName());
		testClass(test, expectedClasses);
	}

}
