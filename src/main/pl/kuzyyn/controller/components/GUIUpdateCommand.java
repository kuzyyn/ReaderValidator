package main.pl.kuzyyn.controller.components;

import java.util.TreeMap;

public interface GUIUpdateCommand {
public <V> void execute(TreeMap<String,V> values);
}
