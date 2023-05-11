package core.physics;

import engine.Transform;
import org.joml.Vector2f;

public class CollisionBox {
    private Transform transform;
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
        boolean collisionX = this.transform.position.x + this.size.x >=
                collisionBox.transform.position.x &&
                collisionBox.transform.position.x + collisionBox.getSize().x >=
                collisionBox.transform.position.x;
        boolean collisionY = this.transform.position.y + this.size.y >=
                collisionBox.transform.position.y &&
                collisionBox.transform.position.y + collisionBox.getSize().y >=
                        collisionBox.transform.position.y;
        return collisionX && collisionY;
    }

    /*
    public boolean isThatCollited(CollisionBox collisionBox) {
        boolean collisionX = (this.transform.position.x + ((this.size.x) * this.transform.scale.x)) >=
                collisionBox.transform.position.x &&
                collisionBox.transform.position.x + (collisionBox.getSize().x * collisionBox.transform.scale.x) >=
                        collisionBox.transform.position.x;
        boolean collisionY = (this.transform.position.y + ((this.size.y) * this.transform.scale.y)) >=
                collisionBox.transform.position.y &&
                collisionBox.transform.position.y + (collisionBox.getSize().y * collisionBox.transform.scale.y) >=
                        collisionBox.transform.position.y;
        return collisionX && collisionY;
    }

    */
}
