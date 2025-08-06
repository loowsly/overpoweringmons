package com.overpoweringmons.fabric;

import com.overpoweringmons.fabric.config.OverpoweringMonsConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import static net.minecraft.commands.Commands.literal;

public class OverpoweringMonsMod implements ModInitializer {
    public static final String MOD_ID = "overpowering_mons";
    
    @Override
    public void onInitialize() {
        // Load configuration
        OverpoweringMonsConfig.load();
        
        // Register commands
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("overpowering-mons")
                .then(literal("reload").executes(context -> {
                    OverpoweringMonsConfig.load();
                    context.getSource().sendSystemMessage(
                        Component.literal("Overpowering Mons config reloaded!")
                            .withStyle(Style.EMPTY.withColor(0x00FF00))
                    );
                    return 1;
                }))
                .then(literal("info").executes(context -> {
                    context.getSource().sendSystemMessage(
                        Component.literal("=== Overpowering Mons Config ===")
                            .withStyle(Style.EMPTY.withColor(0xFFD700))
                    );
                    context.getSource().sendSystemMessage(
                        Component.literal("Max IV Value: " + OverpoweringMonsConfig.maxIvValue)
                            .withStyle(Style.EMPTY.withColor(0x00BFFF))
                    );
                    context.getSource().sendSystemMessage(
                        Component.literal("Max EV Per Stat: " + OverpoweringMonsConfig.maxEvPerStat)
                            .withStyle(Style.EMPTY.withColor(0x00BFFF))
                    );
                    context.getSource().sendSystemMessage(
                        Component.literal("Max Total EVs: " + OverpoweringMonsConfig.maxTotalEvs)
                            .withStyle(Style.EMPTY.withColor(0x00BFFF))
                    );
                    return 1;
                }))
            );
        });
    }
}
