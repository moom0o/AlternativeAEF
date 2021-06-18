[![Discord Mine](https://img.shields.io/discord/807380182729228298?label=chat&logo=discord&logoColor=white)](https://discord.gg/VYZuWRMQ8u)

# AlternativeAEF

AnarchyExploitFixes for people who don't want everything

## Requirements

* ProtocolLib - Mandatory for AlternativeAEF, use normal AEF if you really don't want to install it.

## Prebuilt jar

Go to releases, download the latest jar
<br> If you wish to have a prebuilt BETA jar with the latest code, click Actions at the top of the page, click the
latest build, and download the artifact.

## Building from source

`git clone https://github.com/moom0o/AnarchyExploitFixes.git`

Run `mvn package`

## Donate

You can send bitcoin to `3DJan1GpSkhiWHYec55xKhchZC8NeC829S`

## Config options

Missing config options after an update? Reset your config or manually add the options.
<details>
  <summary>config.yml</summary>

```yml
# AlternativeAEF config

# Patch future/rusher packet fly
PreventPacketFly: true
# Max teleport packets per 10 seconds, this is how the packet fly works, 25 is usually fine, if you go lower players may get stuck.
MaxTeleportPacketsPer10Seconds: 25
LogPacketFlyEvents: false

# Patches the future/rusherhack/kamiblue 2b2t elytra fly exploit
PatchPacketElytraFly: true
# Recommended to not go lower as there could be false positives.
MaxElytraOpensPer10Seconds: 25 # Will only allow players to go about 85km/h on kami blue, and won't even work on rusherhack.

# Patch futureclient/rusherhack boat fly exploit
# This does not affect boats at all! Even on land.
BoatflyPatch: true
MaxEntityPacketsPer10Seconds: 15
LogBoatFlyEvents: true

PreventBurrow: true # Preliminary burrow patch, please test. Makes players take damage every time they move when their feet are in a block
BurrowDamageWhenMoving: 1 # Half a heart of damage every time you move.
TeleportBurrow: true #Teleport player above block

PreventCraftingRecipeLagExploit: true
CraftingRecipeDelay: 5 # in ticks
```

</details>

More information can be found on the main plugin
repo. [https://github.com/moom0o/AnarchyExploitFixes](https://github.com/moom0o/AnarchyExploitFixes)
