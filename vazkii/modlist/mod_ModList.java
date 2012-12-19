package vazkii.modlist;

import vazkii.modlist.fetcher.ModListFetcher;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "vz_modlist", name = "Mod List Mod", version = "by Vazkii. Maintained by ZeroLevels")
public class mod_ModList {

	public static final String MOD_LIST_URL = "http://www.minecraftforum.net/topic/1434593-/";

	@Init
	public void init(FMLInitializationEvent event) {
		ModListFetcher.readAllMods(MOD_LIST_URL);
		KeyBindingRegistry.registerKeyBinding(new Keybind());
		System.out.println("[ModList] Found " + ModListFetcher.getAllMods().size() + " mods in the thread. @ " + MOD_LIST_URL);
	}

}
