JArrayLiterals
==============

Shortcut methods for creating arrays using array literals.   
No more `new int[][] { {1,2}, {3,4} }` notations which have more boilerplate code than actual data!   
Why not do `$($(1,2), $(3,4))` and make your life easier?   

Why bother? If you write a lot of parameterized unit tests and you hardcode the test parameters, then JArrayLiterals 
could help you write parameter data in a more concise way.
If your interested in _"parameterised tests that don't suck"_, check out [JUnitParams](http://code.google.com/p/junitparams/)! The shortcut method of that project actually inspired me to 
make this project.

Check out the example below for identifiying benefits in using this project.


Example
-------
Example showing the benefits of JArrayLiterals:
```java
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
* The first array actually contains elements of the __expected type__. The second does not. This does not concern JUnit or JUnitParams as they convert the test parameters before passing them to the tested method. However, it is a nice feature if you need to use the array directly.
* Take a look at the insertion of the _2th_ element. As the __element itself is an array__, the dimensions do not match any more. You have to create a temporary variable or call a dummy method that returns the _correct_ dimension object. JUnitParameters has a method _$_ which does this for you (similar to JArrayLiteral's `$`).
* Additionally `$ and co` allow you to create empty arrays of an arbitrary dimension (like `Object[0][0][0]`). JUnitParam's `$` method would currently return `Object[0]` instead.
* Take a look at the insertion of the  _3th & 4th_ element. Traditionally you would have to __cast all byte and short__ elements appropriately. JArrayLiterals has an utility method which does that for you.

Misc
----
The class which contains the shortcut methods and the complete example can be found inside the __src__ folder.
