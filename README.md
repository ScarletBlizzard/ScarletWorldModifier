# ScarletWorldModifier
A configurable Sponge plugin for modifying world generation by replacing blocks.

Edit `scarlet_world_modifier.conf` in `config` directory to manage block replacements in specific worlds and biomes.

## Sample config
<pre>
config {
    <i>world-name</i> {
	biomes {
	     "<i>biome-id</i>" {
		  "<i>original-block-id</i>"="<i>final-block-id</i>"
	     }
	}
	global {
	    "<i>original-block-id</i>"="<i>final-block-id</i>"
	}
    }
}
</pre>

## Important!

If *final-block-id* is invalid, then block with *original-block-id* will be replaced by stone.

In order for the plugin to replace blocks in world with name *world-name*, there needs to be following entry in file `config/
sponge/worlds/minecraft/`*dimension*`/`*world-name*`/world.conf`:

<pre>
world-generation-modifiers=[
    "scarlet_world_modifier:swm"
]
</pre>

## Examples
### Hell
![swm hell](https://i.imgur.com/W0HSeBR.png)
Config:
<pre>
config {
    hell {
	global {
	    "minecraft:dirt"="minecraft:netherrack"
	    "minecraft:grass"="minecraft:netherrack"
	    "minecraft:gravel"="minecraft:soul_sand"
	    "minecraft:hardened_clay"="minecraft:netherrack"
	    "minecraft:ice"="minecraft:lava"
	    "minecraft:mycelium"="minecraft:netherrack"
	    "minecraft:mossy_cobblestone"="minecraft:netherrack"
	    "minecraft:red_sand"="minecraft:soul_sand"
	    "minecraft:red_sand"="minecraft:netherrack"
	    "minecraft:sand"="minecraft:soul_sand"
	    "minecraft:snow"="minecraft:air"
	    "minecraft:snow_layer"="minecraft:air"
	    "minecraft:packed_ice"="minecraft:air"
	    "minecraft:stained_hardened_clay"="minecraft:netherrack"
	    "minecraft:stone"="minecraft:netherrack"
	    "minecraft:water"="minecraft:lava"
	}
    }
}
</pre>
### Arid
![swm arid](https://i.imgur.com/Q1jvNKc.png)
Config:
<pre>
config {
    arid {
	global {
	    "minecraft:grass"="minecraft:dirt[variant=coarse_dirt]"
	    "minecraft:ice"="minecraft:water"
	    "minecraft:snow"="minecraft:air"
	    "minecraft:snow_layer"="minecraft:air"
	    "minecraft:packed_ice"="minecraft:air"
	    "minecraft:water"="minecraft:air"
	}
    }
}
</pre>
