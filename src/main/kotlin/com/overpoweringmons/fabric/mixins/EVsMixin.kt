package com.overpoweringmons.fabric.mixins

import com.cobblemon.mod.common.pokemon.EVs
import com.overpoweringmons.fabric.config.OverpoweringMonsConfig
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(EVs::class, remap = false)
class EVsMixin {
    
    @Inject(method = ["getMAX_STAT_VALUE"], at = [At("HEAD")], cancellable = true)
    private fun getCustomMaxStatValue(cir: CallbackInfoReturnable<Int>) {
        cir.returnValue = OverpoweringMonsConfig.maxEvPerStat
    }
    
    @Inject(method = ["getMAX_TOTAL_VALUE"], at = [At("HEAD")], cancellable = true)
    private fun getCustomMaxTotalValue(cir: CallbackInfoReturnable<Int>) {
        cir.returnValue = OverpoweringMonsConfig.maxTotalEvs
    }
}
