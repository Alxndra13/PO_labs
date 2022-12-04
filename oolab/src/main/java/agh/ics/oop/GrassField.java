package agh.ics.oop;

public class GrassField extends AbstractWorldMap {
    int tuftsNumber;

    public GrassField(int tuftsNumber){
        super(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE), new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE),
                (int) Math.sqrt(tuftsNumber*10));
        this.tuftsNumber = tuftsNumber;
        super.edge = (int) (Math.sqrt(tuftsNumber*10));
        this.spawnTufts();
    }

    public void spawnTufts(){
        while (tufts.size() < tuftsNumber){ //dopóki nie będzie ich docelowa liczba
            //losujemy kępkom miejsce na mapie w obszarze (0,0) - (edge,edge)
            Vector2d tuftPosition = randomPosition();
            tufts.put(tuftPosition, new Grass(tuftPosition));
            boundary.addPosition(tuftPosition);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) return true;
        return tufts.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) return super.objectAt(position); //jeśli jest tam zwierze
        return tufts.get(position); //zwraca kępke trawy jeśli znajdzie (inaczej null)
    }
}