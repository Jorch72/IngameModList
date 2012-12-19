package vazkii.modlist.fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public final class ModListFetcher {

	static BufferedReader bufferedReader;
	private static ArrayList<FetchedMod> allFetchedMods = new ArrayList();

	private ModListFetcher() {
	}

	public static ArrayList<FetchedMod> getAllMods() {
		return allFetchedMods;
	}

	public static void readAllMods(String url) {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String line = null;
			boolean isReadingMods = false;
			boolean hasStoppedReadingMods = false;

			String lastLine = "";
			String lineBeforeLast = "";
			String twoLinesBefore = "";

			while ((line = bufferedReader.readLine()) != null) {
				if (isReadingMods) {
					if (line.equals("</div></div>")) hasStoppedReadingMods = true;

					if (!hasStoppedReadingMods) {
						FetchedMod mod = FetchedMod.parseModFromHTMLLine(line);
						allFetchedMods.add(mod);
					}
				}

				if (line.equals("	<div class='bbc_spoiler_wrapper'><div class='bbc_spoiler_content' style=" + '"' + "display:none;" + '"' + "><br />") && lastLine.equals("	<span class='spoiler_title'>Spoiler: </span> <input type='button' class='bbc_spoiler_show' value='Show' />") && lineBeforeLast.equals("<div class='bbc_spoiler'>") && twoLinesBefore.equals("Complete List<br />")) isReadingMods = true;

				twoLinesBefore = lineBeforeLast;
				lineBeforeLast = lastLine;
				lastLine = line;

				if (hasStoppedReadingMods) break;
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
