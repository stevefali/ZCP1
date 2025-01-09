package com.steve.zCP1.events

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin

class PlayerEvent(plugin: Plugin) : Listener {
    private var levelKey: NamespacedKey = NamespacedKey(plugin, "playerLevel")


    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val playerData = event.player.persistentDataContainer

        if (!playerData.has(levelKey, PersistentDataType.INTEGER)) {
            playerData.set(levelKey, PersistentDataType.INTEGER, 0)
        }

    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val player = event.entity
        player.sendMessage("§4Your current level: ${player.persistentDataContainer.get(levelKey, PersistentDataType.INTEGER)}")
    }


}