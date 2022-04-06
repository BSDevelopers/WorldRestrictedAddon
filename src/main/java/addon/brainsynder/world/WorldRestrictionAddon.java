package addon.brainsynder.world;

import com.google.common.collect.Lists;
import org.bukkit.event.EventHandler;
import simplepets.brainsynder.addon.AddonConfig;
import simplepets.brainsynder.addon.PetModule;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.event.entity.PetMountEvent;
import simplepets.brainsynder.api.event.entity.PrePetHatEvent;
import simplepets.brainsynder.api.plugin.SimplePets;

import java.util.ArrayList;
import java.util.List;

@Namespace(namespace = "WorldRestriction")
public class WorldRestrictionAddon extends PetModule {
    private boolean rideRestriction = false;
    private List<String> rideWorldRestrictions = new ArrayList<>();
    private String rideRestrictionReason = null;

    private boolean hatRestriction = false;
    private List<String> hatWorldRestrictions = new ArrayList<>();
    private String hatRestrictionReason = null;

    @Override
    public void init() {
        SimplePets.getDebugLogger().debug(SimplePets.ADDON, "To reload the addons config follow these steps: https://wiki.pluginwiki.us/faq#how-can-i-reload-my-addons-after-customizing-them");
    }

    @Override
    public void loadDefaults(AddonConfig config) {
        config.addDefault("world-restrictions.pet-riding.enabled", false, "Should this restriction be enabled?");
        config.addDefault("world-restrictions.pet-riding.list", Lists.newArrayList("world_nether"),
                "List the worlds that pet riding should not be allowed in here.");
        config.addDefault("world-restrictions.pet-riding.reason", "&cPet riding is disabled in this world",
                "This message gets sent to the player when they try to ride a pet in a blocked world");

        config.addDefault("world-restrictions.pet-hat.enabled", false, "Should this restriction be enabled?");
        config.addDefault("world-restrictions.pet-hat.list", Lists.newArrayList("world_nether"),
                "List the worlds that pet hats should not be allowed in here.");
        config.addDefault("world-restrictions.pet-hat.reason", "&cPutting your pet on your head is disabled in this world",
                "This message gets sent to the player when they try to put their pet on their head in a blocked world");


        rideRestriction = config.getBoolean("world-restrictions.pet-riding.enabled", false);
        rideWorldRestrictions = config.getStringList("world-restrictions.pet-riding.list");
        rideRestrictionReason = config.getString("world-restrictions.pet-riding.reason", null);

        hatRestriction = config.getBoolean("world-restrictions.pet-hat.enabled", false);
        hatWorldRestrictions = config.getStringList("world-restrictions.pet-hat.list");
        hatRestrictionReason = config.getString("world-restrictions.pet-hat.reason", null);
    }

    @EventHandler
    public void onRide (PetMountEvent event) {
        if (!rideRestriction) return;
        if (rideWorldRestrictions.isEmpty()) return;
        if (rideWorldRestrictions.contains(event.getEntity().getEntity().getWorld().getName())) {
            if (rideRestrictionReason == null) {
                event.setCancelled(true);
            }else{
                event.setCancelled(true, rideRestrictionReason);
            }
        }
    }

    @EventHandler
    public void onHat (PrePetHatEvent event) {
        if (event.getEventType() == PrePetHatEvent.Type.REMOVE) return;
        if (!hatRestriction) return;
        if (hatWorldRestrictions.isEmpty()) return;
        if (hatWorldRestrictions.contains(event.getEntityPet().getEntity().getWorld().getName())) {
            if (hatRestrictionReason == null) {
                event.setCancelled(true);
            }else{
                event.setCancelled(true, hatRestrictionReason);
            }
        }
    }
}
