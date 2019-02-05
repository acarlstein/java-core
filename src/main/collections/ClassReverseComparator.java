package collections;

import java.util.Comparator;

public class ClassReverseComparator implements Comparator<ClassComparable>
{
	@Override public int compare(ClassComparable left, ClassComparable right){
		return right.value - left.value;
	}
}
