package vazkii.modlist;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import vazkii.modlist.fetcher.FetchedMod;
import vazkii.modlist.fetcher.ModListFetcher;

import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;
import net.minecraft.src.MathHelper;

public class GuiAllModsList extends GuiScreen {

	public ArrayList<FetchedMod> modsToLookAt;

	public int selected = -1;
	private GuiAllModsListContainer container;
	private GuiTextField textField;

	@Override
	public void initGui() {
		modsToLookAt = ModListFetcher.getAllMods();
		container = new GuiAllModsListContainer(this);
		textField = new GuiTextField(fontRenderer, width - 210, 6, 180, 16);
		textField.setFocused(true);
		textField.setCanLoseFocus(false);
		textField.setMaxStringLength(20);
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);
		textField.textboxKeyTyped(par1, par2);
		if (MathHelper.stringNullOrLengthZero(textField.getText())) modsToLookAt = ModListFetcher.getAllMods();
		else {
			ArrayList<FetchedMod> mods = new ArrayList();
			for (FetchedMod mod : ModListFetcher.getAllMods())
				if ((mod.name + " by " + mod.author).toLowerCase().contains(textField.getText().toLowerCase())) mods.add(mod);
					modsToLookAt = mods;
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		container.drawScreen(par1, par2, par3);
		if (ModListFetcher.getAllMods().isEmpty()) {
			GL11.glPushMatrix();
			GL11.glScalef(2F, 2F, 2F);
			drawCenteredString(fontRenderer, ColorCode.RED + "No Mods Found...", width / 4, 40, 0xFFFFFF);
			GL11.glPopMatrix();
			drawCenteredString(fontRenderer, ColorCode.YELLOW + "Maybe minecraft forum is down...?", width / 2, 104, 0xFFFFFF);
		} else if (modsToLookAt.isEmpty()) {
			GL11.glPushMatrix();
			GL11.glScalef(2F, 2F, 2F);
			drawCenteredString(fontRenderer, ColorCode.RED + "No Mods Found...", width / 4, 40, 0xFFFFFF);
			GL11.glPopMatrix();
			drawCenteredString(fontRenderer, ColorCode.YELLOW + "Maybe you should refine your search...", width / 2, 104, 0xFFFFFF);
		}
		boolean drawText = !MathHelper.stringNullOrLengthZero(textField.getText());

		drawCenteredString(fontRenderer, "List of Minecraft Mods (" + modsToLookAt.size() + " Mods Found)", width / 2 - (drawText ? 60 : 0), 8, 16777215);
		drawCenteredString(fontRenderer, ColorCode.GREY + "Mod by Vazkii. List maintained by catqueen5 and ZeroLevels", width / 2 - (drawText ? 60 : 0), 20, 16777215);
		boolean error = !Desktop.isDesktopSupported();
		if (error) drawCenteredString(fontRenderer, ColorCode.RED + "ERROR: Desktop Class not supported. Will not be able to open webpages.", width / 2, height - 13, 16777215);
		drawCenteredString(fontRenderer, ColorCode.INDIGO + "Double click a mod to open it in your browser. Type something to search for that mod.", width / 2, height - (error ? 24 : 19), 16777215);
		if (drawText) textField.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}

	public void select(int i, boolean doubleClick) {
		selected = i;
		if (doubleClick) {
			FetchedMod mod = modsToLookAt.get(i);
			if (Desktop.isDesktopSupported()) try {
				Desktop.getDesktop().browse(new URI(mod.url));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
