package com.colorify.colorify.model.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public abstract class Player {
    protected String id;
    protected String name;
}
