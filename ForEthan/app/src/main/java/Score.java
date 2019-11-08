public class Score {

    private int number_of_clicks = 0;
    private int number_correct_answers = 0;



    public double getScore(){
        return (number_correct_answers/number_of_clicks)*100;
    }

    public int getNumber_correct_answers() {
        return number_correct_answers;
    }

    public int getNumber_of_clicks() {
        return number_of_clicks;
    }

    public void setNumber_correct_answers(int number_correct_answers) {
        this.number_correct_answers = number_correct_answers;
    }

    public void setNumber_of_clicks(int number_of_clicks) {
        this.number_of_clicks = number_of_clicks;
    }


}
