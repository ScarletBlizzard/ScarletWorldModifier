/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 ScarletBlizzard
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/

package com.github.scarletblizzard.swm;

import java.io.IOException;

import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

import com.flowpowered.math.vector.Vector3i;

import ninja.leaping.configurate.ConfigurationNode;

public class BlockPopulator implements GenerationPopulator {

	GameRegistry registry = Sponge.getRegistry();
    BlockState defaultBlock = BlockTypes.STONE.getDefaultState();
	
    @Override
    public void populate(World world, MutableBlockVolume buffer, ImmutableBiomeVolume biomes) {
        try {
            ConfigurationNode rootNode = Config.configLoader.load();
            ConfigurationNode worldNode = rootNode.getNode("config", world.getProperties().getWorldName());
            ConfigurationNode globalNode = worldNode.getNode("global");
            ConfigurationNode biomesNode = worldNode.getNode("biomes");
            
            final Vector3i max = buffer.getBlockMax();
            final Vector3i min = buffer.getBlockMin();
            for (int x = min.getX(); x <= max.getX(); x++) {
                for (int z = min.getZ(); z <= max.getZ(); z++) {
                    for (int y = 0; y < 256; y++) {
                    	
                    	Vector3i position = new Vector3i(x, y, z);
                        String originalBlockId = buffer.getBlock(position).getId();
                        String modifiedBlockId = null;
                        if (originalBlockId.contains("["))
                        	modifiedBlockId = originalBlockId.split("\\[")[0];
                        
                        
                        ConfigurationNode blockNode = globalNode.getNode(originalBlockId);
                        if (!blockNode.isVirtual()) {
                        	String finalBlockId = blockNode.getString();
                        	setBlock(finalBlockId, buffer, position);
                        } else if (modifiedBlockId != null) {
                        	ConfigurationNode modifiedBlockNode = globalNode.getNode(modifiedBlockId);
                        	if (!modifiedBlockNode.isVirtual()) {
                        		String finalBlockId = modifiedBlockNode.getString();
                            	setBlock(finalBlockId, buffer, position);
                        	}
                        }
                        
                        String biomeId = biomes.getBiome(x, 0, z).getId();
                        ConfigurationNode biomeNode = biomesNode.getNode(biomeId).getNode(biomeId);
                        ConfigurationNode biomeBlockNode = biomeNode.getNode(originalBlockId);
                        if (!biomeBlockNode.isVirtual()) {
                            String finalBlockId = biomeBlockNode.getString();
                        	setBlock(finalBlockId, buffer, position);
                        } else if (modifiedBlockId != null) {
                        	ConfigurationNode modifiedBlockNode = biomeNode.getNode(modifiedBlockId);
                        	if (!modifiedBlockNode.isVirtual()) {
                                String finalBlockId = modifiedBlockNode.getString();
                            	setBlock(finalBlockId, buffer, position);
                        	}
                        }
                        
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void setBlock(String finalBlockId, MutableBlockVolume buffer, Vector3i position) {
        if (finalBlockId != null)
            buffer.setBlock(position, registry.getType(BlockState.class, finalBlockId).orElse(defaultBlock));
    }
    
}
