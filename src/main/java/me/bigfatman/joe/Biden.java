package me.bigfatman.joe;


import me.bigfatman.joe.commands.api.CommandManager;
import me.bigfatman.joe.data.PlayerDataManager;
import me.bigfatman.joe.events.impl.PlayerListener;
import me.bigfatman.joe.events.impl.PunishmentListener;
import me.bigfatman.joe.packet.api.PacketInjector;
import me.bigfatman.joe.utils.ConfigUtil;
import me.bigfatman.joe.utils.gui.MenuFunctionListener;
import me.bigfatman.joe.violation.ViolationManager;

public enum Biden {

    INSTANCE;

    public BidenPlugin bidenPlugin;

    public ViolationManager violationManager;
    public PlayerDataManager dataManager;

    public PacketInjector packetInjector;

    public ConfigUtil bidenConfig;

    public void onStart(BidenPlugin bidenPlugin) {
        this.bidenPlugin = bidenPlugin;

        this.dataManager = new PlayerDataManager();
        this.violationManager = new ViolationManager();

        this.packetInjector = new PacketInjector();

        this.bidenPlugin.getServer().getPluginManager().registerEvents(new PlayerListener(this), bidenPlugin);
        this.bidenPlugin.getServer().getPluginManager().registerEvents(new PunishmentListener(), bidenPlugin);
        this.bidenPlugin.getServer().getPluginManager().registerEvents(new MenuFunctionListener(), bidenPlugin);

        this.bidenPlugin.getCommand("biden").setExecutor(new CommandManager());

        this.bidenConfig = new ConfigUtil(bidenPlugin, "config.yml");
        this.bidenConfig.saveDefaultConfig();
    }

    public void onStop() {
        this.packetInjector.packetThread.shutdown();
    }


}
