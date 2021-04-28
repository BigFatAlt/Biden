package me.bigfatman.joe;


import me.bigfatman.joe.commands.api.CommandManager;
import me.bigfatman.joe.data.PlayerDataManager;
import me.bigfatman.joe.events.impl.PlayerListener;
import me.bigfatman.joe.events.impl.PunishmentListener;
import me.bigfatman.joe.mod.ModManager;
import me.bigfatman.joe.mod.impl.MessageListener;
import me.bigfatman.joe.mod.impl.ModListener;
import me.bigfatman.joe.packet.api.PacketInjector;
import me.bigfatman.joe.utils.ConfigUtil;
import me.bigfatman.joe.utils.gui.MenuFunctionListener;
import me.bigfatman.joe.violation.ViolationManager;

public enum Biden {

    INSTANCE;

    public BidenPlugin bidenPlugin;

    public ModManager modManager;
    public ViolationManager violationManager;
    public PlayerDataManager dataManager;

    public PacketInjector packetInjector;

    public ConfigUtil bidenConfig;

    public void onStart(BidenPlugin bidenPlugin) {
        this.bidenPlugin = bidenPlugin;

        this.dataManager = new PlayerDataManager();
        this.violationManager = new ViolationManager();
        this.modManager = new ModManager(this);

        this.packetInjector = new PacketInjector();

        new MessageListener(modManager);

        this.bidenPlugin.getServer().getPluginManager().registerEvents(new PlayerListener(this), bidenPlugin);
        this.bidenPlugin.getServer().getPluginManager().registerEvents(new PunishmentListener(), bidenPlugin);
        this.bidenPlugin.getServer().getPluginManager().registerEvents(new ModListener(modManager), bidenPlugin);
        this.bidenPlugin.getServer().getPluginManager().registerEvents(new MenuFunctionListener(), bidenPlugin);

        this.bidenPlugin.getCommand("biden").setExecutor(new CommandManager());

        this.bidenConfig = new ConfigUtil(bidenPlugin, "config.yml");
        this.bidenConfig.saveDefaultConfig();
    }

    public void onStop() {
        this.packetInjector.packetThread.shutdown();
    }


}
