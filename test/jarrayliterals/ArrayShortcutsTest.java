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


	private static final void testEmptyArray(Object object, Class<?> arrayClass) {
		testClass(object, arrayClass);
		
		int objLength = Array.getLength(object);
		
		Class<?> componentType = arrayClass.getComponentType();
		if (!componentType.isArray()) {
			assertEquals("Array should have zero elements.", 0, objLength);
		} else {
			assertEquals("Array should have one element", 1, objLength);
			
			testEmptyArray(Array.get(object, 0), componentType);
		}
	}
	
	private static final void testClass(Object object, Class<?> expectedClass) {
		assertEquals("Class mismatch.", 
				expectedClass, object!=null ? object.getClass() : Void.class);
	}
	
	private static final void testClasses(Object object, Object expectedClasses) {
		int objLength = 0;
		if (object!=null && object.getClass().isArray()) {
			objLength = Array.getLength(object);
		}
		int expectedLength = 0;
		if (expectedClasses.getClass().isArray()) {
			expectedLength = Array.getLength(expectedClasses);
		}
		
		
		if (expectedLength != 0) { //expectedLength!=0
			
			assertEquals("Element count mismatch.", expectedLength, objLength);
			for (int i=0; i<expectedLength; i++)
				testClasses(Array.get(object, i), Array.get(expectedClasses, i));
		
		} else if (objLength != 0) { // expectedLength==0, objLength!=0
			
			testEmptyArray(object, (Class<?>) expectedClasses);
			
		} else { // expectedLength==0, objLength==0
			
			testClass(object, (Class<?>) expectedClasses);
		}
	}
	
	private static final void testName(Object test, String expectedName, Object expectedClass) {
		if (test == null) {
			assertEquals("Is not null!", expectedName, "null");
			assertEquals("Is not Void!", expectedClass, Void.class);
		} else {
			assertEquals("Class name mismatch.", expectedName, test.getClass().getSimpleName());
		}
	}


	@Test
	@Parameters
	public final void testShortcuts(Object test, String expectedName, Object expectedClasses) {
		testName(test, expectedName, expectedClasses);
		testClasses(test, expectedClasses);
	}

}
