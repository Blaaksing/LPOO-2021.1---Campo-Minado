package campominado;

import java.util.Comparator;

public class OrdenarRanking implements Comparator<Ranking> {

	@Override
	public int compare(Ranking o1, Ranking o2) {
		
		return o1.getTempo().compareTo(o2.getTempo());
	}

}
