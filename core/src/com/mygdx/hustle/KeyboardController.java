package com.mygdx.hustle;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class KeyboardController implements InputProcessor {
    public boolean left,right,up,down;

    @Override
    public boolean keyDown(int keycode) {
        boolean keyProcessed = false;
        switch (keycode) // switch code base on the variable keycode
        {
            case Keys.LEFT:             // if keycode is the same as Keys.LEFT a.k.a 21
            case Keys.A:                // if keycode is the same as Keys.A a.k.a 29
                left = true;            // do this
                keyProcessed = true;    // we have reacted to a keypress
                break;
            case Keys.RIGHT:
            case Keys.D:
                right = true;
                keyProcessed = true;
                break;
            case Keys.UP:
            case Keys.W:
                up = true;
                keyProcessed = true;
                break;
            case Keys.DOWN:
            case Keys.S:
                down = true;
                keyProcessed = true;
        }
        return keyProcessed;           //  return our flag
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean keyProcessed = false;
        switch (keycode) // switch code base on the variable keycode
        {
            case Keys.LEFT:             // if keycode is the same as Keys.LEFT a.k.a 21
            case Keys.A:                // if keycode is the same as Keys.A a.k.a 29
                left = false;           // do this
                keyProcessed = true;    // we have reacted to a keypress
                break;
            case Keys.RIGHT:
            case Keys.D:
                right = false;
                keyProcessed = true;
                break;
            case Keys.UP:
            case Keys.W:
                up = false;
                keyProcessed = true;
                break;
            case Keys.DOWN:
            case Keys.S:
                down = false;
                keyProcessed = true;
        }
        return keyProcessed;            //  return our flag
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
