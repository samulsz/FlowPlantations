package me.samulsz.api;


import me.samulsz.cache.UserCache;
import me.samulsz.connections.MysqlConnection;
import me.samulsz.managers.FileManager;
import me.samulsz.managers.ToolManager;
import me.samulsz.objects.LoadObjects;
import me.samulsz.utils.NbtUtils;
import org.bukkit.plugin.Plugin;

public class PlantationsAPI {
    private final Plugin plugin;
    private final FileManager fileManager;
    private final LoadObjects loadObjects;
    private final UserCache userCache;
    private final MysqlConnection mysqlConnection;
    private final ToolManager toolManager;
    private final NbtUtils nbtUtils;


    public PlantationsAPI(Plugin plugin) {
        this.plugin = plugin;
        this.fileManager = new FileManager(plugin);
        this.loadObjects = new LoadObjects(fileManager);
        this.mysqlConnection = new MysqlConnection(plugin);
        this.userCache = new UserCache(mysqlConnection);
        this.toolManager = new ToolManager(this);
        this.nbtUtils = new NbtUtils();
        nbtUtils.load();
    }

    public LoadObjects getLoadObjects() {
        return loadObjects;
    }
    public FileManager getFileManager() {
        return fileManager;
    }
    public UserCache getUserCache() {
        return userCache;
    }
    public  MysqlConnection getMysqlConnection() {
        return mysqlConnection;
    }
    public ToolManager getToolManager() {
        return toolManager;
    }

    public NbtUtils getNbtUtils() {
        return nbtUtils;
    }
}
