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
package heckerpowered.magicalfood.common.world.item.enchantment;

import javax.annotation.ParametersAreNonnullByDefault;

import heckerpowered.magicalfood.common.MagicalFood;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * The register class of magical food enchantments, any enchantments derived
 * from {@code MagicalFood} mod should be registered at this class.
 *
 * @author Heckerpowered
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public final class MagicalFoodEnchantment {

    /**
     * The deferred register of all the items derived from {@code MagicalFood} mod.
     */
    public static final DeferredRegister<Enchantment> DEFERRED_REGISTER = DeferredRegister
            .create(ForgeRegistries.ENCHANTMENTS, MagicalFood.MODID);

    /**
     * Do not let anyone instantiate this class
     */
    private MagicalFoodEnchantment() {
    }

    /**
     * The magical hoe enchantment, see {@link MagicalHoeEnchantment} for more
     * details related to this enchantment.
     */
    public static final RegistryObject<MagicalHoeEnchantment> MAGIC_HOE = DEFERRED_REGISTER.register("magical_hoe",
            MagicalHoeEnchantment::new);
}
