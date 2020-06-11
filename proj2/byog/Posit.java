//useful comparison methods for comparing positions
//Don't actually need serialization here (commented out), but brief summary below (don't fully understand)
/*
Serialization is the process of saving an object's state to a sequence of bytes,
which then can be stored on a file or sent over the network, and deserialization
is the process of reconstructing an object from those bytes.
For more detail: https://dzone.com/articles/what-is-serialization-everything-about-java-serial#:~:text=Only%20Classes%20That%20Implement%20Serializable%20Can%20Be%20Serialized&text=Java's%20default%20serialization%20process%20is,except%20static%20and%20transient%20fields).
http://prinavtech.blogspot.com/2017/04/java-serialization-and-deserialization.html
 */
package byog;

//import java.io.Serializable;

public class Posit /*implements Serializable*/ {
    private int xPos;
    private int yPos;

    public Posit(int xP, int yP) {
        xPos = xP;
        yPos = yP;
    }

    public int xPos() {
        return xPos;
    }

    public int yPos() {
        return yPos;
    }

    public static Posit smallerX(Posit p1, Posit p2) {
        if (p1.xPos < p2.xPos) {
            return p1;
        } else {
            return p2;
        }
    }

    public static Posit largerX(Posit p1, Posit p2) {
        if (p1.xPos < p2.xPos) {
            return p2;
        } else {
            return p1;
        }
    }

    public static Posit smallerY(Posit p1, Posit p2) {
        if (p1.yPos < p2.yPos) {
            return p1;
        } else {
            return p2;
        }
    }

    public static Posit largerY(Posit p1, Posit p2) {
        if (p1.yPos < p2.yPos) {
            return p2;
        } else {
            return p1;
        }
    }
}