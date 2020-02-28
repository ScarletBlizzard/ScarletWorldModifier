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

import java.io.File;
import java.io.IOException;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class Config {
    
    public static File config;
    public static ConfigurationLoader<CommentedConfigurationNode> configLoader;
    public static CommentedConfigurationNode rootNode;
    
    public static void setup(File config, ConfigurationLoader<CommentedConfigurationNode> configLoader) {
        Config.config = config;
        Config.configLoader = configLoader;
    }
    
    public static void load() {
        try {
            if (!config.exists()) {
                config.createNewFile();
                rootNode = configLoader.load();
                addDefaultValues();
                configLoader.save(rootNode);
            }
            rootNode = configLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private static void addDefaultValues() {
        CommentedConfigurationNode configNode = rootNode.getNode("config");
        configNode.setComment("This is the configuration file for"
                + "ScarletWorldModifier plugin for Sponge.\n"
                + "If you need help with the configuration, go to "
                + "https://github.com/ScarletBlizzard/ScarletWorldModifier/blob/master/README.md\n");
    }
    
}
