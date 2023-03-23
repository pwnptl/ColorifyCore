package com.colorify.game.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePlayerRequest extends Request{
    private final String name;
}
