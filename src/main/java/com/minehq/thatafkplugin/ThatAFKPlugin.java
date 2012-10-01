package com.minehq.thatafkplugin;

import com.minehq.thatafkplugin.util.Log;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class ThatAFKPlugin extends JavaPlugin {
    private static ThatAFKPlugin plugin;
    // Updater updater = new Updater(this, "slug", this.getFile(), Updater.UpdateType.DEFAULT, false);

    @Override
    public void onEnable() {
        plugin = this;

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            Log.severe("I wasn't able to submit plugin statistics!");
        }

        Log.info("Successfully enabled version " + this.getDescription().getVersion() + "!");
    }

    @Override
    public void onDisable() {
        Log.info("Successfully disabled version " + this.getDescription().getVersion() + "!");
    }

    public void printStackTrace(Throwable t) {
        Log.severe("");
        Log.severe("Internal error!");
        Log.severe("If this bug hasn't been reported please open a ticket at " + getDescription().getWebsite());
        Log.severe("Include the following into your bug report:");
        Log.severe("          ======= SNIP HERE =======");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        t.printStackTrace(pw);
        for (String l : sw.toString().replace("\r", "").split("\n")) {
            Log.severe(l);
        }

        pw.close();

        try {
            sw.close();
        } catch (IOException ignored) {
        }

        Log.severe("          ======= SNIP HERE =======");
        Log.severe("");
    }

    public static ThatAFKPlugin getPlugin() {
        return plugin;
    }

    private void loadCommands() {

    }

    private void loadEvents() {

    }

    public void getDependencies() {
        File libFolder = new File("lib");

        if (!libFolder.exists()) {
            if (!libFolder.mkdirs()) {
                Log.severe("I couldn't create the JailPlusPlus libraries folder!");

                return;
            }
        }

        File h2 = new File("lib/h2-1.3.168.jar");

        if (!h2.exists()) {
            Log.info("I need to download dependencies for Jail++! This should only take a moment...");

            try {
                URL h2Url = new URL("http://search.maven.org/remotecontent?filepath=com/h2database/h2/1.3.168/h2-1.3.168.jar");
                ReadableByteChannel readableByteChannel = Channels.newChannel(h2Url.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream("lib/h2-1.3.168.jar");

                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, 1 << 24);
            } catch (MalformedURLException ex) {
                printStackTrace(ex);
            } catch (FileNotFoundException ex) {
                printStackTrace(ex);
            } catch (IOException ex) {
                printStackTrace(ex);
            } catch (Exception ex) {
                printStackTrace(ex);
            }
        }
    }
}
