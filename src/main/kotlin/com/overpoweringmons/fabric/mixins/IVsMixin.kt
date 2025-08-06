package com.overpoweringmons.fabric.mixins

import com.cobblemon.mod.common.pokemon.IVs
import com.overpoweringmons.fabric.config.OverpoweringMonsConfig
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(IVs::class, remap = false)
class IVsMixin {
    
    @Inject(method = ["getMAX_VALUE"], at = [At("HEAD")], cancellable = true)
    private fun getCustomMaxValue(cir: CallbackInfoReturnable<Int>) {
        cir.returnValue = OverpoweringMonsConfig.maxIvValue
    }
}
