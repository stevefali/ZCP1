package com.steve.zCP1.events

import com.steve.zCP1.utils.DelayedTask
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.craftbukkit.v1_21_R2.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_21_R2.util.CraftChatMessage
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent


class BreakEvent(val server: Server) : Listener {

    @EventHandler
    fun blockBreak(event: BlockBreakEvent) {
        if (event.block.type == Material.SAND) {
            server.logger.info("Sand block broken!")
        }

        if (event.block.type == Material.GOLD_BLOCK) {
            DelayedTask(20 * 4) {
                InformPlayerOfNextLevel(event.player)
            }
        }
    }


    private fun InformPlayerOfNextLevel(player: Player) {
        val crftPlr: CraftPlayer = player as CraftPlayer

        val titleAnimationPacket = ClientboundSetTitlesAnimationPacket(20, 20 * 5, 20)
        crftPlr.handle.f.b(titleAnimationPacket)

        val titleTextPacket = ClientboundSetTitleTextPacket(CraftChatMessage.fromString("§aNext Level Reached")[0])
        crftPlr.handle.f.b(titleTextPacket)


    }


}