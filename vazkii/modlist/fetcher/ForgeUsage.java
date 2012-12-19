package vazkii.modlist.fetcher;

import vazkii.modlist.ColorCode;

public enum ForgeUsage
{

	REQUIRED("Forge Required", "Forge Required", ColorCode.BRIGHT_GREEN), NOT_COMPATIBLE("Not Forge Compatible", "Forge Incompatible", ColorCode.RED), COMPATIBLE("Forge Compatible", "Forge Compatible", ColorCode.BRIGHT_GREEN), UNKNOWN("???", "Unknown Forge Compatibility", ColorCode.RED);

	private ForgeUsage(String find, String displayName, ColorCode colorCode) {
		this.find = find;
		this.displayName = displayName;
		this.colorCode = colorCode;
	}

	private String find, displayName;
	private ColorCode colorCode;

	public String getStrToFind() {
		return find;
	}

	public String getDisplayName() {
		return displayName;
	}

	public ColorCode getColorCode() {
		return colorCode;
	}
}
