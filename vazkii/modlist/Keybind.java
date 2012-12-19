package vazkii.modlist;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;

import net.minecraft.src.KeyBinding;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class Keybind extends KeyHandler {

	static KeyBinding key = new KeyBinding("List of Mods (Minecraft Forums)", Keyboard.KEY_F12);

	public Keybind() {
		super(new KeyBinding[] { key }, new boolean[] { false });
	}

	@Override
	public String getLabel() {
		return "Vz_Modlist";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.currentScreen == null || !(mc.currentScreen instanceof GuiAllModsList)) mc.displayGuiScreen(new GuiAllModsList());
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}

}
