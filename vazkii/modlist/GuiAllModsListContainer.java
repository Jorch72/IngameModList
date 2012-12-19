package vazkii.modlist;

import vazkii.modlist.fetcher.FetchedMod;
import vazkii.modlist.fetcher.ForgeUsage;
import net.minecraft.client.Minecraft;

import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiSlot;
import net.minecraft.src.Tessellator;

public class GuiAllModsListContainer extends GuiSlot {

	GuiAllModsList parent;
	FontRenderer font;

	public GuiAllModsListContainer(GuiAllModsList parent) {
		super(Minecraft.getMinecraft(), parent.width, parent.height, 32, parent.height - 32, 36);
		this.parent = parent;
		font = Minecraft.getMinecraft().fontRenderer;
	}

	@Override
	protected int getSize() {
		return parent.modsToLookAt.size();
	}

	@Override
	protected void elementClicked(int var1, boolean var2) {
		parent.select(var1, var2);
	}

	@Override
	protected boolean isSelected(int var1) {
		return parent.selected == var1;
	}

	@Override
	protected void drawBackground() {
		parent.drawBackground(0);
	}

	@Override
	protected int getContentHeight() {
		return getSize() * 36;
	}

	@Override
	protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5) {
		FetchedMod mod = parent.modsToLookAt.get(var1);
		font.drawStringWithShadow(mod.name + " by " + mod.author, var2, var3 + 1, 0xFFFFFF);
		font.drawString(ColorCode.GREY + mod.availability.getDisplayName(), var2, var3 + 12, 0xFFFFFF);
		String index = ColorCode.GREY + "" + (var1 + 1) + " |";
		font.drawString(index, var2 - 6 - font.getStringWidth(index), var3 + 12, 0xFFFFFF);
		ForgeUsage usage = mod.forgeUsage;
		font.drawString(usage.getColorCode() + usage.getDisplayName(), var2, var3 + 23, 0xFFFFFF);
	}

}
