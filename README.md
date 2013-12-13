JArrayLiterals
==============

Shortcut methods for creating arrays using array literals.   
No more `new int[][] { {1,2}, {3,4} }` notations which have more boilerplate code than actual data!   
Why not do `$($(1,2), $(3,4))` and make your life easier?   

Example
-------
Complex example showing off most features:
```java
		Object obj = $($(
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
```
prints
```

[
 [
  [1.0F , 2.0F]
  , 
  [3.0F , 4.0F]
  , 
  [5.0F , 6.0F]
  , 
  [7.0F , 8.0F]
 ]
 , 
 [
  [100L]
  , 
  [200L]
 ]
 , 
 [a , B]
 , 
 [
  [
   [
    [
     [
      [2.0D]
     ]
    ]
   ]
  ]
 ]
 , 
 [
  [
   [true]
   , 
   [true , false]
   , 
   [false , false , false]
  ]
 ]
 , 
 [
  [a , c]
 ]
 , 
 [
  [2S , 3S , 4S]
  , 
  [1S , 5S , 6S , 7S , 8S , 9S , 0S]
 ]
 , 
 [
  [
   []
   , 
   [null]
  ]
 ]
 , 
 [
  [2B , 5B , 6B , 7B , 8B , 9B]
  , 
  [101B , -57B]
  , 
  []
 ]
 , 
 [
  [
   [
    [
     [
      [
       []
      ]
     ]
    ]
   ]
  ]
 ]
 , 
 [
  [
   [
    [
     [
      [
       []
      ]
     ]
    ]
   ]
  ]
 ]
]

```
