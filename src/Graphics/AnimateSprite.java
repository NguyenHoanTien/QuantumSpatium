package Graphics;

public class AnimateSprite extends Sprite {

    private int frame = 0;
    private Sprite sprite;
    private int rate;
    private int time = 0;
    private int length = -1;

    public AnimateSprite(SpriteSheet sheet, int width, int height, int length, int rate) {
        super(sheet, width, height);
        this.length = length;
        this.rate = rate;
        sprite = sheet.getSprites()[0];
        if (length > sheet.getSprites().length) {
            System.err.println("Error! Length of animation is too long!");
        }
    }

    public void update() {
        time++;
        if (time % rate == 0) {
            if (frame >= length - 1) {
                frame = 0;
            } else {
                frame++;
            }
            sprite = sheet.getSprites()[frame];
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setFrameRate(int frames) {
        rate = frames;
    }
}
