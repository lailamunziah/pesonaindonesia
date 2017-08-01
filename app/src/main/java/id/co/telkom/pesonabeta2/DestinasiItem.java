package id.co.telkom.pesonabeta2;

/**
 * Created by Rp on 3/30/2016.
 */


//********GRIDVIEW************
public class DestinasiItem {
    private int image1;
    private String title1;
    private String discription1;


    public DestinasiItem(int image, String title, String discription) {
        this.image1 = image;
        this.title1 = title;
        this.discription1 = discription;

    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getDiscription1() {
        return discription1;
    }

    public void setDiscription1(String discription1) {
        this.discription1 = discription1;
    }


}
