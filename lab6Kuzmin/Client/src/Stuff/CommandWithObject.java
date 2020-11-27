package Stuff;

public interface CommandWithObject extends Commandable{
    public boolean check(Object arg);
    public LabWork getNewlabwork(Object params);
}