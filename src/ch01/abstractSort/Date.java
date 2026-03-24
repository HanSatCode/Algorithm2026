package ch01.abstractSort;

public class Date implements Comparable<Date> {
	private final int month;
	private final int day;
	private final int year;
	public Date(int month, int day, int year) {
		this.month = month; this.day = day; this.year = year;
	}
	
	@Override
	public int compareTo(Date that) {
		if (this.year < that.year) return -1;
		if (this.year > that.year) return 1;
		if (this.month < that.month) return -1;
		if (this.month > that.month) return 1;
		if (this.day < that.day) return -1;
		if (this.day > that.day) return 1;
		return 0;
	}
}
