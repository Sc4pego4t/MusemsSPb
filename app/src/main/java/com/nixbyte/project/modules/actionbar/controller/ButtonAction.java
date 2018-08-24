package com.nixbyte.project.modules.actionbar.controller;

/**
 * Created by nixbyte on 26.01.17.
 */

public class ButtonAction implements Action {

    private ButtonBehavior behavior;

    public void setBehavior(ButtonBehavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public void performAction() {
        behavior.perform();
    }
}

