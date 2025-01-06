package com.steve.zCP1.events

import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BreakEvent(val server: Server) : Listener {

    @EventHandler
    fun blockBreak(event: BlockBreakEvent) {
        if (event.block.type == Material.SAND) {
            server.logger.info("Sand block broken!")
        }
    }

}