package ex.appex2;

public class ItemException extends Exception{


    public ItemException(){
        //super = exception avec msg
        super("un bambou ne peut pas etre rouge");

    }

}
