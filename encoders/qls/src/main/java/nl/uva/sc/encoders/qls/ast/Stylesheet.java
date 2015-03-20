package nl.uva.sc.encoders.qls.ast;

import java.util.List;

public class Stylesheet extends AstNodeWithLocation {

	private final String name;

	private final List<Page> pages;

	public Stylesheet(TextLocation textLocation, String name, List<Page> pages) {
		super(textLocation);
		this.name = name;
		this.pages = pages;
	}

	public String getName() {
		return name;
	}

	public List<Page> getPages() {
		return pages;
	}

}