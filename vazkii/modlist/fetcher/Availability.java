package vazkii.modlist.fetcher;

public enum Availability
{

	CLIENTSIDE("Clientside Mod", "Clientside Mod"), UNIVERSAL("Universal", "Universal Mod"), SSP_SMP_LAN("SSP SMP LAN", "Single/Multiplayer/LAN"), SSP_SMP("SSP SMP", "Single/Multiplayer"), SSP_LAN("SSP LAN", "LAN Compatible"), SMP_LAN("SMP LAN", "Multiplayer/LAN"), SSP("SSP", "Singleplayer Exclusive"), SMP("SMP", "Multiplayer Exclusive"), UNKNOWN("???", "Unknown");

	private Availability(String find, String displayName) {
		this.find = find;
		this.displayName = displayName;
	}

	private String find, displayName;

	public String getStrToFind() {
		return find;
	}

	public String getDisplayName() {
		return displayName;
	}
}
