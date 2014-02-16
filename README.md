JArrayLiterals
==============

Shortcut methods for creating arrays using array literals.   
Instead of `new int[][] { {1,2}, {3,4} }` do `$($(1,2), $(3,4))`.

JArrayLiterals could help you write parameter unit tests data in a more concise way.   
If your interested in _"parameterised tests that don't suck"_, check out [JUnitParams](http://code.google.com/p/junitparams/)! The shortcut method of that project actually inspired me to make this project.


Example
-------
You can find the complete example in [ArrayShortcutsExample](example/jarrayliterals/ArrayShortcutsExample.java):
```java
	public static void simpleExample() {
		/**
		 * JArrayLiterals approach
		 */
		Object[] flat = (Object[])
			$( $b, $(true, false), $($), $S(1,2,3,4), $B(2,3), $('c'), $($null), $, $null );
		testTypes(flat);

		System.out.println();
		
		/**
		 * standard approach
		 */
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
			if (arr[i] != null)
				System.out.println(""+i+"th element is typeof "+arr[i].getClass().getSimpleName()+".");
			else
				System.out.println(""+i+"th element is null.");
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
```

Gives the output:
```
// JArrayLiterals apporach
[[], [true, false], [[]], [1, 2, 3, 4], [2, 3], [c], [null], [], null]
0th element is typeof Boolean[].
1th element is typeof Boolean[].
2th element is typeof Object[][].
3th element is typeof Short[].
4th element is typeof Byte[].
5th element is typeof Character[].
6th element is typeof Void[].
7th element is typeof Object[].
8th element is null.
Yay :)! Type of array can be cast to Boolean[]!
Yay :)! Type of array can be cast to Boolean[]!

// standard apporach
[[], [true, false], [[]], [1, 2, 3, 4], [2, 3], [c], [null], [], null]
0th element is typeof Object[].
1th element is typeof Object[].
2th element is typeof Object[][].
3th element is typeof Object[].
4th element is typeof Object[].
5th element is typeof Object[].
6th element is typeof Object[].
7th element is typeof Object[].
8th element is null.
Nay :(! Type of array can't be cast Boolean[]!
```
Benefits:
* __readable and flexible__
* elements are of the __expected type__
* create __arrays with correct dimension dynamically__
* utility methods that __cast all bytes or shorts__ 

Caveats:
* User arrays (that are contained in a variable or generated as a return value of a function) __need to be cast to Object__ before passing to the `$` method, if that array should be interpreted as a single argument, rather than a list of arguments.
* Creating arrays of mixed super- and subclass types will __not be of the type of the least common ancestor class__. They will be `Object[]` arrays instead. (TODO)

JavaDoc
-------
You can find the __docs__ on the [JArrayLiteral's Github Pages](http://mucaho.github.io/JArrayLiterals/).

Source
------
You can find the source in [ArrayShortcuts](src/jarrayliterals/ArrayShortcuts.java). To __use the shortcut methods__ you do a __static import__ of the class.

Tests
-----
You can find the tests in [ArrayShortcutsTest](test/jarrayliterals/ArrayShortcutsTest.java). There you can see JArrayLiterals used for __testing itself__ with the help of [JUnitParams](http://code.google.com/p/junitparams/)!

Misc
----
__Open issues__ and/or __open pull requests__. Any suggestions are welcome!

Copyright 2013 mucaho provided by Apache License 2.0 (see LICENSE and NOTICE).


