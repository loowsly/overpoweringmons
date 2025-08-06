package com.overpoweringmons.fabric.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.fabricmc.loader.api.FabricLoader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

object OverpoweringMonsConfig {
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    private val configFile = File(FabricLoader.getInstance().configDir.toFile(), "overpowering_mons.json")
    
    // Default values (vanilla Cobblemon values)
    var maxIvValue: Int = 31
        private set
    var maxEvPerStat: Int = 252
        private set
    var maxTotalEvs: Int = 510
        private set
    
    fun load() {
        if (!configFile.exists()) {
            save()
            return
        }
        
        try {
            FileReader(configFile).use { reader ->
                val config = gson.fromJson(reader, ConfigData::class.java)
                maxIvValue = config.maxIvValue.coerceIn(1, 999)
                maxEvPerStat = config.maxEvPerStat.coerceIn(1, 9999)
                maxTotalEvs = config.maxTotalEvs.coerceIn(1, 99999)
            }
        } catch (e: Exception) {
            println("Failed to load Overpowering Mons config: ${e.message}")
            save() // Create a new config file with defaults
        }
    }
    
    private fun save() {
        try {
            configFile.parentFile.mkdirs()
            FileWriter(configFile).use { writer ->
                val config = ConfigData(maxIvValue, maxEvPerStat, maxTotalEvs)
                gson.toJson(config, writer)
            }
        } catch (e: Exception) {
            println("Failed to save Overpowering Mons config: ${e.message}")
        }
    }
    
    data class ConfigData(
        val maxIvValue: Int = 31,
        val maxEvPerStat: Int = 252,
        val maxTotalEvs: Int = 510
    )
}
