package com.steve.zCP1

import com.steve.zCP1.commands.GuiCommand
import com.steve.zCP1.events.BreakEvent
import com.steve.zCP1.utils.DelayedTask
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

class ZCP1 : JavaPlugin() {

    private lateinit var breakEvent: BreakEvent

    private val config = getConfig();


    override fun onEnable() {
        DelayedTask(this)

        breakEvent = BreakEvent(server)


        getCommand("gui")?.setExecutor(GuiCommand(this))

        server.pluginManager.registerEvents(breakEvent, this)

        setupSpawnConfig()

    }

    override fun onDisable() {
        // Plugin shutdown logic

        HandlerList.unregisterAll(breakEvent)
    }



    private fun setupSpawnConfig() {
        config.addDefault("teleportX", 25.0)
        config.addDefault("teleportY", 64.0)
        config.addDefault("teleportZ", 25.0)
    }

}
