package string.formatting;

import java.util.Arrays;
import java.util.StringJoiner;

public class FormatSpecifier
{

	/**
	 * Format: % [conversion]
	 *
	 * For '%d":
	 *  '%' is the format specifier start
	 *  'd' is the conversion to integer
	 *
	 * For '%s':
	 *  '%' is the format specifier start
	 *  's' is the conversion to string
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public String getKeyIntegerValue(String key, int value){
		return String.format("'%s' : %d", key, value);
	}

	/**
	 * Format: % [precision] [conversion]
	 *
	 *  'precision': Decimals to display
	 *
	 * For '%.2f':
	 *  '%' is the format specifier start
	 *  '.2' is the precision. In this case, two decimals precision
	 *  'f' is the conversion to float
	 *
	 * @param values
	 * @return value1 + value2 + ... + valueN = mean
	 * Where the average value will be a up to two decimals
	 */
	public String getMedianAsString(int[] values){
		StringJoiner sj = new StringJoiner(" + ");
		for (int value: values){
			sj.add(String.valueOf(value));
		}

		return String.format("%s = %.2f", sj.toString(), getMedian(values));
	}

	private double getMedian(int[] values){
		if (values.length == 1){
			return values[0];
		}

		Arrays.sort(values);
		int halfLength = values.length / 2;
		if (values.length % 2 == 0)
		{
			return ((double) values[halfLength] + (double) values[halfLength - 1]) / 2;
		}
		return (double) values[halfLength];
	}


}
