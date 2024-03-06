# Interpolation

Different Interpolation Methods implemented and visualized in Java. \
Generally Interpolation is a method to find a (steady) function to a discrete set of values. \
For this implementation the set of values contains of so-called grid points. 
Each of these points represents an x-value with its corresponding y-value. \
The grid points are all equidistant, meaning, that they all have the same width between each other.

## Interpolation Methods

The Interpolation Methods generally follow the following structure. 
Some of the methods have been adapted due to performance reasons.
- $a$:double -> the left most grid point
- $b$:double -> the right most grid point
- $y$:double[] -> the y-value of all grid points
- $n$:int -> the number of intervals (between grid points)
- $h$:double -> distance between two grid points

### Newton Interpolation 
