package vazkii.modlist.fetcher;

public class FetchedMod {

	public final String name;
	public final String author;
	public final String url;
	public final Availability availability;
	public final ForgeUsage forgeUsage;

	public static final char dummyTestChar = '>';

	private FetchedMod(String name, String author, String url, Availability availability, ForgeUsage forgeUsage) {
		this.name = name;
		this.author = author;
		this.url = url;
		this.availability = availability;
		this.forgeUsage = forgeUsage;
	}

	public static FetchedMod parseModFromHTMLLine(String html) {
		char[] htmlArray = html.toCharArray();

		int index = 0;
		int urlStartIndex = -1;
		int urlEndIndex = -1;

		int nameStartIndex = -1;
		int nameEndIndex = -1;

		int authorStartIndex = -1;
		int authorEndIndex = -1;

		for (char c : htmlArray) {
			if (c == 0x27) if (urlStartIndex == -1) urlStartIndex = index;
			else if (urlEndIndex == -1) urlEndIndex = index;
			if (c == 0x3E && nameStartIndex == -1) nameStartIndex = index;
			if (c == 0x3C && nameEndIndex == -1 && urlEndIndex != -1) nameEndIndex = index;
			if (c == 0x79 && nameEndIndex != -1 && html.charAt(index - 1) == 0x62 && authorStartIndex == -1) authorStartIndex = index + 1;
			if (c == 0x3C && authorStartIndex != -1 && authorEndIndex == -1) authorEndIndex = index - 1;

			++index;
		}

		String foundName = "N/A";
		String foundAuthor = "N/A";

		try {
			foundName = html.substring(nameStartIndex + 1, nameEndIndex).replaceAll("&#39;", "'");
		} catch (StringIndexOutOfBoundsException e) {
		} // Because formatting isn't perfect, this may happen
		try {
			foundAuthor = html.substring(authorStartIndex + 1, authorEndIndex).replaceAll("&#39;", "'");
		} catch (StringIndexOutOfBoundsException e) {
		}

		String foundURL = "https://dl.dropbox.com/u/34938401/Mods/ModNotFound.html?mn=" + foundName.replaceAll(" ", "%20");
		try {
			foundURL = html.substring(urlStartIndex + 1, urlEndIndex);
		} catch (StringIndexOutOfBoundsException e) {
		}

		Availability foundAvailability = null;
		for (Availability av : Availability.class.getEnumConstants())
			if (html.contains(av.getStrToFind())) {
				foundAvailability = av;
				break;
			}
		if (foundAvailability == null) foundAvailability = Availability.UNKNOWN;

		ForgeUsage foundForgeUsage = null;
		for (ForgeUsage fu : ForgeUsage.class.getEnumConstants())
			if (html.contains(fu.getStrToFind())) {
				foundForgeUsage = fu;
				break;
			}
		if (foundForgeUsage == null) foundForgeUsage = ForgeUsage.UNKNOWN;

		return new FetchedMod(foundName, foundAuthor, foundURL, foundAvailability, foundForgeUsage);
	}

	@Override
	public String toString() {
		return name + " by " + author + ", " + availability.getDisplayName() + ", " + forgeUsage.getDisplayName() + " @ " + url;
	}
}
