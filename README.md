<div align="center">
    <a href="https://www.codefactor.io/repository/github/brainsynder-dev/worldrestrictedaddon"><img src="https://www.codefactor.io/repository/github/brainsynder-dev/worldrestrictedaddon/badge" alt="CodeFactor" /></a>
</div>

# WorldRestrictedAddon
This is an addon for the SimplePets v5 plugin.

It aims to block certain tasks from certain worlds.

### Default configuration (Located in `plugins/SimplePets/Addons/config/WorldRestriction.yml`)
```yaml
world-restrictions:
  pet-riding:
    # Should this restriction be enabled?
    enabled: false
    
    # List the worlds that pet riding should not be allowed in here.
    list:
    - world_nether
    
    # This message gets sent to the player when they try to ride a pet in a blocked world
    reason: '&cPet riding is disabled in this world'
    
  pet-hat:
    # Should this restriction be enabled?
    enabled: false
    
    # List the worlds that pet hats should not be allowed in here.
    list:
    - world_nether
    
    # This message gets sent to the player when they try to put their pet on their head in a blocked world
    reason: '&cPutting your pet on your head is disabled in this world'
```