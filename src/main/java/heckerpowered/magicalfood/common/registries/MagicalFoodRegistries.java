/**
* Copyright (C) 2023 Heckerpowered Corporation
*
* Permission is hereby granted, free of charge, to any person obtaining a copy of this software
* and associated documentation files (the “Software”), to deal in the Software without
* restriction, including without limitation the rights to use, copy, modify, merge, publish,
* distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all copies or
* substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
* BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
* DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package heckerpowered.magicalfood.common.registries;

import javax.annotation.ParametersAreNonnullByDefault;

import heckerpowered.magicalfood.common.MagicalFood;
import heckerpowered.magicalfood.common.world.level.block.MagicalFarmBlock;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryManager;

/**
 * A class that exposes static references to all and {@link MagicalFood}
 * registries. Created to have a central place to access the registries directly
 * if modders need. It is still advised that if you are registering things to
 * use {@link RegisterEvent} or {@link DeferredRegister}, but queries and
 * iterations can use this.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public final class MagicalFoodRegistries {
    /**
     * Do not let anyone instantiate this class
     */
    private MagicalFoodRegistries() {
    }

    /**
     * {@link MagicalFarmBlock.Feature} feature registry object, used to
     */
    public static final IForgeRegistry<MagicalFarmBlock.Feature> FARM_FEATURES = RegistryManager.ACTIVE
            .getRegistry(Keys.FARM_FEATURES);

    /**
     * {@link MagicalFoodRegistries}'s keys, used to get {@link ForgeRegistry}
     * object
     */
    @ParametersAreNonnullByDefault
    @MethodsReturnNonnullByDefault
    @FieldsAreNonnullByDefault
    public static final class Keys {
        /**
         * Do not let anyone instantiate this class
         */
        private Keys() {
        }

        /**
         * The registry key of {@link MagicalFarmBlock.Feature}, normally this field
         * should only be referenced by {@link MagicalFoodRegistries} for getting
         * {@link ForgeRegistry} object.
         */
        public static final ResourceKey<Registry<MagicalFarmBlock.Feature>> FARM_FEATURES = ResourceKey
                .createRegistryKey(MagicalFood.getResource("farm_feature"));
    }
}
