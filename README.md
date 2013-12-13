JArrayLiterals
==============

Shortcut methods for creating arrays using array literals.   
No more `new int[][] { {1,2}, {3,4} }` notations which have more boilerplate code than actual data!   
Why not do `$($(1,2), $(3,4))` and make your life easier?   

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
