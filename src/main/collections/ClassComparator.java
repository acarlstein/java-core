package collections;

import java.util.Comparator;

public class ClassComparator implements Comparator<ClassComparable>
{
	@Override public int compare(ClassComparable left, ClassComparable right){
		return left.value - right.value;
	}
}
