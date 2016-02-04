import java.util.ArrayList;
public class YearAfterFilter implements Filter {
	private int myYear;

	public YearAfterFilter(int year) {
		myYear = year;
	}
	/*
		Pass all candidate that is no earlier than myYear
	*/
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}

}
