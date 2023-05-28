package components;

import engine.GameObject;
import renderer.component.Spritesheet;

public class Animation {

    private int startFrameNumber;
    private int endFrameNumber;
    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;

    public Animation(int endFrameNumber) {
        this.startFrameNumber = 0;
        this.endFrameNumber = endFrameNumber;
    }

    public Animation(int start, int endFrameNumber) {
        this.startFrameNumber = start;
        this.endFrameNumber = endFrameNumber;
        this.spriteIndex = start;
    }


    public void play (float dt, GameObject obj, Spritesheet sprites) {
        spriteFlipTimeLeft -= dt;
        if(spriteFlipTimeLeft <= 0) {
            spriteFlipTimeLeft = spriteFlipTime;
            spriteIndex++;
            if(spriteIndex > endFrameNumber) {
                spriteIndex = startFrameNumber;
            }
            obj.getComponent(SpriteRenderer.class).setSprite(sprites.getSprite(spriteIndex));
        }
    }

}
