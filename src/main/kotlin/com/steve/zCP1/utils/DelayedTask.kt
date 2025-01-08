package com.steve.zCP1.utils

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

class DelayedTask(plugin: Plugin) {

    private var id = -1

    init {
        plug = plugin
    }


    companion object {
        var plug: Plugin? = null;
    }

    constructor(runnable: Runnable, delay: Long) : this(plug!!) {
        if (plug!!.isEnabled) {
            id  = Bukkit.getScheduler().scheduleSyncDelayedTask(plug!!, runnable, delay)
        } else {
            runnable.run()
        }
    }

}