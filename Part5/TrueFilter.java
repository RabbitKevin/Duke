/*
    Filter that has no filter function, pass all candidate
*/
public class TrueFilter implements Filter {
	@Override
	public boolean satisfies(String id) {
		return true;
	}

}
