package core.physics;

import core.os.listeners.MouseListener;
import engine.Transform;
import org.joml.Vector2f;

public class CollisionBox {
    public Transform transform;
    private Vector2f size;

    public CollisionBox(Transform transform, Vector2f size) {
        this.transform = transform;
        this.size = size;
    }

    public Vector2f getSize() {
        return size;
    }

    public void changePos(Vector2f pos){
        this.transform.setPosition(pos);
    }



    public boolean isThatCollited(CollisionBox collisionBox) {
        boolean collisionX = this.transform.position.x <=
                collisionBox.transform.position.x + collisionBox.size.x &&
                this.transform.position.x + this.size.x >= collisionBox.transform.position.x;


        boolean collisionY = this.transform.position.y <=
                collisionBox.transform.position.y + collisionBox.size.y &&
                this.transform.position.y + this.size.y >collisionBox.transform.position.y;
        return collisionX && collisionY;
    }

    public boolean isCollitedWithTheCursor() {
        boolean collisionX = this.transform.position.x <= MouseListener.getOrthoX() &&
                (this.transform.position.x + this.size.x) >= MouseListener.getOrthoX();
        boolean collisionY = this.transform.position.y <= MouseListener.getOrthoY() &&
                this.transform.position.y + this.transform.scale.y >= MouseListener.getOrthoY();
        System.out.println(this.transform.position.x + "-" +  (this.transform.position.x + this.size.x) + "     " + MouseListener.getX() + "    " + (collisionX && collisionY));
        return collisionX && collisionY;
    }

}
