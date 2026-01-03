package com.tealsilver.catharsis;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import java.util.function.*;

public class ModItems {

    public static final Item SILVER_INGOT = register(
            "silver_ingot",
            Item::new,
            new Item.Settings()
    );

    public static final Item RAW_SILVER = register(
            "raw_silver",
            Item::new,
            new Item.Settings()
    );

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Catharsis.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> {
                    itemGroup.add(ModItems.RAW_SILVER);
                    itemGroup.add(ModItems.SILVER_INGOT);
                });
    }
}
