package com.steve.zCP1.commands

import com.steve.zCP1.ZCP1
import com.steve.zCP1.utils.DelayedTask
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack


class GuiCommand(plugin: ZCP1) : CommandExecutor, Listener {

    private val INVENTORY_NAME = "GUI"
    private val config = plugin.config

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("This command is only for players.")
            return true
        }

        val inventory = Bukkit.createInventory(sender, 9 * 1, INVENTORY_NAME)
        inventory.addItem(makeItem(Material.ENDER_PEARL, "Transporter", listOf("Go to a Place.")))

        sender.openInventory(inventory)
        return true

    }

    private fun makeItem(material: Material, name: String, lore: List<String>): ItemStack {
        val itemStack = ItemStack(material, 1);
        val meta = itemStack.itemMeta

        meta?.setDisplayName(name)
        meta?.lore = lore
        itemStack.setItemMeta(meta)

        return itemStack
    }

    @EventHandler
    fun onItemClicked(event: InventoryClickEvent) {
        if (event.view.title != INVENTORY_NAME) {
            return
        }

        if (event.currentItem?.type == Material.ENDER_PEARL) {
            val player = event.whoClicked
            val location = Location(
                player.world,
                config.getDouble("teleportX"),
                config.getDouble("teleportY"),
                config.getDouble("teleportZ"),
                player.location.yaw,
                player.location.pitch
            )
            player.teleport(
                location
            )

            DelayedTask(20 * 3) {
                player.world.spawnEntity(player.location, EntityType.SHEEP)
                player.world.getBlockAt(location.add(2.0, 1.0, 0.0)).type = Material.GOLD_BLOCK
            }

        }

        event.isCancelled = true
    }
}