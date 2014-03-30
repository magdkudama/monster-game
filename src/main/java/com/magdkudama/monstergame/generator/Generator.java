package com.magdkudama.monstergame.generator;

import com.magdkudama.monstergame.model.World;

import java.io.IOException;

public interface Generator {
    public World generate() throws IOException;
}
