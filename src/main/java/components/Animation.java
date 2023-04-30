package components;

import engine.GameObject;

public class Animation {

    private int endFrameNumber;
    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;

    public Animation(int endFrameNumber) {
        this.endFrameNumber = endFrameNumber;
    }

    public Animation(int endFrameNumber, float spriteFlipTime) {
        this.endFrameNumber = endFrameNumber;
        this.spriteFlipTime = spriteFlipTime;
    }

    public void play (float dt, GameObject obj, Spritesheet sprites) {
        spriteFlipTimeLeft -= dt;
        if(spriteFlipTimeLeft <= 0) {
            spriteFlipTimeLeft = spriteFlipTime;
            spriteIndex++;
            if(spriteIndex > 4) {
                spriteIndex = 0;
            }
            obj.getComponent(SpriteRenderer.class).setSprite(sprites.getSprite(spriteIndex));
        }
    }

}
