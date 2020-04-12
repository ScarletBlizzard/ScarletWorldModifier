# ScarletWorldModifier
A configurable Sponge plugin for modifying world generation by replacing blocks.

Edit `scarlet_world_modifier.conf` in `config` directory to manage block replacements in specific worlds and biomes.

**Sample config:**
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

**Important!**

*block-id* refers to the id of the block with its variants if it has any. For example, if you want to write id of a simple
dirt block, you have to write `minecraft:dirt[snowy=false,variant:dirt]` but not just `minecraft:dirt`.

If *final-block-id* is invalid, then block with *original-block-id* will be replaced by stone.

In order for the plugin to replace blocks in world with name *world-name*, there needs to be following entry in file `config/
sponge/worlds/minecraft/`*dimension*`/`*world-name*`/world.conf`:

<pre>
world-generation-modifiers=[
    "scarlet_world_modifier:swm"
]
</pre>
