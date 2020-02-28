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

import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameRegistryEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;

@Plugin(id = "scarlet_world_modifier", name = "ScarletWorldModifier",
        version = "1.0.0", authors = "ScarletBlizzard", 
        description = "A configurable Sponge plugin for modifying world generation by replacing blocks.",
        url = "https://github.com/ScarletBlizzard/ScarletWorlds")
public class ScarletWorldModifier {

    @Inject private Logger logger;
    
    @Inject
    @DefaultConfig(sharedRoot = true)
    private File config;
    
    @Inject
    @DefaultConfig(sharedRoot = true)
    private ConfigurationLoader<CommentedConfigurationNode> configLoader;
    
    @Listener
    public void onGameStart(GameStartedServerEvent e) throws IOException {
        Config.setup(config, configLoader);
        Config.load();
        logger.info("Successfully running ScarletWorldModifier plugin.");
    }
    
    @Listener
    public void worldGeneratorModifierRegistration(GameRegistryEvent.Register<WorldGeneratorModifier> e) {
        e.register(new GeneratorModifier());
    }
    
}
