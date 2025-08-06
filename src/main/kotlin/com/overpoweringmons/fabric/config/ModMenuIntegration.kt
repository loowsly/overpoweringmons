package com.overpoweringmons.fabric.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import me.shedaniel.clothconfig2.api.ConfigBuilder
import net.minecraft.network.chat.Component

class ModMenuIntegration : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { parent ->
            val builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("Overpowering Mons Config"))
                .setSavingRunnable {
                    // Config is automatically saved when values change
                }
            
            val general = builder.getOrCreateCategory(Component.literal("General Settings"))
            
            general.addEntry(
                builder.entryBuilder()
                    .startIntField(Component.literal("Max IV Value"), OverpoweringMonsConfig.maxIvValue)
                    .setDefaultValue(31)
                    .setMin(1)
                    .setMax(999)
                    .setTooltip(Component.literal("Maximum value for Individual Values (IVs)"))
                    .setSaveConsumer { /* Will be handled by mixin */ }
                    .build()
            )
            
            general.addEntry(
                builder.entryBuilder()
                    .startIntField(Component.literal("Max EV Per Stat"), OverpoweringMonsConfig.maxEvPerStat)
                    .setDefaultValue(252)
                    .setMin(1)
                    .setMax(9999)
                    .setTooltip(Component.literal("Maximum EVs that can be allocated to a single stat"))
                    .setSaveConsumer { /* Will be handled by mixin */ }
                    .build()
            )
            
            general.addEntry(
                builder.entryBuilder()
                    .startIntField(Component.literal("Max Total EVs"), OverpoweringMonsConfig.maxTotalEvs)
                    .setDefaultValue(510)
                    .setMin(1)
                    .setMax(99999)
                    .setTooltip(Component.literal("Maximum total EVs that can be allocated across all stats"))
                    .setSaveConsumer { /* Will be handled by mixin */ }
                    .build()
            )
            
            builder.build()
        }
    }
}
