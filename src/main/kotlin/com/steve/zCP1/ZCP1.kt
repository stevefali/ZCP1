package com.steve.zCP1

import com.steve.zCP1.events.BreakEvent
import org.bukkit.plugin.java.JavaPlugin

class ZCP1 : JavaPlugin() {

    private lateinit var breakEvent: BreakEvent

    override fun onEnable() {
        breakEvent = BreakEvent(server);

        server.pluginManager.registerEvents(breakEvent, this)

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
