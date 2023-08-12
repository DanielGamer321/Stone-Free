package com.danielgamer321.rotp_sf.init;

import com.danielgamer321.rotp_sf.entity.stand.stands.StoneFreeEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject.EntityStandSupplier;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;

public class AddonStands {

    public static final EntityStandSupplier<EntityStandType<StandStats>, StandEntityType<StoneFreeEntity>> 
    STONE_FREE = new EntityStandSupplier<>(InitStands.STAND_STONE_FREE);
}
