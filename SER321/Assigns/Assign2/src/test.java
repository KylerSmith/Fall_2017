package movie;

public class test {


	public static void main(String args[]) {
		
		MovieLibrary ml = new MovieLibrary();

		MovieDescription puppy = new MovieDescription("Minions Puppy","NR","10 Dec 2013",
             	"4:16 min","Dave seeing many owners walk their dogs wants a puppy of his own. "+
             	"He finds a mini-UFO who becomes his pal. This short film released with Despic"+
             	"able Me 2 chronicles how Dave helps the UFO return home.","MinionsPuppy.mp4",
             	"Dave","Animation");

		ml.add(puppy);

		System.out.println(ml.get("Minions Puppy").getReleased());

	}



}
