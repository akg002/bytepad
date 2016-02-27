package in.silive.bo.model;

import java.util.ArrayList;

/**
 * Created by kartikey on 22/02/16.
 */
public class PaperModel {
    public String Title;
    public String ExamCategory;
    public String PaperCategory;
    public String Size;
    public String URL;
    public String RelativeURL;
    @SuppressWarnings("serial")
    public static class PapersList extends ArrayList<PaperModel> {
    }
}
