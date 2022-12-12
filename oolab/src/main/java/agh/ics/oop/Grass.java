package agh.ics.oop;

public class Grass extends AbstractWorldMapElement{
    public Grass(Vector2d position){
        super(position);
    }

    @Override
    public String toString(){
        return "*";
    }

    @Override
    public String getImageSrc() {
        return "src/main/resources/grass1.png";
    }
}