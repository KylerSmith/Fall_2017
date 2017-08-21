

public class MovieDescription {
	
/**
* Class properties.
*/
	private String title;
	private String rating;
	private String released;
	private String runtime;
	private String plot;
	private String filename;
	private String[] genre;
	private String[] actors;

/**
* Constructors.
*/

	// TODO: Create constructors.

/**
* Getters.
*/
	
	public String getTitle()    { return title; }
	public String getRating()   { return rating; }
	public String getReleased() { return released; }
	public String getRuntime()  { return runtime; }
	public String getPlot()     { return plot; }
	public String getFilename() { return filename; }
	public String[] getGenre() { return genre; }
	public String[] getActors() { return actors; }

/**
* Setters.
*/

	public void setTitle(String title)       { this.title = title; }
	public void setRating(String rating) 	 { this.rating = rating; }
	public void setReleased(String released) { this.released = released; }
	public void setRuntime(String runtime) 	 { this.runtime = runtime; }
	public void setPlot(String plot) 	 { this.plot = plot; }
	public void setFilename(String filename) {this.filename = filename; }
	public void setGenre(String[] genre) 	 { this.genre = genre; }
	public void setActors(String[] actors) 	 { this.actors = actors; }

/**
* Main entry point for the program.
*/

	public static void main(String[] args) {
		MovieDescription md = new MovieDescription();
	}


}







